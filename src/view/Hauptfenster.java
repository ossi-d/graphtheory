package view;
import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


// Diese Klasse ist für die Darstellung des Hauptfensters zustaendig
@SuppressWarnings("serial")
public class Hauptfenster extends JFrame
{
	private MatrixPanel adjazenzmatrix;
	private WegMatrix wegmatrix;
	private DistanzMatrix distanzMatrix;
	private JPanel matrixPanel, ausgabePanel, berechnungPanel;
	private JEditorPane berechnungen;
	private JMenuBar menueBar;
	private JMenu menue;
	private JMenuItem beenden, neu;
	
	// Konstruktor für Hauptfenster
	public Hauptfenster()
	{
		initFrame();
		initMatrixPanel();
		initAusgabePanel();
		initBerechnungenPanel();
		initComps();
		initMenue();
		addMenue();
		addListeners();
		add();
		
		setVisible(true);
	}
	

// -------- INIT METHODEN -------------------------------------------------------------------------------	
	private void initFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);    // Prozess wird bei schliessen des fensters beendet  
		setSize(800, 800); // Groesse des Fensters wird gesetzt
		setTitle("Hauptfenster"); 
		setLocationRelativeTo(null); 
		setVisible(true);
		setLayout(new GridLayout(3,1));
	}
		
	private void initMatrixPanel()
	{
		// HAUPTPANEL
		matrixPanel = new JPanel();
		matrixPanel.setSize(500, 500);
		matrixPanel.setLayout(new GridLayout(1,1));		
	}
	
	private void initAusgabePanel() 
	{
		ausgabePanel = new JPanel();
		ausgabePanel.setSize(500, 500);
		ausgabePanel.setLayout(new GridLayout(1,2));
		
	}
	
	private void initBerechnungenPanel() 
	{
		berechnungPanel = new JPanel();
		berechnungPanel.setLayout(new GridLayout(1,1));	
		berechnungPanel.setSize(500, 500);
			
		berechnungen = new JEditorPane();
		berechnungen.setEditable(false);
	}
	
	
	private void initComps() 
	{
		//ADJAZENZMATRIX
		adjazenzmatrix = new MatrixPanel(Controller.getInstanz().getKnotenanzahl());
		
		// WEGMATRIX
		wegmatrix = new WegMatrix(Controller.getInstanz().getKnotenanzahl());
		
		//DISTANZMATRIX
		distanzMatrix = new DistanzMatrix(Controller.getInstanz().getKnotenanzahl());
	}
	
	private void initMenue()
	{
		menueBar = new JMenuBar();
		menue = new JMenu("Menue");
		neu = new JMenuItem("Neue Matrix erzeugen");
		beenden = new JMenuItem("Beenden");
	}
	
	
// ---------- ADD METHODEN -------------------------------------------------------------------------------	
	private void add()
	{
		matrixPanel.add(adjazenzmatrix);
		add(matrixPanel, BorderLayout.NORTH);
		
		//BERECHNUNGENPANEL FUER BERECHNUNGEN 
		berechnungPanel.add(berechnungen);
		add(berechnungPanel);
		
		//AUSGABEPANEL FUER BERECHNUNGEN 
		ausgabePanel.add(wegmatrix);
		ausgabePanel.add(distanzMatrix);
		add(ausgabePanel);		
	}
	
	private void addMenue()
	{
		setJMenuBar(menueBar);
		menue.add(neu);
		menue.addSeparator();
		menue.add(beenden);
		menueBar.add(menue);		
	}
	

// ---------- ADD LISTENER  -------------------------------------------------------------------------------
	private void addListeners()
	{
		neu.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){ neu();}});
		beenden.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){ beenden();}});
	}
	
// ----------  LISTENER METHODEN -------------------------------------------------------------------------------
	
	public void beenden()
	{
		System.exit(0);
	}
	
	public void neu()
	{
		dispose();
		@SuppressWarnings("unused")
		KnotenEingabeFenster eingabe = new KnotenEingabeFenster();
	}
	
// ----------  GET METHODEN -------------------------------------------------------------------------------
	public MatrixPanel getMatrixPanel()
	{
		return adjazenzmatrix;
	}
	
	public WegMatrix getWegMatrix()
	{
		return wegmatrix;
	}
	
	public DistanzMatrix getDistanzMatrix()
	{
		return distanzMatrix;
	}
	
	 public JEditorPane getBerechnungen() 
	 {
		 return berechnungen;
	 }


}
