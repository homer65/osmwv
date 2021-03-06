package core;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
public interface Icon 
{
	public void setSize(int width,int height);
	public Dimension getSize();
	public void buildRandom();
	public void buildFromImage(BufferedImage image);
	public void build9(Icon i00,Icon i01,Icon i02,Icon i10,Icon i11,Icon i12,Icon i20,Icon i21,Icon i22);
	public void build16(Icon i00,Icon i01,Icon i02,Icon i03,Icon i10,Icon i11,Icon i12,Icon i13,Icon i20,Icon i21,Icon i22,Icon i23,Icon i30,Icon i31,Icon i32,Icon i33);
	public Icon clip(int p, int q);
	public Icon clip16(int p,int q);
	public Icon mittelKreuz(Icon icon);
	public Icon mittel16Kreuz(Icon icon);
	public void setPixel(Pixel pixel);
	public Pixel getPixel(int x,int y);
	public BufferedImage getImage();
	public boolean readFromTileServer(int x,int y,int z);
	public Icon copy();
	public void writeToFile(File file);
	public void readFromFile(File file);
	public void umrande(Color c);
	public void paintGPX(GPXTrack track,Koordinate koordinate);
}
