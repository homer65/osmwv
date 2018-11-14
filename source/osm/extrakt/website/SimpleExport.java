package osm.extrakt.website;

import core.Export;

public class SimpleExport implements Export
{
	@Override
	public void close() 
	{
				
	}
	@Override
	public void open() 
	{
	
	}
	@Override
	public void write(String lat, String lon, String url) 
	{
		System.out.println(lat + ":" + lon);
		System.out.println(url);	
	}
}
