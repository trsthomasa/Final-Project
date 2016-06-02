package finalProject;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -Thomas Arrizza
//Date - 4-19-2016
//Class - 2
//Lab  -
import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing
{
	private int xPos;
	private int yPos;

	public MovingThing()
	{
		xPos = 100;
		yPos = 100;
	}

	public MovingThing(int x, int y)
	{
		xPos = x;
		yPos = y;		
	}

	public void setPos( int x, int y)
	{
		setX(x);
		setY(y);	
	}


	public void setX(int x)
	{
		xPos = x;
	}


	public void setY(int y)
	{
		yPos = y;
	}

	public int getX()
	{
		return xPos;
	}


	public int getY()
	{
		return yPos;
	}

	public abstract void setSpeed( double s );
	public abstract double getSpeed();
	public abstract void setHSpeed( double s );
	public abstract double getHSpeed();
	
	public abstract void draw(Graphics window);
	public void leftRight(){
		setX((int)(getX()+getHSpeed()));
	}
	public void drop()
	{
		
		      
		
		      setY((int)(getY()+getSpeed()));
		
	}

	public String toString()
	{
		String out ="";
		
		out+="x = " + getX()+"\ny = "+getY()+"\nspeed = " + getSpeed();
		
		return out;
	}
}