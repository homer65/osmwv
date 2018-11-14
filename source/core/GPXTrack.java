package core;
import java.util.*;
public interface GPXTrack 
{
	public void init();
	public void setName(String name);
	public void addPoint(double lat,double lon);
	public void removeNearestPoint(double lat,double lon);
	public void writeToFile();
	public void readFromFile();
	public ArrayList<Point> getPoints();
}
