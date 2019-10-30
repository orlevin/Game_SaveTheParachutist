package view;

import java.awt.event.KeyEvent;

public class ArrowsUserInput extends UserInput {
	
	@Override
	public void keyPressed(KeyEvent e){
		// TODO Auto-generated method stub
		super.keyPressed(e);
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
			moveOnX = -stepSize;
		}
		if (key == KeyEvent.VK_RIGHT){
			moveOnX = stepSize;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			moveOnX = 0;
		}
	}
}
