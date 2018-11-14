package osm.extrakt.website;
import java.io.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import core.Export;
import core.Factory;
public class Verarbeitung 
{
	private Export export = Factory.getExport();
	private boolean inNode = false;
	private String fileName = null;
	private String lat = "";
	private String lon = "";
	public Verarbeitung(String s)
	{
		fileName = s;
	}
	public void start()
	{
		File file = new File(fileName);
		if (!file.canRead())
		{
			System.out.println("Verarbeitung:start:Kann File nicht lesen:");
			System.out.println(fileName);
			System.exit(12);
		}
		try
		{
			export.open();
			InputStream in = new FileInputStream(file);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader parser = factory.createXMLStreamReader(in);
			while (parser.hasNext())
			{
				switch (parser.getEventType())
				{
					case XMLStreamConstants.START_ELEMENT:
						start_element(parser);
						break;
					case XMLStreamConstants.END_ELEMENT:
						end_element(parser);
						break;
				}
				parser.next();
			}
			parser.close();
			in.close();
			export.close();
		}
		catch (Exception e)
		{
			System.out.println("");
			System.out.println(e.toString());
			System.exit(12);
		}
	}
	public void start_element(XMLStreamReader parser)
	{
		String ln = parser.getLocalName();
		if (ln.equals("node"))
		{
			inNode = true;
			node(parser);
		}
		if (ln.equals("tag")) tag(parser);
	}
	public void end_element(XMLStreamReader parser)
	{
		String ln = parser.getLocalName();
		if (ln.equals("node")) inNode = false;
	}
	public void node(XMLStreamReader parser)
	{
		int n = parser.getAttributeCount();
		for (int i=0;i<n;i++)
		{
			String aname = parser.getAttributeLocalName(i);
			String avalue = parser.getAttributeValue(i);
			if (aname.equals("lon")) lon = avalue;
			if (aname.equals("lat")) lat = avalue;
		}
	}
	public void tag(XMLStreamReader parser)
	{

		if (inNode)
		{
			String k = "";
			String v = "";
			int n = parser.getAttributeCount();
			for (int i=0;i<n;i++)
			{
				String aname = parser.getAttributeLocalName(i);
				String avalue = parser.getAttributeValue(i);
				if (aname.equals("k")) k = avalue;
				if (aname.equals("v")) v = avalue;
			}
			if (k.toLowerCase().equals("website"))
			{
				export.write(lat,lon,v);
			}
		}
	}
}
