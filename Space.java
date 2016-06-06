package finalProject;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -Thomas Arrizza
//Date - 4-19-2016
//Class - 2
//Lab  -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import static java.lang.Character.*;

import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import unit_17.Ship;

public class Space extends Canvas implements MouseListener, Runnable
{
	

	// uncomment once you are ready for this part
	private boolean gravtoggle = true;
	private boolean temptoggle = false;
	private boolean stillball = false;
	
	 private double gravity = .1;
	 private double resistance = .9;
	private int size = 50;
	private ArrayList<Ball> ball;
	private boolean[] keys;
	private BufferedImage back;
	private int time=0;
	
	private boolean titlecheck=true;
	
	public Space()
	{
		setBackground(Color.black);

		
		
		//instantiate other stuff
		addMouseListener(this);
		ball = new ArrayList<Ball>();
		//ball.add(new Ball(100,100,size,0,Math.random()*7));
		
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		graphToBack.setColor(Color.GREEN);
		
		graphToBack.drawString("Gravity ", 5, 70 );
		graphToBack.drawRect(60,60,20,20);
		graphToBack.setColor(Color.PINK);
		graphToBack.drawString("Speed", 5, 150 );
		graphToBack.drawRect(60,140,20,20);
		
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("Motion ", 5, 230 );
		graphToBack.drawRect(60,220,20,20);
		
		graphToBack.setColor(Color.WHITE);
		graphToBack.drawString("Freeze ", 5, 310 );
		graphToBack.drawRect(60,300,20,20);
		
		if(time<1000)
		time++;
		if(titlecheck==false){
			for(int i = 0 ; i<ball.size();i++){
				if(temptoggle==true){
					int speed = (int)
							(Math.sqrt(Math.pow(ball.get(i).getSpeed(),2) +
							Math.pow(ball.get(i).getHSpeed(),2)));
					speed= speed*50;
					if(speed>255)
						speed=255;
					Color color = new Color(speed,0,255-speed);
					ball.get(i).setColor(color);
				}else{
					ball.get(i).setColor(Color.BLUE);
				}
				ball.get(i).draw(graphToBack);
			}
		
		}else if (titlecheck == true){
			if(time<600){
				graphToBack.drawString("Welcome to my game", 300, 300 );
				graphToBack.drawString("click to add more balls", 300, 315 );
				graphToBack.drawString("by Thomas Arrizza ", 300, 330 );
			}else{
				titlecheck=false;
			}
			
		}

		for(int i = 0 ; i<ball.size();i++){
			if(gravtoggle==true)
			ball.get(i).setSpeed((ball.get(i).getSpeed()+gravity));
			ball.get(i).drop();
			ball.get(i).leftRight();
			if(ball.get(i).getY()<0){
				
				ball.get(i).setSpeed((ball.get(i).getSpeed()*-1));
				ball.get(i).setY(1);
				
			}
			if(ball.get(i).getY()>=575-size){
				ball.get(i).setY(575-size);
				ball.get(i).setSpeed((ball.get(i).getSpeed()*-1)*resistance);
				
			}	
			if(ball.get(i).getX()>800-size){
				ball.get(i).setX(800-size-2);
				ball.get(i).setHSpeed((ball.get(i).getHSpeed()*-1));
			}
			if(ball.get(i).getX()<0 ){
				
				ball.get(i).setHSpeed((ball.get(i).getHSpeed()*-1));
				ball.get(i).setX(1);
			}
			
			
			ball.get(i).setHSpeed((ball.get(i).getHSpeed()*.9997));
			collide();
			}
			

		
		
		twoDGraph.drawImage(back, null, 0, 0);
	}


	void collide() {
		
	    for (int i = 0; i < ball.size(); i++) {
	    for(int j = i; j< ball.size();j++){	
	    	
	      float dx = ball.get(j).getX() - ball.get(i).getX();
	      float dy = ball.get(j).getY() - ball.get(i).getY();	//gets change in velocity between two balls
	      double distance = Math.sqrt(dx*dx + dy*dy);
	      float minDist =size;
	      if (distance < minDist) { //checks that they hit
	        double angle = Math.atan2(dy, dx);
	        double targetX = ball.get(i).getX() + Math.cos(angle) * minDist; //picks new angle
	        double targetY = ball.get(i).getY() + Math.sin(angle) * minDist;
	        double ax = (targetX - ball.get(j).getX())*.05;	//chooses new x and y position
	        double ay = (targetY - ball.get(j).getY())*.05;
	        
	        ball.get(i).setHSpeed(ball.get(i).getHSpeed()-ax);	// makes the new coords a velocity
	        ball.get(i).setSpeed(ball.get(i).getSpeed()-ay);
	       
	        ball.get(j).setHSpeed(ball.get(j).getHSpeed()+ax);	// sets the other ball on the opposite path
	        ball.get(j).setSpeed(ball.get(j).getSpeed()+ay);
	       
	      }
	    }   
	    }
	  }
	
	
	
	public void mouseClicked(MouseEvent e){
		if(e.getX()<80 && e.getX()>60 && e.getY()<80 && e.getY()>60){
			
			if(gravtoggle==true){
				gravtoggle = false;
			}else{
				gravtoggle = true;
			}
			
		}else if(e.getX()<80 && e.getX()>60 && e.getY()<160 && e.getY()>140){
			if(temptoggle==true){
				temptoggle = false;
			}else{
				temptoggle = true;
			}
		}else if(e.getX()<80 && e.getX()>60 && e.getY()<240 && e.getY()>220){
			//System.out.println("test");
			if(stillball==true){
				stillball = false;
			}else{
				stillball = true;
			}
		}else if(e.getX()<80 && e.getX()>60 && e.getY()<320 && e.getY()>300){
			//System.out.println("test");
			for(int i = 0 ; i<ball.size();i++){
				ball.get(i).setHSpeed(0);
				ball.get(i).setSpeed(0);
			}
		}else{
			if(stillball==false)
			ball.add(new Ball(e.getX(),e.getY(),size,(int)(Math.random()*7*
					Math.pow(-1, (int)(Math.random()*10)))
							,(int)(Math.random()*7*Math.pow(-1, (int)(Math.random()*10)))));
			else
				ball.add(new Ball(e.getX(),e.getY(),size,0));
		}
		
		
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}


}

