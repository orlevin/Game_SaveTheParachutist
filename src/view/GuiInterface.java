package view;

import java.awt.Graphics;

import model.GameStatus;

public interface GuiInterface {

	public void drawStatus(Graphics g);
	public int getBoardWidth();
	public int getBoardHeigth();
	public int getBoatHeigth();
	public int getBoatWidth();
	public int getAirplaneHeigth();
	public int getAirplaneWidth();
	public int getParachutistHeigth();
	public int getParachutistWidth();
	public void setStatus(GameStatus status);
	public void gameOver();
	
	
	
}
