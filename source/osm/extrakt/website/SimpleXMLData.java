package osm.extrakt.website;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import core.*;
public class SimpleXMLData implements XMLData
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private int p = 0;
	private int q = 0;
	private double lat = 0;
	private double lon = 0;
	@Override
	public ArrayList<String> getXML(Koordinate koordinate) 
	{
		ArrayList<String> erg = new ArrayList<String>();
		x = koordinate.getX();
		y = koordinate.getY();
		z = koordinate.getZ();
		p = koordinate.getP();
		q = koordinate.getQ();
		double delta = Parameter.delta;
		calculateLatLon();
		double minlat = lat - delta;
		double maxlat = lat + delta;
		double minlon = lon - delta;
		double maxlon = lon + delta;
		String mila = "" + minlat;
		String mala = "" + maxlat;
		String milo = "" + minlon;
		String malo = "" + maxlon;
		if (mila.length() > 6) mila = mila.substring(0,6); 
		if (mala.length() > 6) mala = mala.substring(0,6); 
		if (milo.length() > 6) milo = milo.substring(0,6); 
		if (malo.length() > 6) malo = malo.substring(0,6); 
		String sUrl = "https://api.openstreetmap.org/api/0.6/map?bbox=";
		sUrl+=milo + "," + mila + "," + malo + "," + mala;
		Protokol.write("SimpleXMLData:getXML:API URL:");
		Protokol.write(sUrl);
		try
		{
			URL url = new URL(sUrl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String satz = br.readLine();
			while (satz != null)
			{
				erg.add(satz);
				satz = br.readLine();
			}
			br.close();
		}
		catch (Exception e)
		{
			Protokol.write("SimpleXMLData:getXML:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	private void calculateLatLon()
	{
		double fp = (double) p;
		double fq = (double) q;
		double fx = (double) y;
		double fy = (double) x;
		fx = fx + 1 + (fp / 256);
		fy = fy + 1 + (fq / 256);
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
