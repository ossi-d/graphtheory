package view;
import java.awt.*;
import javax.swing.*;
import controller.*;

@SuppressWarnings("serial")

public class WegMatrix extends JPanel 
{
	private int knotenanzahl;
	private JLabel[][] labels;

	public WegMatrix(int knotenanzahl) 
	{
		this.knotenanzahl = knotenanzahl;
		labels = new JLabel[knotenanzahl][knotenanzahl];
		initAdd();
		setVisible(true);
	}

	public JLabel getLabels(int spalte, int zeile) 
	{
		return labels[spalte][zeile];
	}
	

	private void initAdd() 
	{
		setLayout(new BorderLayout());
		
//----- BESCHRIFTUNG---------------------------------------------------------------------------
		JPanel labelsPanel = new JPanel(new GridLayout(knotenanzahl+1, knotenanzahl+1));
		JLabel textLabel = new JLabel("Wegmatrix", JLabel.CENTER);
		
//----- BESCHRIFTUNG wird HINZUGEFUEGT---------------------------------------------------------------------------    
		add(textLabel, BorderLayout.NORTH);
		add(labelsPanel, BorderLayout.CENTER);
    	
    
    for (int spalte = -1; spalte < knotenanzahl; spalte ++) 
    {
    	for (int zeile = -1; zeile < knotenanzahl; zeile ++) 
    	{
    		if(zeile == -1 && spalte == -1)
    		{
    			labelsPanel.add(new JLabel(""));
    		}
    		else
    		{
    			 if(zeile == -1)
    			 {
    				 labelsPanel.add(new JLabel((spalte+1)+"", JLabel.CENTER)); 
    			 }
    		     else
    		     {
    		    	 if(spalte == -1)
    		    	 {
    		    		 labelsPanel.add(new JLabel((zeile + 1)+"", JLabel.CENTER)); 
    		    	 }
       		         else
       		         {
       		        	 labels[spalte][zeile] = new JLabel("0", JLabel.CENTER);

//------------------BORDER fuer FORMATIERUNG---------------------------------------------------------------------------         		        	  
       		        labels[spalte][zeile].setBorder(BorderFactory.createLineBorder(Color.BLACK));
       		        labelsPanel.add(labels[spalte][zeile]);
       		        } 
    		    }
    		       
    	    }
        }
    }    
  }
  
	public void aktualisieren()
	{
		int[][] wegmatrix = Controller.getInstanz().berechneWegMatrix();
        for (int s = 0; s < knotenanzahl; s++) 
        {
        	for (int z = 0; z < knotenanzahl; z++) 
        	{
        		labels[s][z].setText(String.valueOf(wegmatrix[s][z]));
        	}

        }

	}
	

}

