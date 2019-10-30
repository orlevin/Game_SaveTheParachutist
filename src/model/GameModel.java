package model;

import java.util.ArrayList;

import view.GameGui;

public class GameModel {
	private GameStatus status;
	private ArrayList<GameCharacter> characterList; 
	private AirplaneMovements airplaneMove;
	private static final int BOAT_INDEX = 0;
	private static final int AIRPLANE_INDEX = 1;
	private static final int PARACHUTIST_START_INDEX = 2;

	public GameModel( Boat boat, Airplane airplane,GameStatus status){
		
		characterList = new ArrayList<GameCharacter >();
		characterList.add(boat);
		characterList.add(airplane);
		airplaneMove = new StraightAirplaneMove();
		this.status =status; 
	}

	//Boat control
	public int getBoatX(){
		return characterList.get(BOAT_INDEX).getPositionX();
	}

	public int getBoatY(){
		return characterList.get(BOAT_INDEX).getPositionY();
	}

	public void setBoatX(int position){
		characterList.get(BOAT_INDEX).setPositionX(position);
	}

	public void setBoatY(int position){
		characterList.get(BOAT_INDEX).setPositionY(position);
	}

	public void moveBoatX(int step){
		characterList.get(BOAT_INDEX).moveOnX(step);
	}

	public void moveBoatY(int step){
		characterList.get(BOAT_INDEX).moveOnY(step);
	}

	//airplane functions
	public void moveAirplane(){
		airplaneMove.airplaneMovement((Airplane) characterList.get(AIRPLANE_INDEX),GameGui.BOARD_WIDTH , status.getLevel());
	}
	public int getAirplaneX(){
		return characterList.get(AIRPLANE_INDEX).getPositionX();
	}

	public int getgetAirplaneY(){
		return characterList.get(AIRPLANE_INDEX).getPositionY();
	}


	//parachutist functions

	public boolean makeParachutist(int parachutistHeigth, int parachtistWidth){
		double makingChance = Math.random() * 1000;
		if(makingChance <= status.getLevel()+10 && (getAirplaneX() > 0) && (getAirplaneX() < GameGui.BOARD_WIDTH - parachtistWidth)){
			characterList.add(new Parachutist(characterList.get(AIRPLANE_INDEX).getPositionX(), characterList.get(AIRPLANE_INDEX).getPositionY(),parachutistHeigth,parachtistWidth));
			return true;			
		}
		return false;
	}

	public void moveParachutists(){
		if( characterList.get(PARACHUTIST_START_INDEX) != null){
			for (int i = PARACHUTIST_START_INDEX; i < characterList.size(); i++) {
				characterList.get(i).moveOnY(((status.getLevel()/5)+1));;
			}	
		}
	}
	public ArrayList<GameCharacter> getParachutList(){
		return characterList;
	}

	//statuses functions
	public void statusUpdata(GameStatus status){ 
		this.status= status;
		if(characterList.get(PARACHUTIST_START_INDEX) != null){
			Parachutist savedParachutist =null, drownParachutist = null;
			for (int i = PARACHUTIST_START_INDEX; i < characterList.size(); i++) {
				Parachutist parachutist = (Parachutist)characterList.get(i);
				if(isParachutistSaved(parachutist, (Boat)characterList.get(BOAT_INDEX))){
					savedParachutist = parachutist;
					status.addPoint();
				}	
				else if(isParachutistDrown(parachutist,(Boat) characterList.get(BOAT_INDEX))){
					drownParachutist = parachutist;
					status.subtractlives();
				}
			}
			characterList.remove(savedParachutist);
			characterList.remove(drownParachutist);
		}
		levelUpCheck(status); 
	}

	private boolean isParachutistSaved(Parachutist parachutist, Boat boat){
		if(boat.getPositionX() <=   (parachutist.getPositionX()+(parachutist.getWidth())) && boat.getPositionX()+boat.getWidth() >= parachutist.getPositionX() && 
				parachutist.getPositionY()+(parachutist.getHeigth()/3) >= (boat.getPositionY()- boat.getHeigth()/3) &&
				parachutist.getPositionY()+(parachutist.getHeigth()/3) <= (boat.getPositionY()+ boat.getHeigth())/0.5)
			return true;
		return false;
	}

	private boolean isParachutistDrown(Parachutist parachutist, Boat boat){
		if( parachutist.getPositionY()+(parachutist.getHeigth()/3) > (boat.getPositionY()+boat.getHeigth()/3))
			return true;
		return false;
	}

	private void levelUpCheck(GameStatus status){
		if(status.getLevelUP() == true && status.getPoints() > 0 && (status.getPoints() %10) == 0 ){
			status.setLevelUp(false);
			status.increaseLevel();
		}
		else if(status.getPoints() %10 != 0 && status.getLevelUP() == false )
			status.setLevelUp(true);

	}

}
