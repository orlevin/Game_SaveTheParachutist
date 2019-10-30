package control;

import model.Airplane;
import model.Boat;
import model.GameModel;
import model.GameStatus;
import view.GameGui;
import view.ArrowsUserInput;
import view.UserInput;

public class GameCntroller  implements Runnable{	
	 
	private GameGui board;
	private GameModel controller;
	private Thread gameThread;
	private UserInput playerWayControl = new ArrowsUserInput(); 
	private GameStatus status;
	private GameStates gameState; 

	public GameCntroller(){
		status = new GameStatus();
		board = new GameGui(playerWayControl , status); 
		Boat boat = new Boat(board.getBoatStartPosX() , board.getBoatStartPosY(), board.getBoatHeigth(), board.getBoardWidth());
		Airplane airplane = new Airplane(GameGui.BOARD_WIDTH, 0, board.getAirplaneHeigth(), board.getAirplaneWidth()); 
		controller = new GameModel(boat,airplane,status);
	}

	public void start(){
		if (gameThread == null){
			gameThread = new Thread(this);
			gameState = gameState.PLAY;
			gameThread.start();
		}
	}

	private void isGameEnded(){
		if(status.getlives() <= 0 )
			gameState = gameState.ENDGAME;
		else 
			gameState = gameState.PLAY;	
	}

	@Override
	public void run() {	
		while (gameState == gameState.PLAY) {

			//The model layer
			controller.moveBoatX(board.boatStepX());
			controller.moveAirplane();
			controller.moveParachutists();
			controller.makeParachutist(board.getParachutistHeigth(), board.getParachutistWidth());
			controller.statusUpdata(status);
			isGameEnded();

			//The view layer
			board.setStatus(status);
			board.moveBoat(controller.getBoatX(),controller.getBoatY());
			board.moveAirplane(controller.getAirplaneX(),controller.getgetAirplaneY());
			board.setParachutList(controller.getParachutList());
			board.repaint();			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
		board.gameOver();
	}

	public static void main(String[] args) {
		GameCntroller b = new GameCntroller();
		b.start();
	}
}















