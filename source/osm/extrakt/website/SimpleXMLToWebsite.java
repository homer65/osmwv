package osm.extrakt.website;
import java.io.*;
import java.util.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import core.*;
public class SimpleXMLToWebsite implements XMLToWebsite 
{
	private ArrayList<String> erg = new ArrayList<String>();
	public ArrayList<String> getWebsite(InputStream in)
	{
		Protokol.write("SimpleXMLToWebsite:getWebsite:start");
		try
		{
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
		}
		catch (Exception e)
		{
			System.out.println("SimpleXMLToWebsite:getWebsite:Exception:");
			System.out.println(e.toString());
		}
		return erg;
	}
	public void start_element(XMLStreamReader parser)
	{
		String ln = parser.getLocalName();
		if (ln.equals("node"))
		{
			node(parser);
		}
		if (ln.equals("tag")) tag(parser);
	}
	public void end_element(XMLStreamReader parser)
	{
		
	}
	public void node(XMLStreamReader parser)
	{

	}
	public void tag(XMLStreamReader parser)
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
			erg.add(v);
		}
		if (k.toLowerCase().equals("url"))
		{
			erg.add(v);
		}
		if (k.toLowerCase().equals("link"))
		{
			erg.add(v);
		}
	}
}
