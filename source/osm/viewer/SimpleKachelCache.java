package osm.viewer;
import core.*;
public class SimpleKachelCache implements KachelCache
{
	private int size = 200;
	private int pos = 0;
	private Kachel[] cache = new Kachel[size];
	public SimpleKachelCache()
	{
		for (int i=0;i<size;i++)
		{
			cache[i] = Factory.getKachel();
		}
	}
	@Override
	public Kachel get(int x, int y, int z) 
	{
		Kachel erg = null;
		for (int i=0;i<size;i++)
		{
			Kachel test = cache[i];
			boolean matches = true;
			if (test.getX() != x) matches = false;
			if (test.getY() != y) matches = false;
			if (test.getZ() != z) matches = false;
			if (matches)
			{
				push(test);
				erg = test;
				break;
			}
		}
		if (erg == null)
		{
			Icon icon = Factory.getIcon();
			icon.readFromTileServer(x,y,z);
			erg = Factory.getKachel();
			erg.setX(x);
			erg.setY(y);
			erg.setZ(z);
			erg.setIcon(icon);
			push(erg);
		}
		return erg;
	}
	@Override
	public void push(Kachel kachel) 
	{
		cache[pos] = kachel;
		pos++;
		if (pos >= size) pos = 0;
	}
	@Override
	public String getStatistik() 
	{
		String erg = "no Statistik available";
		return erg;
	}	
}
