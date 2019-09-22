package osm.viewer;
import core.*;
import core.Icon;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;
import java.io.*;
//import java.net.*;
public class MainFrame extends Menu implements PixelListener
{
	private static final long serialVersionUID = 0;
	private Icon icon = null;
	private InfoPanel info = Factory.getInfoPanel();
	private KachelCache cache = Factory.getKachelCache();
	private KachelPrefetcher prefetch = Factory.getKachelPrefetcher();
	private JPanel cpan = new JPanel();
	private JPanel bpan = new JPanel();
	private JButton butt1 = new JButton("+");
	private JButton butt2  = new JButton("-");
	private JButton butt3 = new JButton("@");
	//private JButton butt4 = new JButton("gpx");
	private JMenuBar menu = new JMenuBar();
	private JMenu m1 = new JMenu("Information");
	private JMenu m2 = new JMenu("File");
	private JMenu m3 = new JMenu("GPX");
	private JMenu m4 = new JMenu("Cache");
	private JMenu m5 = new JMenu("Position");
	//private JMenuItem m11 = new JMenuItem("About...");
	private JMenuItem m12 = new JMenuItem("Version...");
	private JMenuItem m21 = new JMenuItem("SavePosition");
	private JMenuItem m22 = new JMenuItem("RestorePosition");
	private JMenuItem m23 = new JMenuItem("SaveMapAsPNG");
	private JMenuItem m24 = new JMenuItem("SaveXMLData");
	//private JMenuItem m31 = new JMenuItem("Open GPX Track");
	//private JMenuItem m32 = new JMenuItem("Close GPX Track");
	private JMenuItem m33 = new JMenuItem("Load GPX Track");
	private JMenuItem m41 = new JMenuItem("Statistik...");
	//private JMenuItem m42 = new JMenuItem("Build Cache");
	private JMenuItem m51 = new JMenuItem("Position to Border");
	private JMenuItem m52 = new JMenuItem("Position plus one north");
	private JMenuItem m53 = new JMenuItem("Position plus one east");
	private JMenuItem m54 = new JMenuItem("Position plus one south");
	private JMenuItem m55 = new JMenuItem("Position plus one west");
	private IconPanel ip = Factory.getIconPanel();
	private Persistenz persistenz = Factory.getPersistenz();
	private Koordinate koordinate = persistenz.getStartKoordinate();
	private GPXTrack track = null;
	//private Util util = Factory.getUtil();
	public MainFrame() 
	{
		super("http://www.myoggradio.org");
		bpan.setLayout(new GridLayout(1,3));
		bpan.add(butt1);
		bpan.add(butt2);
		bpan.add(butt3);
		//bpan.add(butt4);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		//butt4.addActionListener(this);
		butt1.setToolTipText("Increase Zoom");
		butt2.setToolTipText("Decrease Zoom");
		butt3.setToolTipText("Show local Websites");
		//butt4.setToolTipText("Add Point to GPX Track");
		//m11.addActionListener(this);
		m12.addActionListener(this);
		m21.addActionListener(this);
		m22.addActionListener(this);
		m23.addActionListener(this);
		m24.addActionListener(this);
		//m31.addActionListener(this);
		//m32.addActionListener(this);
		m33.addActionListener(this);
		m41.addActionListener(this);
		//m42.addActionListener(this);
		m51.addActionListener(this);
		m52.addActionListener(this);
		m53.addActionListener(this);
		m54.addActionListener(this);
		m55.addActionListener(this);
		m1.add(m12);
		//m1.add(m11);
		m2.add(m21);
		m2.add(m22);
		m2.add(m23);
		m2.add(m24);
		//m3.add(m31);
		//m3.add(m32);
		m3.add(m33);
		m4.add(m41);
		//m4.add(m42);
		m5.add(m51);
		m5.add(m52);
		m5.add(m53);
		m5.add(m54);
		m5.add(m55);
		menu.add(m5);
		menu.add(m4);
		menu.add(m3);
		menu.add(m2);
		menu.add(m1);
		setJMenuBar(menu);
		ip.addListener(this);
		showIcon();
		cpan.setLayout(new BorderLayout());
		cpan.add(bpan,BorderLayout.NORTH);
		cpan.add(ip,BorderLayout.CENTER);
		cpan.add(info,BorderLayout.SOUTH);
		setContentPane(cpan);
	}
	public void showIcon()
	{
		info.setKoordinate(koordinate);
		info.build();
		Kachel k00 = cache.get(koordinate.getX()+0,koordinate.getY()+0,koordinate.getZ());
		Kachel k01 = cache.get(koordinate.getX()+0,koordinate.getY()+1,koordinate.getZ());
		Kachel k02 = cache.get(koordinate.getX()+0,koordinate.getY()+2,koordinate.getZ());
		Kachel k03 = cache.get(koordinate.getX()+0,koordinate.getY()+3,koordinate.getZ());
		Kachel k10 = cache.get(koordinate.getX()+1,koordinate.getY()+0,koordinate.getZ());
		Kachel k11 = cache.get(koordinate.getX()+1,koordinate.getY()+1,koordinate.getZ());
		Kachel k12 = cache.get(koordinate.getX()+1,koordinate.getY()+2,koordinate.getZ());
		Kachel k13 = cache.get(koordinate.getX()+1,koordinate.getY()+3,koordinate.getZ());
		Kachel k20 = cache.get(koordinate.getX()+2,koordinate.getY()+0,koordinate.getZ());
		Kachel k21 = cache.get(koordinate.getX()+2,koordinate.getY()+1,koordinate.getZ());
		Kachel k22 = cache.get(koordinate.getX()+2,koordinate.getY()+2,koordinate.getZ());
		Kachel k23 = cache.get(koordinate.getX()+2,koordinate.getY()+3,koordinate.getZ());		
		Kachel k30 = cache.get(koordinate.getX()+3,koordinate.getY()+0,koordinate.getZ());
		Kachel k31 = cache.get(koordinate.getX()+3,koordinate.getY()+1,koordinate.getZ());
		Kachel k32 = cache.get(koordinate.getX()+3,koordinate.getY()+2,koordinate.getZ());
		Kachel k33 = cache.get(koordinate.getX()+3,koordinate.getY()+3,koordinate.getZ());		
		prefetch = Factory.getKachelPrefetcher();
		prefetch.setKoordinaten(koordinate.getX(),koordinate.getY(),koordinate.getZ());
		//prefetch.startPrefetch(cache);
		Icon icon00 = k00.getIcon(track);
		Icon icon01 = k01.getIcon(track);
		Icon icon02 = k02.getIcon(track);
		Icon icon03 = k03.getIcon(track);
		Icon icon10 = k10.getIcon(track);
		Icon icon11 = k11.getIcon(track);
		Icon icon12 = k12.getIcon(track);
		Icon icon13 = k13.getIcon(track);
		Icon icon20 = k20.getIcon(track);
		Icon icon21 = k21.getIcon(track);
		Icon icon22 = k22.getIcon(track);
		Icon icon23 = k23.getIcon(track);
		Icon icon30 = k30.getIcon(track);
		Icon icon31 = k31.getIcon(track);
		Icon icon32 = k32.getIcon(track);
		Icon icon33 = k33.getIcon(track);
		icon00.umrande(Color.CYAN);
		icon01.umrande(Color.CYAN);
		icon02.umrande(Color.CYAN);
		icon03.umrande(Color.CYAN);
		icon10.umrande(Color.CYAN);
		icon11.umrande(Color.CYAN);
		icon12.umrande(Color.CYAN);
		icon13.umrande(Color.CYAN);
		icon20.umrande(Color.CYAN);
		icon21.umrande(Color.CYAN);
		icon22.umrande(Color.CYAN);
		icon23.umrande(Color.CYAN);
		icon30.umrande(Color.CYAN);
		icon31.umrande(Color.CYAN);
		icon32.umrande(Color.CYAN);
		icon33.umrande(Color.CYAN);
		Icon icon16 = Factory.getIcon();
		icon16.build16(icon00,icon01,icon02,icon03,icon10,icon11,icon12,icon13,icon20,icon21,icon22,icon23,icon30,icon31,icon32,icon33);
		icon = icon16.clip16(koordinate.getP(),koordinate.getQ());
		icon = icon16.mittel16Kreuz(icon);
		if (track != null) icon.paintGPX(track,koordinate);
		ip.setIcon(icon);
		this.validate();
		this.repaint();
	}
	@Override
	public void clicked(Pixel pixel) 
	{
		int x = koordinate.getX();
		int y = koordinate.getY();
		int q = koordinate.getQ();
		int p = koordinate.getP();
		int a = pixel.getX();
		int b = pixel.getY();
		p = p + a - 384;
		q = q + b - 384;
		if (p < 0)
		{
			if (p < -256)
			{
				p += 512;
				y--;
				y--;
			}
			else
			{
				p += 256;
				y--;
			}
		}
		if (q < 0) 
		{
			if (q < -256)
			{
				q += 512;
				x--;
				x--;
			}
			else
			{
				q += 256;
				x--;
			}
		}
		if (p > 256)
		{
			if (p > 512)
			{
				p -= 512;
				y++;
				y++;
			}
			else
			{
				p -= 256;
				y++;
			}
		}
		if (q > 256)
		{
			if (q > 512)
			{
				q -= 512;
				x++;
				x++;
			}
			else
			{
				q -= 256;
				x++;
			}
			
		}
		koordinate.setX(x);
		koordinate.setY(y);
		koordinate.setQ(q);
		koordinate.setP(p);
		koordinate.calculateLatLon();
		showIcon();
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		/*
		if (quelle == m11)
		{
			String about = "http://ehm.homelinux.org/OSMWV/about.html";
			URL url = null;
			try
			{
				url = new URL(about);
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(url.toURI());
			}
			catch (Exception e)
			{
				Protokol.write("MainFrame:actionPerformed:m11:Exception:");
				Protokol.write(e.toString());
			}
		}
		*/
		if (quelle == m12)
		{
			JOptionPane.showMessageDialog(null,Parameter.version,"Version",JOptionPane.INFORMATION_MESSAGE);
		}
		if (quelle == m21)
		{
			persistenz.storeStartKoordinate(koordinate);
		}
		if (quelle == m22)
		{
			koordinate = persistenz.getStartKoordinate();
			showIcon();
		}
		if (quelle == m23)
		{
			FileChooser fc = Factory.getFileChooser();
			File file = fc.getWriteFile();
			if (file != null)
			{
				icon.writeToFile(file);
			}			
		}
		if (quelle == m24)
		{
			FileChooser fc = Factory.getFileChooser();
			File file = fc.getWriteFile();
			if (file != null)
			{
				XMLData xmlData = Factory.getXMLData();
				ArrayList<String> xml = xmlData.getXML(koordinate);
				try
				{
					Writer writer = new FileWriter(file);
					for (int i=0;i<xml.size();i++)
					{
						String satz = xml.get(i);
						writer.write(satz + "\n");
					}
					writer.close();
				}
				catch (Exception e)
				{
					Protokol.write("MainFrame:actionPerformed:m24:Exception:");
					Protokol.write(e.toString());
				}
			}			
		}
		/*
		if (quelle == m31) // Open GPX Track
		{
			track = Factory.getGPXTrack();
			JOptionPane.showMessageDialog(null,"GPX Track is now open","",JOptionPane.INFORMATION_MESSAGE);
		}
		if (quelle == m32) // Close GPX Track
		{
			if (track == null)
			{
				JOptionPane.showMessageDialog(null,"You must Open GPX Track first","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				track.writeToFile();
				track = null;
			}
		}
		*/
		if (quelle == m33) // Load GPX Track
		{
			FileChooser fc = Factory.getFileChooser();
			File file = fc.getGPXFile();
			if (file != null)
			{
				cache.resetRAM();
				track = Factory.getGPXTrack();
				track.readFromFile(file);
				if (track != null) track.buildKoordinate(koordinate);
				showIcon();
			}			
		}
		if (quelle == butt1) // Zoom +
		{
			int x = koordinate.getX();
			int y = koordinate.getY();
			int z = koordinate.getZ();
			int q = koordinate.getQ();
			int p = koordinate.getP();
			if (z < 18)
			{
				z++;
				double dx = (double) x;
				dx += 1.5;
				double dy = (double) y;
				dy += 1.5;
				double dp = (double) p;
				double dq = (double) q;
				dx += dq / 256.0;
				dy += dp / 256.0;
				dx = 2.0 * dx;
				dy = 2.0 * dy;
				x = (int) dx;
				y = (int) dy;
				p = (int) ((dy - y) * 256.0);
				q = (int) ((dx - x) * 256.0);
				p -=384;
				q -=384;
				if (p < 0)
				{
					y--;
					p += 256;
					if (p < 0)
					{
						y--;
						p += 256;
					}
				}
				if (q < 0)
				{
					x--;
					q += 256;
					if (q < 0)
					{
						x--;
						q += 256;
					}
				}
			}
			else
			{
				Protokol.write("MainFrame:actionPerformed: Nur Zoomstufe 2 bis 18 erlaubt");
			}
			koordinate.setX(x);
			koordinate.setY(y);
			koordinate.setZ(z);
			koordinate.setQ(q);
			koordinate.setP(p);
			this.showIcon();
		}
		if (quelle == butt2) // Zoom -
		{
			int x = koordinate.getX();
			int y = koordinate.getY();
			int z = koordinate.getZ();
			int q = koordinate.getQ();
			int p = koordinate.getP();
			if (z > 2)
			{
				z--;
				double dx = (double) x;
				dx += 1.5;
				double dy = (double) y;
				dy += 1.5;
				double dp = (double) p;
				double dq = (double) q;
				dx += dq / 256.0;
				dy += dp / 256.0;
				dx = dx / 2.0;
				dy = dy / 2.0;
				x = (int) dx;
				y = (int) dy;
				p = (int) ((dy - y) * 256.0);
				q = (int) ((dx - x) * 256.0);
				p -=384;
				q -=384;
				if (p < 0)
				{
					y--;
					p += 256;
					if (p < 0)
					{
						y--;
						p += 256;
					}
				}
				if (q < 0)
				{
					x--;
					q += 256;
					if (q < 0)
					{
						x--;
						q += 256;
					}
				}
			}
			else
			{
				Protokol.write("MainFrame:actionPerformed: Nur Zoomstufe 2 bis 18 erlaubt");
			}
			koordinate.setX(x);
			koordinate.setY(y);
			koordinate.setZ(z);
			koordinate.setQ(q);
			koordinate.setP(p);
			this.showIcon();
		}
		if (quelle == butt3) // @ Lokale Webseiten anzeigen
		{
			int x = koordinate.getX();
			int y = koordinate.getY();
			int z = koordinate.getZ();
			int q = koordinate.getQ();
			int p = koordinate.getP();
			WebsiteViewer viewer = Factory.getWebsiteViewer(x,y,z,p,q);
			viewer.start();
			JOptionPane.showMessageDialog(null,"WebsiteViewer started. Please Wait","",JOptionPane.INFORMATION_MESSAGE);
		}
		/*
		if (quelle == butt4) // gpx add Point
		{
			if (track != null)
			{
				koordinate.calculateLatLon();
				double lat = koordinate.getLat();
				double lon = koordinate.getLon();
				String slat = util.getRound4(lat);
				String slon = util.getRound4(lon);
				track.addPoint(lat,lon);
				Protokol.write("Point added: " + slat + ":" + slon);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"You must Open GPX Track first","",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		*/
		if (quelle == m41)
		{
			KachelCache kc = Factory.getKachelCache();
			String statistik = kc.getStatistik();
			JOptionPane.showMessageDialog(null,statistik,"Statistik",JOptionPane.INFORMATION_MESSAGE);
		}
		/*
		if (quelle == m42)
		{
			int ok = JOptionPane.showConfirmDialog(null,"Build of Cache may take a long Time \n Do you want this?");
			if (ok == JOptionPane.OK_OPTION)
			{
				int x = koordinate.getX();
				int y = koordinate.getY();
				int z = koordinate.getZ();
				if (z < 7)
				{
					JOptionPane.showMessageDialog(null,"Sorry, Zoom Level mußt be greater 6","",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					int gesamt = 0;
					int temp = 1;
					for (int iz=z;iz<18;iz++)
					{
						gesamt += temp;
						temp = 4 * temp;
					}
					System.out.println("MainFrame::m42:Numberof tiles to read: " + gesamt);
					ProgressPanel bar = Factory.getProgressPanel();
					bar.setMaximum(gesamt);
					CacheBuilder cacheBuilder = Factory.getCacheBuilder();
					cacheBuilder.buildCache(x,y,z,bar);
				}
			}
		}
		*/
		if (quelle == m51) // Position to Border
		{
			koordinate.setP(0);
			koordinate.setQ(0);
			koordinate.calculateLatLon();
			showIcon();
		}
		if (quelle == m54) // Position plus one north
		{
			int x = koordinate.getX();
			int z = koordinate.getZ();
			x++;
			int n = 1;
			for (int i=0;i<z;i++)
			{
				n = n * 2;
			}
			if (x > n) x--;
			koordinate.setX(x);
			showIcon();
		}
		if (quelle == m53) // Position plus one east
		{
			int y = koordinate.getY();
			int z = koordinate.getZ();
			y++;
			int n = 1;
			for (int i=0;i<z;i++)
			{
				n = n * 2;
			}
			if (y > n) y--;
			koordinate.setY(y);
			showIcon();
		}
		if (quelle == m52) // Position plus one south
		{
			int x = koordinate.getX();
			x--;
			if (x < 0) x++;
			koordinate.setX(x);
			showIcon();
		}
		if (quelle == m55) // Position plus one north
		{
			int y = koordinate.getY();
			y--;
			if (y < 0) y++;
			koordinate.setY(y);
			showIcon();
		}
	}
	public boolean istGerade(int i)
	{
		boolean erg = false;
		int x = i / 2;
		if (i == (2 * x)) erg = true;
		return erg;
	}
}
