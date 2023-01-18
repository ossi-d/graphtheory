package view;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/* 1.JFrame erstellen
* 2.Panel erstellen
* 3.Buttons etc auf Panel packen
* 4.Panel auf Frame packen
* 5.Frame anzeigen
*/


@SuppressWarnings("serial")
public class KnotenEingabeFenster extends JFrame
{
// Instanzvariablen
	private JPanel panel, panel1, panel2;
	private JButton ok;
	private JLabel label;
	private JTextField input;
	
// Konstruktor initialisiert das Eingabefenster	
	public KnotenEingabeFenster()
	{
		init();
		initButton();
		initLabel();
		initInput();
		initPanel();
		addComponents();
		addListener();
		setVisible(true);
	}

/*----------------------------------------------------------------------------------------------------------------
	INITIALISIERUNG METHODEN
----------------------------------------------------------------------------------------------------------------*/
	public void init()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);    // Prozess wird bei schliessen des fensters beendet  
		setSize(500, 150); // Groesse des Fensters wird gesetzt
		setTitle("Knoteneingabe"); 
		setLocationRelativeTo(null); 
	}
	
	public void initPanel()
	{
		this.panel = new JPanel(new BorderLayout());
		this.panel1 = new JPanel();
		this.panel2 = new JPanel();
	}
	
	public void initButton()
	{
		this.ok = new JButton("Weiter");
	}
	
	public void initLabel()
	{
		this.label = new JLabel("Knotenanzahl eingeben: ");
	}
	
	public void initInput()
	{
		this.input = new JTextField();
		input.setColumns(2);
	}
	
// ----------------------- ADD METHODEN  ----------------------------------------------------------------
	public void addComponents()
	{
		panel1.add(label);
		panel1.add(input);
		panel2.add(ok);
		panel.add(panel1, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
		add(panel);
	}
	

// ---------------------- LISTENER ADDEN ------------------------------------------------------------------
	public void addListener()
	{
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {weiter();}});
	}
	
	
// ---------------------- HANDLERMETHODEN -------------------------------------------------------------
	public void weiter()
	{	
		// PR†FUNG PASST
		try 
		{			
			Controller.getInstanz().setKnotenanzahl(Integer.parseInt(input.getText()));
		} 
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(KnotenEingabeFenster.this, "Bitte Zahl zwischen 2 und 15 eingeben!");
			return;
		}
		
		Hauptfenster hauptfenster = new Hauptfenster();
		Controller.getInstanz().setHauptfenster(hauptfenster);
		dispose();
	
		
	}
	
	public static void main(String[] args) 
	{
		
		@SuppressWarnings("unused")
		KnotenEingabeFenster eingabe = new KnotenEingabeFenster();

	}

}
