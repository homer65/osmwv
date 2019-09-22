package osm.viewer;
import java.io.*;
import javax.swing.*;
import core.*;
public class AdvancedFileChooser implements FileChooser 
{
	public static File lastChoosed = null;
	public File getReadFile() 
	{
		File erg = null;
		JFileChooser fc = null;
		if (lastChoosed == null)
		{
			fc = new JFileChooser();
		}
		else
		{
			fc = new JFileChooser(lastChoosed);
		}
		int rc = fc.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION)
		{
			erg = fc.getSelectedFile();
			lastChoosed = erg;
		}
		return erg;
	}
	public File getWriteFile() 
	{
		File erg = null;
		JFileChooser fc = null;
		if (lastChoosed == null)
		{
			fc = new JFileChooser();
		}
		else
		{
			fc = new JFileChooser(lastChoosed);
		}
		int rc = fc.showSaveDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION)
		{
			erg = fc.getSelectedFile();
			lastChoosed = erg;
		}
		return erg;
	}
	public File getGPXFile() 
	{
		File erg = null;
		JFileChooser fc = null;
		if (lastChoosed == null)
		{
			fc = new JFileChooser();
		}
		else
		{
			fc = new JFileChooser(lastChoosed);
		}
		int rc = fc.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION)
		{
			erg = fc.getSelectedFile();
			lastChoosed = erg;
		}
		return erg;
	}
}
