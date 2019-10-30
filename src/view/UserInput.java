package view;
import java.awt.event.KeyAdapter;

public abstract class UserInput extends KeyAdapter {	
	protected int moveOnX;
	protected int moveOnY;
	
	//how match pixels the boat will move for any press
	protected int stepSize;
	
	public UserInput(){
		moveOnX = 0;
		moveOnY = 0;
		this.stepSize = 3;
	}
	public void setStepSize(int size){
		stepSize = size;
	}
}
	

