package osm.extrakt.website;
public class Main 
{
	public static void main(String[] args) 
	{
		String fileName = args[0];
		Verarbeitung v = new Verarbeitung(fileName);
		v.start();
	}
}
