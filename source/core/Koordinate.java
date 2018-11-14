package core;
public interface Koordinate 
{
	public void setX(int i);
	public void setY(int i);
	public void setQ(int i);
	public void setP(int i);
	public void setZ(int i);
	public int getX();
	public int getY();
	public int getQ();
	public int getP();
	public int getZ();
	public void set(double lat,double lon,int zoom);
	public double getLat();
	public double getLon();
	public double getTrueLat();
	public double getTrueLon();
	public void calculateLatLon();
	public void calculateTrueLatLon();
}
