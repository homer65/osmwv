package osm.viewer;
import core.*;
import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class SimpleInfoPanel extends InfoPanel
{
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private int p = 0;
	private int q = 0;
	private double lat = 0;
	private double lon = 0;
	private JLabel labx = null;
	private JLabel laby = null;
	private JLabel labz = null;
	private JLabel lablat = null;
	private JLabel lablon = null;
	private JPanel kpan = null;
	private JPanel gpan = null;
	private Util util = Factory.getUtil();
	public SimpleInfoPanel()
	{
		init();
	}
	private void init()
	{
		this.removeAll();
		this.setLayout(new BorderLayout());
		double dx = (double)x;
		double dy = (double)y;
		double dp = (double)p;
		double dq = (double)q;
		dx = dx + 1 + (dp / 256);
		dy = dy + 1 + (dq / 256);
		labx = new JLabel(" x:" + util.getRound2(dx));
		laby = new JLabel(" y:" + util.getRound2(dy));
		labz = new JLabel("Zoom:" + z);
		lablat = new JLabel("lat:" + util.getRound4(lat));
		lablon = new JLabel("lon:" + util.getRound4(lon));
		kpan = new JPanel();
		kpan.setLayout(new GridLayout(1,2));
		kpan.add(labx);
		kpan.add(laby);
		gpan = new JPanel();
		gpan.setLayout(new GridLayout(1,2));
		gpan.add(lablat);
		gpan.add(lablon);
		this.add(labz,BorderLayout.CENTER);
		this.add(kpan,BorderLayout.EAST);
		this.add(gpan,BorderLayout.SOUTH);
		this.validate();
		this.repaint();
	}
	@Override
	public void setKoordinate(Koordinate k) 
	{
		k.calculateLatLon();
		x = k.getX();
		y = k.getY();
		z = k.getZ();
		p = k.getP();
		q = k.getQ();
		lat = k.getLat();
		lon = k.getLon();
		init();
	}
	@Override
	public void build() 
	{
		init();
	}
}
