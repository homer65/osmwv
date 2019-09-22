package osm.viewer;
import core.*;
public class SimpleKoordinate implements Koordinate
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private int q = 0;
	private int p = 0;
	private double lat = 0;
	private double lon = 0;
	@Override
	public int getP() 
	{
		return p;
	}
	@Override
	public int getQ() 
	{
		return q;
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
	public void setP(int i) 
	{
		p = i;		
	}
	@Override
	public void setQ(int i) 
	{
		q = i;		
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
	public int getZ()
	{
		return z;
	}
	@Override
	public void setZ(int i) 
	{
		z = i;		
	}
	@Override
	public void set(double lat, double lon, int zoom) 
	{
		double fz = 1.0;
		for (int i=0;i<zoom;i++)
		{
			fz = 2.0 * fz;
		}
		double ytile = (int)Math.floor( (lon + 180) / 360 * fz ) ;
		double xtile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * fz ) ;
		z = zoom;
		x = (int) xtile;
		y = (int) ytile;
		xtile -= (double) x;
		ytile -= (double) y;
		q = (int) (256.0 * xtile);
		p = (int) (256.0 * ytile);
		x--;
		y--;
		System.out.println("SimpleKoordinate:set:");
		System.out.println(x + ":" + y + "::" + q + ":" + p);
	}
	@Override
	public double getLat() 
	{
		calculateLatLon();
	    return lat;
	}
	@Override
	public double getLon() 
	{
		calculateLatLon();
		return lon;
	}
	@Override
	public double getTrueLat() 
	{
		calculateTrueLatLon();
	    return lat;
	}
	@Override
	public double getTrueLon() 
	{
		calculateTrueLatLon();
		return lon;
	}
	public void calculateLatLon()
	{
		//System.out.println("SimpleKoordinate:calculateLatLon:");
		//System.out.println(z + ":" + x + ":" + y + ":" + q + ":" + p);
		double fx = (double) x;
		fx += 1.5;
		double fy = (double) y;
		fy += 1.5;
		double dp = (double) p;
		double dq = (double) q;
		fx += dq / 256.0;
		fy += dp / 256.0;
		double f = fx;
		fx = fy;
		fy = f;
		//System.out.println(z + ":" + fx + ":" + fy);
		double fz = 1.0;
		double pi = Math.PI;
		for (int i=0;i<z;i++)
		{
			fz = 2.0 * fz;
		}
		lon = 360.0 * ((fx)/fz) - 180.0;
		lat = pi - 2.0 * pi * (fy)/fz;
		lat = Math.sinh(lat);
		lat = Math.atan(lat) * 180.0 / pi;
		//System.out.println(lat + ":" + lon);
	}
	public void calculateTrueLatLon()
	{
		double fp = (double) p;
		double fq = (double) q;
		double fx = (double) (y);
		double fy = (double) (x);
		//fx = fx + 1 + (fp / 256);
		//fy = fy + 1 + (fq / 256);
		fx = fx + (fq / 256);
		fy = fy + (fp / 256);
		double fz = 1;
		double pi = Math.PI;
		for (int i=0;i<z;i++)
		{
			fz = 2 * fz;
		}
		lon = 360 * ((fx)/fz) - 180;
		lat = pi - 2 * pi * (fy)/fz;
		lat = Math.sinh(lat);
		lat = Math.atan(lat) * 180 / pi;
	}
}
