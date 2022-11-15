package SnakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	 
	 GameFrame(){
		 this.add(new GamePanel());
		 this.setTitle("SNAKE GAME");
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setResizable(false);
		 this.pack(); // add components in jframe take jrame and fit 
	     this.setVisible(true); 
	     this.setLocationRelativeTo(null); //frame at middle
	 }
	 
	 
}
