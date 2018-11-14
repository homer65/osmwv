package osm.viewer;
import core.*;
public class SimpleKachelPrefetcher extends Thread implements  KachelPrefetcher
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private KachelCache cache = null;
	@Override
	public void setKoordinaten(int x, int y, int z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public void startPrefetch(KachelCache cache)
	{
		this.cache = cache;
		this.start();
	}
	@Override
	public void run() 
	{
		int a = x - 1;
		int b = y - 1;
		cache.get(a,b,z);
		a = x;
		b = y - 1;
		cache.get(a,b,z);
		a = x + 1;
		b = y - 1;
		cache.get(a,b,z);
		a = x + 2;
		b = y - 1;
		cache.get(a,b,z);
		a = x + 3;
		b = y - 1;
		cache.get(a,b,z);
		a = x - 1;
		b = y;
		cache.get(a,b,z);
		a = x - 1;
		b = y + 1;
		cache.get(a,b,z);
		a = x - 1;
		b = y + 2;
		cache.get(a,b,z);
		a = x - 1;
		b = y + 3;
		cache.get(a,b,z);
		a = x;
		b = y + 3;
		cache.get(a,b,z);
		a = x + 1;
		b = y + 3;
		cache.get(a,b,z);
		a = x + 2;
		b = y + 3;
		cache.get(a,b,z);
		a = x + 3;
		b = y + 3;
		cache.get(a,b,z);
		a = x + 3;
		b = y;
		cache.get(a,b,z);
		a = x + 3;
		b = y + 1;
		cache.get(a,b,z);
		a = x + 3;
		b = y + 2;
		cache.get(a,b,z);
	}
}
