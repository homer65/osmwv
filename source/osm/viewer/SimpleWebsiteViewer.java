package osm.viewer;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import core.*;
public class SimpleWebsiteViewer extends Thread implements WebsiteViewer
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private int p = 0;
	private int q = 0;
	private double lat = 0;
	private double lon = 0;
	public SimpleWebsiteViewer(int x,int y,int z,int p,int q)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.p = p;
		this.q = q;
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
		//Protokol.write("MainFrame:calculateLatLon:" + lat + ":" + lon);
	}
	public void run()
	{
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
		Protokol.write("SimpleWebsiteViewer:start:API URL:");
		Protokol.write(sUrl);
		try
		{
			URL url = new URL(sUrl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			Reader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			File ausxml = new File("ausxml");
			Protokol.write("SimpleWebsiteViewer:run:ausxml:");
			Protokol.write(ausxml.getAbsolutePath());
			Writer wr = new FileWriter(ausxml);
			String satz = br.readLine();
			while (satz != null)
			{
				wr.write(satz);
				satz = br.readLine();
			}
			wr.close();
			br.close();
			con = url.openConnection();
			in = con.getInputStream();
			XMLToWebsite xtw = Factory.getXMLToWebsite();
			ArrayList<String> websites = xtw.getWebsite(in);
			StringSorter sorter = Factory.getStringSorter();
			websites = sorter.sort(websites);
			File temp = File.createTempFile("osm",".html");
			OutputStream os = new FileOutputStream(temp);
			Protokol.write("SimpleWebsiteViewer:start:TempFile:");
			Protokol.write(temp.getAbsolutePath());
			Writer writer = new OutputStreamWriter(os,"UTF-8");
			writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			writer.write("<html>" + "\n");
			writer.write("<head>" + "\n");
			writer.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">" + "\n");
			writer.write("</head>");
			writer.write("<body>" + "\n");
			String previous = "";
			for (int i=0;i<websites.size();i++)
			{
				String website = websites.get(i);
				if (website.length() > 4)
				{
					String test = website.substring(0,4);
					if (!test.toLowerCase().equals("http"))
					{
						website = "http://" + website;
					}
				}
				if (!previous.equals(website))
				{
					writer.write("<a href=\"" + website + "\">" + website + "</a><br>");
				}
				previous = website;
			}
			writer.write("</body>");
			writer.write("</html>");
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(temp.toURI());
		}
		catch (Exception e)
		{
			Protokol.write("SimpleWebsiteViewer:start:Exception:");
			Protokol.write(e.toString());
		}
	}
}
