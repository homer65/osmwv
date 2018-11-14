package core;
import javax.swing.*;
public abstract class IconPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public abstract void setIcon(Icon icon);
	public abstract Icon getIcon();
	public abstract void addListener(PixelListener listener);
	public abstract void removeListener(PixelListener listener);
}

