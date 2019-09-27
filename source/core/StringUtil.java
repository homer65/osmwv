package core;
public class StringUtil 
{
	public static Double toDouble(String s)
	{
		Double erg = 0.0;
		try
		{
			erg = Double.parseDouble(s);
		}
		catch (Exception e)
		{
			System.out.println("StringUtil:toDouble:Exception:" + s);
			System.out.println(e.toString());
		}
		return erg;
	}
}
