package osm.viewer;
import core.*;
import java.text.Collator;
import java.util.*;
public class SimpleStringSorter implements StringSorter,Comparator<String>
{
	private Collator collator = Collator.getInstance();
	@Override
	public ArrayList<String> sort(ArrayList<String> al) 
	{
		Collections.sort(al,this);
		return al;
	}
	@Override
	public int compare(String s1, String s2) 
	{
		return collator.compare(s1,s2);
	}
}
