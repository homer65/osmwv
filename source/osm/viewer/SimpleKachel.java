package osm.viewer;
import core.*;

import java.awt.Color;
import java.util.*;
public class SimpleKachel implements Kachel
{
	private int x = -1;
	private int y = -1;
	private int z = -1;
	private Icon icon = null;
	private Koordinate k1 = Factory.getKoordinate();
	private Koordinate k2 = Factory.getKoordinate();
	private double klatmin = 0;
	private double klatmax = 0;
	private double klonmin = 0;
	private double klonmax = 0;
	private double plat = 0;
	private double plon = 0;
	@Override
	public Icon getIcon() 
	{
		return icon;
	}
	public Icon getIcon(GPXTrack track)
	{
		if (track != null)
		{
			ArrayList<Point> punkte = track.getPoints();
			for (int i=0;i<punkte.size();i++)
			{
				Point p = punkte.get(i);
				writePointOnIcon(p);
			}
		}
		return icon; // Noch nicht fertig
	}
	private void setPixelOnIcon(int kp,int kq)
	{
		Pixel pixel = Factory.getPixel();
		pixel.setX(kp);
		pixel.setY(kq);
		pixel.setColor(Color.CYAN);
		icon.setPixel(pixel);
	}
	private void writePointOnIcon(Point p)
	{
		if (isOnKachel(p))
		{
			double dlat = ((plat - klatmin) / (klatmax - klatmin)) * 256;
			double dlon = ((plon - klonmin) / (klonmax -klonmin)) * 256;
			int kp = (int) dlon;
			int kq = (int) dlat;
			kq = 255 - kq;
			for (int i=0;i<7;i++)
			{
				for (int j=0;j<7;j++)
				{
					int kpi = kp + i;
					int kqj = kq + j;
					if (kpi < 256)
					{
						if (kqj < 256)
						{
							setPixelOnIcon(kpi,kqj);
						}
					}
				}
			}
		}
	}
	private boolean isOnKachel(Point p) 
	{
		plat = p.getLat();
		plon = p.getLon();
		boolean erg = true;
		k1 = Factory.getKoordinate();
		k1.setX(x);
		k1.setY(y);
		k1.setZ(z);
		k1.setP(0);
		k1.setQ(0);
		klatmax = k1.getTrueLat();
		klonmin = k1.getTrueLon();
		if (plat > klatmax) erg = false;
		if (plon < klonmin) erg = false;
		k2 = Factory.getKoordinate();
		k2.setX(x);
		k2.setY(y);
		k2.setZ(z);
		k2.setP(255);
		k2.setQ(255);
		klatmin = k2.getTrueLat();
		klonmax = k2.getTrueLon();
		if (plat < klatmin) erg = false;
		if (plon > klonmax) erg = false;
		return erg;
	}
	@Override
	public int getX() 
	{
		return x;
	}
	@Override
	public int getY() 
	{
		return y;
	}
	@Override
	public int getZ() 
	{
		return z;
	}
	@Override
	public void setIcon(Icon icon) 
	{
		this.icon = icon;		
	}
	@Override
	public void setX(int i) 
	{
		x = i;
	}
	@Override
	public void setY(int i) 
	{
		y = i;		
	}
	@Override
	public void setZ(int i) 
	{
		z = i;		
	}
}
