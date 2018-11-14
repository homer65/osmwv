package osm.viewer;
import core.*;
public class SimpleUtil implements Util
{
	@Override
	public String getRound2(double d) 
	{
		double x = 100 * d;
		long l = Math.round(x);
		x = (double) l;
		x = x / 100;
		String erg = "" + x;
		return erg;
	}	
	@Override
	public String getRound3(double d) 
	{
		double x = 1000 * d;
		long l = Math.round(x);
		x = (double) l;
		x = x / 1000;
		String erg = "" + x;
		return erg;
	}	
	@Override
	public String getRound4(double d) 
	{
		double x = 10000 * d;
		long l = Math.round(x);
		x = (double) l;
		x = x / 10000;
		String erg = "" + x;
		return erg;
	}	
}
