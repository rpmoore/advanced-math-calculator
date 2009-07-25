package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoadingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430736201227001798L;

	private Ellipse2D.Double circles[] = null;
	
	public LoadingPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		circles = new Ellipse2D.Double[12];
		
		double theta = Math.PI/6.0;
		double radius = 50.0;
		Ellipse2D.Double temp;		
		for(int i = 0; i < 12; i++)
		{
			temp = new Ellipse2D.Double(Math.cos(theta*i)*radius+100,Math.sin(theta*i)*radius+100,10,10);
			circles[i]=temp;
		}
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i < 12; i++)
		{
			g2d.setColor(Color.GREEN);
			g2d.fill(circles[i]);
		}
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Testing the panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new LoadingPanel(true);
		frame.add(panel);
		panel.setSize(new Dimension(200,200));
		panel.setPreferredSize(new Dimension(200,200));

		frame.pack();
		frame.setVisible(true);
	}
}
