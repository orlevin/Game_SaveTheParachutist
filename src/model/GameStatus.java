package model;

public class GameStatus {

	private int points;
	private int lives;
	private int level;
	private boolean levelUp;

	public GameStatus(){
		points = 0;
		this.lives = 3;
		level = 1;
		levelUp = false;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getlives() {
		return lives;
	}

	public void setlives(int disqualifications) {
		this.lives = disqualifications;
	}

	public void addPoint(){
		points++;
	}

	public void addlives(){
		lives++;
	}

	public void subtractPoint(){
		points--;
	}

	public void subtractlives(){
		lives--;
	}

	public void setLevelUp(boolean up){
		levelUp = up;
	}
	public boolean getLevelUP(){
		return levelUp;
	}
	public void setLevel(int level){
		this.level = level;
	}

	public void increaseLevel(){
		this.level++;
	}
	public int getLevel(){
		return level;
	}
}
