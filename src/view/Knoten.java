package view;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Knoten extends JButton
{
	private int zeile;
	private int spalte;
	
	public Knoten(int zeile, int spalte) 
	{
		setZeile(zeile);
		setSpalte(spalte);
		initButton();
	}
	
	private void initButton()
	{
		setSize(5, 5);
	}

	public int getZeile() 
	{
		return zeile;
	}

	public int getSpalte() 
	{
		return spalte;
	}

	public void setZeile(int zeile) 
	{
		this.zeile = zeile;
	}

	public void setSpalte(int spalte) 
	{
		this.spalte = spalte;
	}
	
	
}
