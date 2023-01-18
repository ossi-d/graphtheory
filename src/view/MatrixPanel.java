 package view;
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class MatrixPanel extends JPanel
{
	private int knotenanzahl;
	private Knoten[][] buttons;

	  public MatrixPanel(int knotenanzahl) 
	  {
	    this.knotenanzahl = knotenanzahl;
	    buttons = new Knoten[knotenanzahl][knotenanzahl];
	    init();
	    setVisible(true);
	  }

	  public Knoten getButton(int spalte, int zeile) 
	  {
	    return buttons[spalte][zeile];
	  }

	  private void init() 
	  {
		  
	    setLayout(new BorderLayout());
	    setBorder(BorderFactory.createLineBorder(Color.darkGray));
	    
	    // PANEL welches die Buttons aufnimmmt + LABEL für die Beschriftung
	    JPanel buttonsPanel = new JPanel(new GridLayout(knotenanzahl+1, knotenanzahl+1));
	    JLabel textLabel = new JLabel("Adjazenzmatrix", JLabel.CENTER);
	    
	    add(textLabel, BorderLayout.NORTH);
	    add(buttonsPanel, BorderLayout.CENTER);
	    
	    //Spalte
	    for (int s = -1; s < knotenanzahl; s++) 
	    {
	    	// Zeile
	    	for (int z =-1; z < knotenanzahl; z++) 
	    	{
	    		// Einrueckung
	    		if(z==-1 && s==-1)
	    		buttonsPanel.add(new JLabel(""));
	    		else
	    		
	    		// Beschriftung (s+1) wenn eine neue Zeile begonnen wird	
	    		if(z == -1)
	    		buttonsPanel.add(new JLabel((s+1)+"", JLabel.CENTER));
	    		else
	    			
	    		// Beschriftung (z+1) wenn eine neue Spalte begonnen wird	
	    		if(s == -1)
	    		buttonsPanel.add(new JLabel((z+1)+"", JLabel.CENTER));
	    		
	    		else
	    		{
	    			//KnotenButton wird erzeugt auf 0 gesetzt und geaddet
	    			buttons[s][z] = new Knoten(s, z);
	    		 // buttons[s][z].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    		    buttons[s][z].setAction(new KlickMatrix(buttons[s][z]));
	    		    buttons[s][z].setText("0");
	    		    buttonsPanel.add(buttons[s][z]);

	    		 // HAUPTDIAGONALE WIRD DEAKTIVIERT
	    		 if (buttons[s][z].getZeile() == buttons[s][z].getSpalte())
	    		 buttons[s][z].setEnabled(false);
	    		}
	    	}
	    }

	  }
}
