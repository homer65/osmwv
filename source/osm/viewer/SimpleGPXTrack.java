package osm.viewer;
import java.io.*;
import core.*;
import java.util.*;
public class SimpleGPXTrack implements GPXTrack
{
	private Util util = Factory.getUtil();
	private FileChooser fc = Factory.getFileChooser();
	private ArrayList<Point> punkte = new ArrayList<Point>(); 
	private ArrayList<String> satz = new ArrayList<String>();
	@Override
	public void addPoint(double lat, double lon) 
	{
		Point punkt = new Point();
		punkt.setLat(lat);
		punkt.setLon(lon);
		punkte.add(punkt);
	}
	@Override
	public void init() 
	{
		punkte = new ArrayList<Point>();
	}
	@Override
	public void removeNearestPoint(double lat, double lon) 
	{
		int n = punkte.size();
		if (n > 0)
		{
			double mind = -1;
			int mini = -1;
			for (int i=0;i<n;i++)
			{
				Point punkt = punkte.get(i);
				double dx = punkt.getLat() - lat;
				double dy = punkt.getLon() - lon;
				double d = (dx * dx) + (dy * dy);
				if (mind < 0)
				{
					mind = d;
					mini = i;
				}
				else
				{
					if (d < mind)
					{
						mind = d;
						mini = i;
					}
				}
			}
			punkte.remove(mini);
		}
	}
	@Override
	public void setName(String name) 
	{
		//
	}
	@Override
	public void readFromFile() 
	{
		Protokol.write("SimpleGPXTrack:readFromFile:not implemented");		
	}
	@Override
	public void writeToFile() 
	{
		buildAusgabe();
		File file = fc.getWriteFile();
		if (file != null)
		{
			try
			{
				Writer writer = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(writer);
				for (int i=0;i<satz.size();i++)
				{
					String x = satz.get(i);
					bw.write(x + "\n");
				}
				bw.close();
			}
			catch (Exception e)
			{
				Protokol.write("SimpleGPXTrack:WriteToFile:Exception:");
				Protokol.write(e.toString());
				Protokol.write(file.getAbsolutePath());
			}
		}
		else
		{
			Protokol.write("SimpleGPXTrack:writeToFile:Null file");
		}
	}
	@Override
	public ArrayList<Point> getPoints() 
	{
		return punkte;
	}
	private void buildAusgabe()
	{
		satz = new ArrayList<String>();
		satz.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		satz.add("<gpx version=\"1.1\" creator=\"OpenStreetMapWebsiteViewer\">");
		for (int i=0;i<punkte.size();i++)
		{
			satz.add(buildWPT(punkte.get(i)));
		}
		satz.add("</gpx>");
	}
	private String buildWPT(Point p)
	{
		double lat = p.getLat();
		double lon = p.getLon();
		String slat = util.getRound4(lat);
		String slon = util.getRound4(lon);
		String erg = "<wpt lat=\"" + slat + "\" lon=\"" + slon + "\">";
		return erg;
	}
}