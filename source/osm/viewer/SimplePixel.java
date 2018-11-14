package osm.viewer;
import java.awt.Color;
import core.*;
public class SimplePixel implements Pixel
{
	private int x = 0;
	private int y = 0;
	private Color c = Color.red;
	public Color getColor() 
	{
		return c;
	}
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public void setColor(Color c) 
	{
		this.c = c;
	}
	public void setX(int x) 
	{
		this.x = x;	
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public Pixel copy() 
	{
		Pixel erg = Factory.getPixel();
		erg.setX(x);
		erg.setY(y);
		erg.setColor(c);
		return erg;
	}
	public String toString()
	{
		String erg = x + "." + y + ":" + c.toString();
		return erg;
	}
}
