package osm.viewer;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import core.*;
public class SimpleIcon implements Icon
{
	private int width = 256;
	private int height = 256;
	private int[][] pixel = null;
	public void buildRandom()
	{
		pixel = new int[width][height];
		int c = 0;
		for (int i=0;i<width;i++)
		{
			for (int j=0;j<height;j++)
			{
				pixel[i][j] = c;
			}
		}
	}
	public void umrande(Color c)
	{
		int offx = 0;
		int offy = 0;
		for (int i=0;i<256;i++)
		{
			pixel[offx+i][offy] = c.getRGB();
		}
		offx = 0;
		offy = 0;
		for (int i=0;i<256;i++)
		{
			pixel[offx][offy+i] = c.getRGB();
		}
		offx = 255;
		offy = 0;
		for (int i=0;i<256;i++)
		{
			pixel[offx][offy+i] = c.getRGB();
		}
		offx = 0;
		offy = 255;
		for (int i=0;i<256;i++)
		{
			pixel[offx+i][offy] = c.getRGB();
		}
	}
	public void writeToFile(File file) 
	{
		if (true)
		{
			try
			{
				ImageIO.write(getImage(),"PNG",file);
			}
			catch (Exception e)
			{
				Protokol.write("SimpleIcon:writeToFile:Exception:");
				Protokol.write(file.getAbsolutePath());
				Protokol.write(e.toString());
			}
		}
	}
	public Icon clip(int p,int q)
	{
		SimpleIcon erg = new SimpleIcon();
		erg.width = 512;
		erg.height = 512;
		erg.pixel = new int[512][512];
		for (int i=0;i<512;i++)
		{
			for (int j=0;j<512;j++)
			{
				erg.pixel[i][j] = this.pixel[i+p][j+q];
			}
		}
		return erg;
	}
	public Icon mittelKreuz(Icon icon)
	{
		Color rot = new Color(255,0,0);
		SimpleIcon erg = new SimpleIcon();
		erg.width = 512;
		erg.height = 512;
		erg.pixel = new int[512][512];
		for (int i=0;i<512;i++)
		{
			for (int j=0;j<512;j++)
			{
				erg.pixel[i][j] = icon.getPixel(i,j).getColor().getRGB();
				if (i==256)
				{
					if (j>231 && j<281)
					{
						erg.pixel[i][j] = rot.getRGB();
					}
				}
				if (j==256) 
				{
					if (i>231 && i<281)
					{
						erg.pixel[i][j] = rot.getRGB();
					}
				}
			}
		}
		return erg;
	}
	public void build9(Icon i00,Icon i01,Icon i02,Icon i10,Icon i11,Icon i12,Icon i20,Icon i21,Icon i22)
	{
		width = 3 * 256;
		height = 3 * 256;
		pixel = new int[width][height];
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i][j] = i00.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i][j+256] = i10.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i][j+512] = i20.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+256][j] = i01.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+256][j+256] = i11.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+256][j+512] = i21.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+512][j] = i02.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+512][j+256] = i12.getPixel(i,j).getColor().getRGB();
			}
		}
		for (int i=0;i<256;i++)
		{
			for (int j=0;j<256;j++)
			{
				pixel[i+512][j+512] = i22.getPixel(i,j).getColor().getRGB();
			}
		}
	}
	public void buildFromImage(BufferedImage bimage)
	{
		try
		{
			width = bimage.getWidth();
			height = bimage.getHeight();
			// Protokol.write("SimpleIcon:buildFromImage:width :" + width);
			// Protokol.write("SimpleIcon:buildFromImage:height:" + height);
			pixel = new int[width][height];
			for (int i=0;i<width;i++)
			{
				for (int j=0;j<height;j++)
				{
					pixel[i][j] = bimage.getRGB(i,j);
				}
			}
		}
		catch (Exception e)
		{
			Protokol.write("SimpleIcon:buildFromImage:Exception:");
			Protokol.write(e.toString());
		}
	}
	public BufferedImage getImage() 
	{
		BufferedImage erg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for (int i=0;i<width;i++)
		{
			for (int j=0;j<height;j++)
			{
				erg.setRGB(i,j,pixel[i][j]);
			}
		}
		return erg;
	}
	public Pixel getPixel(int x, int y) 
	{
		Pixel p = Factory.getPixel();
		p.setX(x);
		p.setY(y);
		if (x < width)
		{
			if (y < height)
			{
				p.setColor(new Color(pixel[x][y]));
			}
		}
		return p;
	}
	public boolean readFromTileServer(int x,int y,int z) 
	{
		boolean erg = true;
		int max = 1;
		for (int i=0;i<z;i++)
		{
			max = 2 * max;
		}
		if (x >= 0 && y >= 0 && x < max && y < max)
		{
			BufferedImage im = null;
			String sUrl = "http://tile.openstreetmap.org/" + z + "/" + y + "/" + x + ".png";
			try
			{
				URL url = new URL(sUrl);
				URLConnection con = url.openConnection();
				InputStream in = con.getInputStream();
				im = ImageIO.read(in);
			}
			catch (Exception e)
			{
				Protokol.write("SimpleIcon:readFromTileServer:Exception:");
				Protokol.write(e.toString());
				erg = false;
			}
			if (im != null)
			{
				width = im.getWidth();
				height = im.getHeight();
				//Protokol.write("SimpleIcon:readFromTileServer:width:" + width);
				//Protokol.write("SimpleIcon:readFromTileServer:height:" + height);
				pixel = new int[width][height];
				for (int i=0;i<width;i++)
				{
					for (int j=0;j<height;j++)
					{
						pixel[i][j] = im.getRGB(i,j);
					}
				}
			}
			else
			{
				String msg = "SimpleIcon:readFromTileServer:No Image Loaded";
				buildRandom();
				Protokol.write(msg);
				erg = false;
			}
		}
		else
		{
			String msg = "SimpleIcon:readFromTileServer:Out of Bounds";
			buildRandom();
			Protokol.write(msg);
			erg = false;
		}
		return erg;
	}
	public void setPixel(Pixel p)
	{
		int x = p.getX();
		int y = p.getY();
		Color c = p.getColor();
		pixel[x][y] = c.getRGB();
	}
	public void setSize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		buildRandom();
	}
	public Dimension getSize() 
	{
		return new Dimension(width,height);
	}
	public Icon copy() 
	{
		SimpleIcon erg = new SimpleIcon();
		erg.width = width;
		erg.height = height;
		if (pixel != null)
		{
			erg.pixel = new int[width][height];
			for (int i=0;i<width;i++)
			{
				for (int j=0;j<height;j++)
				{
					erg.pixel[i][j] = pixel[i][j];
				}
			}
		}
		return erg;
	}
	@Override
	public void readFromFile(File file) 
	{
		BufferedImage bimage = null;
		try
		{
			bimage = ImageIO.read(file);
			buildFromImage(bimage);
		}
		catch (Exception e)
		{
			Protokol.write("SimpleIcon:readFromFile:Exception:");
			Protokol.write(e.toString());
			Protokol.write(file.getAbsolutePath());
			try
			{
				Thread.sleep(1000);
				bimage = ImageIO.read(file);
				buildFromImage(bimage);
			}
			catch (Exception ex)
			{
				Protokol.write("SimpleIcon:readFromFile:2ter Versuch:Exception:");
				Protokol.write(ex.toString());
				Protokol.write(file.getAbsolutePath());
				try
				{
					Thread.sleep(10000);
					bimage = ImageIO.read(file);
					buildFromImage(bimage);
				}
				catch (Exception exe)
				{
					Protokol.write("SimpleIcon:readFromFile:3ter Versuch:Exception:");
					Protokol.write(exe.toString());
					Protokol.write(file.getAbsolutePath());
				}
			}
		}
	}
}
