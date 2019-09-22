package core;
public interface KachelCache 
{
	public void push(Kachel kachel);
	public Kachel get(int x,int y,int z);
	public String getStatistik();
	public void resetRAM();
}
