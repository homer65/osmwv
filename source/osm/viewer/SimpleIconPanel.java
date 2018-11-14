package osm.viewer;
import core.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
public class SimpleIconPanel extends IconPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private ArrayList<PixelListener> alPL = new ArrayList<PixelListener>();
	private Icon icon = null;
	private int width = 0;
	private int height = 0;
	public SimpleIconPanel()
	{
		this.addMouseListener(this);
	}
	public void addListener(PixelListener listener) 
	{
		alPL.add(listener);	
	}
	public void removeListener(PixelListener listener) 
	{
		alPL.remove(listener);
	}
	public void setIcon(Icon icon) 
	{
		this.removeAll();
		Dimension dim = icon.getSize();
		width = dim.width;
		height = dim.height;
		setPreferredSize(new Dimension(width,height));
		this.icon = icon;
		repaint();
	}
	public void paint(Graphics g)
	{
		if (icon != null)
		{
			BufferedImage bi = icon.getImage();
			g.drawImage(bi,0,0,null);
		}
	}
	@Override
	public Icon getIcon() 
	{
		return icon;
	}
	public void mouseClicked(MouseEvent me) 
	{
		int x = me.getX();
		int y = me.getY();
		Pixel pixel = icon.getPixel(x,y);
		for (int i=0;i<alPL.size();i++)
		{
			PixelListener ppl = alPL.get(i);
			ppl.clicked(pixel);
		}
	}
	public void mouseEntered(MouseEvent arg0) 
	{
	
	}
	public void mouseExited(MouseEvent arg0) 
	{
	
	}
	public void mousePressed(MouseEvent arg0) 
	{
				
	}
	public void mouseReleased(MouseEvent arg0)
	{
				
	}
}

