package view;


import java.awt.event.*;
import javax.swing.AbstractAction;
//import javax.swing.JButton;
import controller.*;

@SuppressWarnings("serial")
public class KlickMatrix extends AbstractAction
{
	private Knoten button;
//	private JButton testButton = new JButton();

	public KlickMatrix(Knoten button) 
	{
	    if (button != null) {
	      this.button = button;

	    } else
	      throw new IllegalArgumentException("Nullreferenz für button!");
	 }

	  //@Override
	  public void actionPerformed(ActionEvent e) {
	    if (button.getText().equals("0"))
	    {
	      
	      // KANTE wird in SPALTE und ZEILE hinzugefuegt	
	      Controller.getInstanz().addKante(button.getSpalte(), button.getZeile() );	
	      Controller.getInstanz().addKante(button.getZeile(),button.getSpalte());
	      
	      //DIAGONALE wird mitgeklickt
	      Controller.getInstanz().getHauptfenster().getMatrixPanel().getButton(button.getZeile(),button.getSpalte()).setText("1");
	      Controller.getInstanz().getHauptfenster().getMatrixPanel().getButton(button.getSpalte(),button.getZeile()).setText("1");
	      	      
	      Controller.getInstanz().getHauptfenster().getWegMatrix().aktualisieren();
	      Controller.getInstanz().getHauptfenster().getDistanzMatrix().aktualisieren();
	      Controller.getInstanz().berechnungenAusgabe();
	    }
	    
	    else
	    	
	    if (button.getText().equals("1"))
	    {
	    	// KANTE wird in SPALTE und ZEILE geloescht
	    	Controller.getInstanz().loescheKante(button.getSpalte(), button.getZeile());
	    	Controller.getInstanz().loescheKante(button.getZeile(),button.getSpalte());
	    	
	    	//DIAGONALE wird mitgeklickt
	    	Controller.getInstanz().getHauptfenster().getMatrixPanel().getButton(button.getZeile(),button.getSpalte()).setText("0");
	    	Controller.getInstanz().getHauptfenster().getMatrixPanel().getButton(button.getSpalte(),button.getZeile()).setText("0");
	  
	    	Controller.getInstanz().getHauptfenster().getWegMatrix().aktualisieren();
	    	Controller.getInstanz().getHauptfenster().getDistanzMatrix().aktualisieren();
	    	Controller.getInstanz().berechnungenAusgabe();
	    }
	      
	  }

	


}
