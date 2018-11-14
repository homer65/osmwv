package osm.viewer;
import java.io.File;
import core.*;
public class Main 
{
	public static void main(String[] args) 
	{
		Protokol.write(Parameter.version);
		String homedir = System.getProperty("user.home");
		String s = File.separator;
		Parameter.cachedir = homedir + s + ".osmwvcache" + s;
		Protokol.write("Main:main:cachedir:");
		Protokol.write(Parameter.cachedir);
		File dir = new File(Parameter.cachedir);
		if (!dir.isDirectory())
		{
			Protokol.write("Main:main:will create cachedir");
			dir.mkdir();
		}	
		MainFrame mf = new MainFrame();
		mf.anzeigen();
	}
}
