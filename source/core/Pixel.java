package core;
import java.awt.*;
public interface Pixel 
{
	public void setX(int x);
	public void setY(int y);
	public void setColor(Color c);
	public int getX();
	public int getY();
	public Color getColor();
	public Pixel copy();
	public String toString();
}
