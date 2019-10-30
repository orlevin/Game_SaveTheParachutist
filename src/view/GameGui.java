package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameCharacter;
import model.GameStatus;
import model.Parachutist;

public class GameGui extends JPanel{
	//some final int's to set the images position
	public static final int BOARD_HEIGTH = 600;
	public static final int BOARD_WIDTH = 800;
	private static final int SEA_REFERENCE_POSITION_FRON_BOTTOM = 120;
	private static final int BOAT_REFERENCE_POSITION_FROM_BOTTOM = 150;
	private static final int LIVE_HEADLINE_POSITIN_X = 30;
	private static final int LIVE_HEADLINE_POSITIN_Y = 50;
	private static final int SCORES_HEADLINE_POSITIN_X = 30;
	private static final int SCORES_HEADLINE_POSITIN_Y = 30;
	private static final int LEVEL_HEADLINE_POSITIN_Y = 30;
	private static final int LEVEL_HEADLINE_POSITIN_X = BOARD_WIDTH / 2  ; 
	private static final int gap = 40;
	private static final String BACKGROUND_IMAGE_PATH = "res/background.png";
	private static final String SEA_IMAGE_PATH = "res/sea.png";
	private static final String AIRPLANE_IMAGE_PATH = "res/airplane.png";
	private static final String PARACHUTIST_IMAGE_PATH = "res/parachutistSmall.png"; 
	private  static final String BOAT_IMAGE_PATH = "res/boatSmall.png";
	private static final String TITLE = "Matific - Game Chalenge ";
	
	private ImageIcon backgroundIcon;
	private ImageIcon seaIcon;
	private ImageIcon airplaneIcon;
	private ImageIcon parachutistIcon;
	private ImageIcon boatIcon;
	private UserInput boatController;
	private JFrame jf;
	private int boatPosX ;
	private int boatPosY ;
	private int airplanePosX;
	private int airplanePosY;
	private int seaPos;
	private ArrayList<GameCharacter> characterList;
	private GameStatus status; 
	public GameGui(UserInput pml,GameStatus status){
		//get images
		backgroundIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
		seaIcon = new ImageIcon(SEA_IMAGE_PATH);
		airplaneIcon = new ImageIcon(AIRPLANE_IMAGE_PATH);
		parachutistIcon = new ImageIcon(PARACHUTIST_IMAGE_PATH);
		boatIcon = new ImageIcon(BOAT_IMAGE_PATH);
		boatController =  pml;
		this.status = status;
		// make frame
		jf = new JFrame();
		jf.setTitle(TITLE);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setSize(BOARD_WIDTH, BOARD_HEIGTH);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.add(this);
		jf.addKeyListener(boatController);
		boatPosX = jf.getWidth()/2;
		boatPosY = jf.getHeight()-BOAT_REFERENCE_POSITION_FROM_BOTTOM;
		seaPos = jf.getHeight()-SEA_REFERENCE_POSITION_FRON_BOTTOM;
		characterList = new ArrayList<GameCharacter>();
		jf.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);	
		drawStatus(g);
		drawSea(g);
		drawBoat(g);
		drawAirplne(g);
		drawParachutist(g);
	}

	//drawImages
	private void drawBackground(Graphics g){
		g.drawImage(backgroundIcon.getImage(), 0, 0, this);
	}
	private void drawSea(Graphics g){
		g.drawImage(seaIcon.getImage(), 0, seaPos, this);
	}
	private void drawBoat(Graphics g){
		if(boatPosX < 0)
			boatPosX = 0;
		else if (boatPosX > jf.getWidth())
			boatPosX = jf.getWidth();
		g.drawImage(boatIcon.getImage(),boatPosX, boatPosY, this);
	}

	private void drawAirplne(Graphics g){
		g.drawImage(airplaneIcon.getImage(),airplanePosX, airplanePosY, this);
	}

	private void drawCharacters(Graphics g){
			for (GameCharacter character : characterList) {
				g.drawImage(parachutistIcon.getImage(),parachutist.getPositionX(),parachutist.getPositionY(), this);			
			}
	}


	//statuses functions

	public void setStatus(GameStatus status){
		this.status = status;
	}

	//print statuses on screen 
	private void drawStatus(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 15);
		g.setFont(font);

		// Score status
		g.drawString("Score:", SCORES_HEADLINE_POSITIN_X, SCORES_HEADLINE_POSITIN_Y);
		g.drawString(Integer.toString(status.getPoints()), SCORES_HEADLINE_POSITIN_X +"Score:".length() + Integer.toString(status.getPoints()).length() + gap, SCORES_HEADLINE_POSITIN_Y );

		// Lives status
		g.drawString("Lives:", LIVE_HEADLINE_POSITIN_X, LIVE_HEADLINE_POSITIN_Y);
		g.drawString(Integer.toString(status.getlives()), LIVE_HEADLINE_POSITIN_X + "Lives:".length() +  Integer.toString(status.getlives()).length() + gap, LIVE_HEADLINE_POSITIN_Y );

		// Level status
		g.drawString("Level:", LEVEL_HEADLINE_POSITIN_X , LEVEL_HEADLINE_POSITIN_Y);
		g.drawString(Integer.toString(status.getLevel()), LEVEL_HEADLINE_POSITIN_X + "Level:".length() +  Integer.toString(status.getLevel()).length() + gap, LEVEL_HEADLINE_POSITIN_Y);
	}

	//print Game over on screen
	public void gameOver() {
		Graphics g = this.getGraphics();
		Font gameOverFont = new Font("Arial", Font.BOLD, 50);
		g.setFont(gameOverFont);
		Color color = Color.red;
		while(true){
			color = redBlueSwitchColor(color);	
			g.setColor(color);	
			g.drawString("Game Over :(" , jf.getWidth() /2 - 4*gap , jf.getHeight()/ 2);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//for switch colors in Game over text
	private Color redBlueSwitchColor(Color c){
		if(c == Color.BLUE)
			return Color.RED;
		else 
			return Color.BLUE;
	}

	//board functions
	public int getBoardWidth(){
		return jf.getWidth();
	}

	public int getBoardHeigth(){
		return jf.getHeight();
	}

	//boat functions
	public int getBoatHeigth(){
		return boatIcon.getIconHeight();
	}

	public int getBoatWidth(){
		return boatIcon.getIconWidth();
	}
	public int getBoatStartPosX(){
		return boatPosX;
	}
	public int getBoatStartPosY(){
		return boatPosY;
	}
	public int boatStepX(){
		int step = boatController.moveOnX;
		if(boatPosX == 0 && step < 0)
			return 0;
		else if ((boatPosX+ boatIcon.getIconWidth()) >= jf.getWidth() && step > 0)
			return 0;
		else
			return step;
	}
	public int boatStepY(){
		if(boatPosY == 0)
			return 0;
		else if (boatPosX == jf.getHeight())
			return 0;
		else
			return boatController.moveOnY;
	}

	//airplane functions

	public int getAirplaneHeigth(){
		return airplaneIcon.getIconHeight();
	}

	public int getAirplaneWidth(){
		return airplaneIcon.getIconWidth();
	}


	//parachutist func

	public int getParachutistHeigth(){
		return parachutistIcon.getIconHeight();
	}

	public int getParachutistWidth(){
		return parachutistIcon.getIconWidth();
	}

	public void setParachutList(ArrayList<GameCharacter> pl){
		parachutList = pl;
	}

	
	

}
