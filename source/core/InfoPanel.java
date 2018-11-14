package core;
import javax.swing.*;
@SuppressWarnings("serial")
public abstract class InfoPanel extends JPanel
{
	public abstract void setKoordinate(Koordinate k);
	// public abstract void setX(int x);
	// public abstract void setY(int y);
	// public abstract void setZoom(int z);
	public abstract void build();
}
