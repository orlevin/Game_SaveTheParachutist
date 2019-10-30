package model;

public class StraightAirplaneMove implements AirplaneMovements {

	//to make the level number a factor, it parameter speedUp the airplane when the level goes up (in jumps of five)  	
	private int increaseSpeedRatio = 5; 	

	@Override
	public void airplaneMovement(Airplane airplane, int startPosition, int level) {
		// TODO Auto-generated method stub
		airplane.moveOnX(-1 - (level/increaseSpeedRatio));
		if(airplane.getPositionX() < -airplane.getWidth() )
			airplane.setPositionX(startPosition);
	}	
}
