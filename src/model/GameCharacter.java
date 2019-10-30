package model;

public abstract class GameCharacter {

	protected int charcterPositionX;
	protected int charcterPositionY;
	protected int charcterWidth;
	protected int charcterHeight;

	public GameCharacter(int positionX,int positionY,int height,int width){
		this.charcterPositionX = positionX;
		this.charcterPositionY = positionY;
		this.charcterHeight = height;
		this.charcterWidth = width;	
	}

	public int getPositionX() {
		return charcterPositionX;
	}

	public int getPositionY() {
		return charcterPositionY;
	}

	public int getWidth() {
		return charcterWidth;
	}

	public int getHeigth() {
		return charcterHeight;
	}

	public void setPositionX(int positionX) {
		this.charcterPositionX = positionX;
	}
	public void setPositionY(int positionY) {
		this.charcterPositionY = positionY;
	}

	public void moveOnX(int step) {
		this.charcterPositionX += step;
	}
	public void moveOnY(int step) {
		this.charcterPositionY += step;
	}


}
