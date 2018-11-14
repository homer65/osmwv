package osm.extrakt.website;
import java.io.*;

import core.Export;
public class HTMLExport implements Export
{
	private File file = new File("osm.html");
	private BufferedWriter bw = null;
	private int anzahl = 0;
	private int anz001 = 0;
	@Override
	public void close() 
	{
		try
		{
			bw.write("</body>" + "\n");
			bw.write("</html>" + "\n");
			bw.close();
			System.out.println("HTMLExport:close: " + anzahl + " URL exported");
		}
		catch (Exception e)
		{
			System.out.println("HTMLExport:close:Exception:");
			System.out.println(e.toString());
			System.exit(12);
		}
	}
	@Override
	public void open() 
	{
		try
		{
			Writer writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			bw.write("<html>" + "\n");
			bw.write("<body>" + "\n");
		}
		catch (Exception e)
		{
			System.out.println("HTMLExport:open:Exception:");
			System.out.println(e.toString());
			System.exit(12);
		}
	}
	@Override
	public void write(String lat, String lon, String url) 
	{
		try
		{
			bw.write("<a href=\"");
			bw.write(url);
			bw.write("\">");
			bw.write(url);
			bw.write("</a><br>" + "\n");
			anzahl++;
			anz001++;
			if (anz001 > 1000)
			{
				anz001 = 0;
				System.out.println("HTMLExport:write: " + anzahl + " URL exported");
			}
		}
		catch (Exception e)
		{
			System.out.println("HTMLExport:write:Exception:");
			System.out.println(e.toString());
			System.exit(12);
		}
	}
}
