package ua.ghost.guitamagochi.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MyProgressBar extends JComponent {
	
	
	private int value=100;
	
	public MyProgressBar(){
		
		this.setBackground(Color.cyan);
	}
	
	@Override
	public void paint(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.green);
		g.fillRect(0, 0, this.getWidth()/100*value , this.getHeight());
		
		g.setColor(Color.black);
		g.drawString(" "+value+" / "+(100), 0, this.getHeight()-5);
		
		super.paint(g);
	}
	
	
	public void setValue(int newValue){
		if(newValue>100) newValue=100;
		if(newValue<0) newValue=0;
		value = newValue;
		this.repaint();
		
	}

}
