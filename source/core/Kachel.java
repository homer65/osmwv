package core;
public interface Kachel 
{
	public void setX(int i);
	public void setY(int i);
	public void setZ(int i);
	public int getX();
	public int getY();
	public int getZ();
	public void setIcon(Icon icon);
	public Icon getIcon();
	public Icon getIcon(GPXTrack track);
}
