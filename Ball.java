package finalProject;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -Thomas Arrizza
//Date - 4-19-2016
//Class - 2
//Lab  -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ball extends MovingThing
{
	
	private double vspeed;
	private double hspeed;
	private int width;
	private Color c = Color.BLUE;

	public Ball()
	{
		this(0,0,0);
		hspeed = 0;
	}

	public Ball(int x, int y)
	{
		this(x,y,0);
		hspeed = 0;
	}

	public Ball(int x, int y, int s)
	{
		super(x, y);
		setSpeed(s);
		hspeed = 0;
		
	}
	public Ball(int x, int y, int w, int s)
	{
		super(x, y);
		width = w;
		setSpeed(s);
		hspeed = 0;
		
	}
	public Ball(int x, int y, int w, int s, double hs)
	{
		super(x, y);
		width = w;
		setSpeed(s);
		hspeed = hs;
		
	}

	public void setHSpeed(double s){
		hspeed = s;
	}
	public double getHSpeed()
	{
	   return hspeed;
	}
	
	public void setSpeed(double s)
	{
	   vspeed =s;
	}
	

	public double getSpeed()
	{
	   return vspeed;
	}

	public void setColor(Color c){
		this.c=c;
	}
	public void draw( Graphics window )
	{
		window.setColor(c);
   	window.drawOval(getX(),getY(),width,width);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
