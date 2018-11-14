package core;
public interface KachelPrefetcher 
{
	public void setKoordinaten(int x,int y,int z);
	public void startPrefetch(KachelCache cache);
}
