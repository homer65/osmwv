package osm.viewer;
import java.awt.Color;

import core.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class SimpleProgressPanel extends JFrame implements ProgressPanel
{
	JProgressBar bar = null;
	int max = 100;
	@Override
	public void setMaximum(int i) 
	{
		max = i;
		bar = new JProgressBar(0,i);	
		bar.setBackground(Color.green);
		bar.setStringPainted(true);
		this.add(bar);
		this.pack();
		this.setVisible(true);
	}
	@Override
	public void setValue(int i) 
	{
		if (bar != null)
		{
			int x = 100 * i / max;
			String sx = x + "%";
			bar.setValue(i);
			bar.setString(sx);
			this.repaint();
		}
	}

	@Override
	public void close() 
	{
		this.setVisible(false);
	}
}
