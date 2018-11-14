package core;
import osm.extrakt.website.*;
import osm.viewer.*;
public class Factory 
{
	public static CacheBuilder getCacheBuilder()
	{
		return new SimpleCacheBuilder();
	}
	public static ProgressPanel getProgressPanel()
	{
		return new SimpleProgressPanel();
	}
	public static GPXTrack getGPXTrack()
	{
		return new SimpleGPXTrack();
	}
	public static Util getUtil()
	{
		return new SimpleUtil();
	}
	public static XMLData getXMLData()
	{
		return new SimpleXMLData();
	}
	public static FileChooser getFileChooser()
	{
		return new AdvancedFileChooser();
	}
	public static Persistenz getPersistenz()
	{
		return new SimplePersistenz();
	}
	public static Koordinate getKoordinate()
	{
		return new SimpleKoordinate();
	}
	public static InfoPanel getInfoPanel()
	{
		return new SimpleInfoPanel();
	}
	public static StringSorter getStringSorter()
	{
		return new SimpleStringSorter();
	}
	public static WebsiteViewer getWebsiteViewer(int x,int y,int z,int p, int q)
	{
		return new SimpleWebsiteViewer(x,y,z,p,q);
	}
	public static KachelPrefetcher getKachelPrefetcher()
	{
		return new SimpleKachelPrefetcher();
	}
	public static KachelCache getKachelCache()
	{
		return new FileKachelCache();
	}
	public static Kachel getKachel()
	{
		return new SimpleKachel();
	}
	public static XMLToWebsite getXMLToWebsite()
	{
		return new SimpleXMLToWebsite();
	}
	public static IconPanel getIconPanel()
	{
		return new SimpleIconPanel();
	}
	public static Icon getIcon()
	{
		return new SimpleIcon();
	}
	public static Pixel getPixel()
	{
		return new SimplePixel();
	}
	public static Export getExport()
	{
		return new HTMLExport();
		//return new SimpleExport();
	}
}
