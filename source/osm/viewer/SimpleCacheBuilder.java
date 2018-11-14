package osm.viewer;
import core.*;
public class SimpleCacheBuilder extends Thread implements CacheBuilder
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private ProgressPanel bar = null;
	private KachelCache cache = Factory.getKachelCache();
	@Override
	public void buildCache(int x,int y,int z,ProgressPanel bar) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.bar = bar;
		this.start();
	}
	public void run()
	{
		Protokol.write("SimpleCacheBuilder:run:Anfang");
		int anz = 0;
		int num = 1;
		for (int iz=z;iz<18;iz++)
		{
			for (int ix=0;ix<num;ix++)
			{
				for (int iy=0;iy<num;iy++)
				{
					int px = x + ix;
					int py = y + iy;
					try
					{
						cache.get(px,py,iz);
						anz++;
						bar.setValue(anz);
						Thread.sleep(5);
					}
					catch (Exception e)
					{
						Protokol.write("SimpleCacheBuilder:run:Exception:");
						Protokol.write(e.toString());
					}
				}
			}
			x = 2 * x;
			y = 2 * y;
			num = num * 2;
		}
		bar.close();	
		Protokol.write("SimpleCacheBuilder:run:Ende");
	}
}
