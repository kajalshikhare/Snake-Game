package SnakeGame;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	static final int frameWidth=600;
	static final int frameHeight=600;
	static final int unitSize=25;          // how big object is
	static final int numberOfUnits=(frameWidth*frameHeight)/unitSize; // how many objects fit on screen
	static final int delay=70;
	final int x[]=new int[numberOfUnits];// hold all x c0-ordinates
	final int y[]=new int[numberOfUnits];
	int bodyParts=6;
	int applesEaten;
	int appleX; // x position of apple;
	int appleY;
	char direction='R'; // start with right direction 
	boolean running=false;
	Timer timer;
	Random random;
	
	
	GamePanel(){
		random=new Random();
		this.setPreferredSize(new Dimension(frameWidth,frameHeight)); //size to panel
		this.setBackground(Color.black); 
		this.setFocusable(true);
		this.addKeyListener(new MykeyAdapter());
		startGame();
	}
    public void startGame() {
    	newApple();
    	running =true;
    	timer= new Timer(delay,this);
    	timer.start();
    }
   public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   draw(g);
    	
    }
   public void draw(Graphics g) {
	   if(running) {
//	       for(int i=0; i< frameHeight/unitSize; i++) {
//		    g.drawLine(i*unitSize, 0,i*unitSize, frameHeight);
//		    g.drawLine(0, i*unitSize,frameWidth, i*unitSize);
//	     }
	       g.setColor(Color.red);
	       g.fillOval( appleX, appleX,unitSize,unitSize);
	       for(int i=0; i< bodyParts; i++) {
		    if(i==0) {
			   g.setColor(Color.green);
			   g.fillOval(x[i],y[i],unitSize,unitSize);
		    }else {
			   g.setColor(Color.blue);
			   g.fillOval(x[i],y[i],unitSize,unitSize);
		   }
		    g.setColor(Color.RED);
			   g.setFont(new Font("Ink Free", Font.BOLD,40));
			   FontMetrics m = getFontMetrics(g.getFont());
			   g.drawString("Score: "+applesEaten,(frameWidth-m.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
	   }
	   }
	   else {
		   gameOver(g);
	   }
	   
   }
   public void newApple() {
	   appleX=random.nextInt((int)(frameWidth/unitSize))*unitSize;
	   appleY=random.nextInt((int)(frameHeight/unitSize))*unitSize;
	   
   }
   public void move() {//move the snake
	   for(int i=bodyParts; i>0; i--) {
		   x[i]=x[i-1];
		   y[i]=y[i-1];// move snake by 1
	   }
	   switch(direction) {
	   case 'U':
		   y[0]=y[0]-unitSize;
		   break;
	   case 'D':
		   y[0]=y[0]+unitSize;
		   break;
	   case 'L':
		  x[0]=x[0]-unitSize;
		   break;
	   case 'R':
			  x[0]=x[0]+unitSize;
			   break;
	   }
   }
   public void checkApple() {
	   if((x[0]==appleX) && (y[0]==appleY) ) {
		   bodyParts++;
		   applesEaten++;
		   newApple();
	   }
	   
   }
   public void checkCollision() {
	   // checks if head collides with body
	   for(int i=bodyParts; i>0; i--) {
		   if(x[0]==x[i] &&  y[0]==y[i])
		       running =false;
	   }
	   // check if head touches left border
	   if(x[0]<0) {
		   running =false;
	   }
      // check if head touches right border
	   if(x[0]>frameWidth) {
		   running =false;
	   }
	  // check if head touches top border
	   if(y[0]<0) {
		   running =false;
	   }
	   // check if head touches bottom border
	   if(y[0]>frameHeight) {
		   running =false;
	   }
	   if(running ==false) {
		timer.stop();   
	   }
	   
   }
   public void gameOver(Graphics g) {
	   g.setColor(Color.RED);
	   g.setFont(new Font("Ink Free", Font.BOLD,75));
	   FontMetrics m = getFontMetrics(g.getFont());
	   g.drawString("GAME OVER",(frameWidth-m.stringWidth("GAME OVER"))/2, frameHeight/2);
	   g.setFont(new Font("Ink Free", Font.BOLD,40));
	   g.drawString("Score: "+applesEaten,(frameWidth-m.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
   }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
	}
	public class MykeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction='L';	
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction='R';	
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction='U';	
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction='D';	
				}
				break;			
			}
		}
	}
}
