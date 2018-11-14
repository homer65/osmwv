package osm.viewer;
import core.*;
import java.io.*;
public class FileKachelCache implements KachelCache
{
	private int size = 200;
	private int pos = 0;
	private Kachel[] cache = new Kachel[size];
	private String cachedir = Parameter.cachedir;
	public FileKachelCache()
	{
		for (int i=0;i<size;i++)
		{
			cache[i] = Factory.getKachel();
		}
	}
	@Override
	public Kachel get(int x, int y, int z) 
	{
		Parameter.getKachel++;
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
				Parameter.getKachelFromRam++;
				break;
			}
		}
		if (erg == null)
		{
			File kachelfile = new File(cachedir + x + "-" + y + "-" + z + "-kachel.png");
			if (kachelfile.canRead())
			{
				Icon icon = Factory.getIcon();
				icon.readFromFile(kachelfile);
				erg = Factory.getKachel();
				erg.setX(x);
				erg.setY(y);
				erg.setZ(z);
				erg.setIcon(icon);
				Parameter.getKachelFromFile++;
			}
			else
			{
				Icon icon = Factory.getIcon();
				icon.readFromTileServer(x,y,z);
				icon.writeToFile(kachelfile);
				erg = Factory.getKachel();
				erg.setX(x);
				erg.setY(y);
				erg.setZ(z);
				erg.setIcon(icon);
				Parameter.getKachelFromServer++;
			}
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
		String erg = "Kachel read Total : " + Parameter.getKachel + "\n";
		      erg += "Kachel from File  : " + Parameter.getKachelFromFile + "\n";
		      erg += "Kachel from RAM   : " + Parameter.getKachelFromRam + "\n";
		      erg += "Kachel from Server: " + Parameter.getKachelFromServer + "\n";
		return erg;
	}	
}
