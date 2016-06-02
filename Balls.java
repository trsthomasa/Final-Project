package finalProject;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -Thomas Arrizza
//Date - 4-19-2016
//Class - 2
//Lab  -
import javax.swing.JFrame;
import java.awt.Component;

public class Balls extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public Balls()
	{
		super("Ball Pit");
		setSize(WIDTH,HEIGHT);

		Space theGame = new Space();
		((Component)theGame).setFocusable(true);
		
		getContentPane().add(theGame);

		
		setVisible(true);
	}

	public static void main( String args[] )
	{
		Balls run = new Balls();
	}
}