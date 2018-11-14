package osm.viewer;
import java.util.prefs.Preferences;
import core.*;
public class SimplePersistenz implements Persistenz
{
	private Preferences prefs = Preferences.userRoot();
	@Override
	public Koordinate getStartKoordinate() 
	{
		int x = 20;
		int y = 32;
		int z = 6;
		int q = 145;
		int p = 200;
		try
		{
			x = prefs.getInt("OpenStreetMapWebsiteViewerKoordinateX",20);
			y = prefs.getInt("OpenStreetMapWebsiteViewerKoordinateY",32);
			z = prefs.getInt("OpenStreetMapWebsiteViewerKoordinateZ",6);
			q = prefs.getInt("OpenStreetMapWebsiteViewerKoordinateQ",145);
			p = prefs.getInt("OpenStreetMapWebsiteViewerKoordinateP",200);
		}
		catch (Exception e)
		{
			x = 20;
			y = 32;
			z = 6;
			q = 145;
			p = 200;
		}
		Koordinate koordinate = Factory.getKoordinate();
		koordinate.setX(x);
		koordinate.setY(y);
		koordinate.setZ(z);
		koordinate.setQ(q);
		koordinate.setP(p);
		return koordinate;
	}
	@Override
	public void storeStartKoordinate(Koordinate koordinate) 
	{
		int x = koordinate.getX();
		int y = koordinate.getY();
		int p = koordinate.getP();
		int q = koordinate.getQ();
		int z = koordinate.getZ();
		prefs.putInt("OpenStreetMapWebsiteViewerKoordinateX",x);
		prefs.putInt("OpenStreetMapWebsiteViewerKoordinateY",y);
		prefs.putInt("OpenStreetMapWebsiteViewerKoordinateZ",z);
		prefs.putInt("OpenStreetMapWebsiteViewerKoordinateQ",q);
		prefs.putInt("OpenStreetMapWebsiteViewerKoordinateP",p);
	}
}
