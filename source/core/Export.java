package core;
public interface Export 
{
	public void open();
	public void write(String lat,String lon,String url);
	public void close();
}
