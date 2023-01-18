package view;
import java.awt.*;


import javax.swing.*;
import controller.Controller;

@SuppressWarnings("serial")

public class DistanzMatrix extends JPanel 
{
	private int knotenanzahl;
	private JLabel[][] labels;

	public DistanzMatrix(int knotenanzahl) 
	{
		this.knotenanzahl = knotenanzahl;
		//labels = new JLabel[knotenanzahl+1][knotenanzahl+1];
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
		JPanel labelsPanel = new JPanel(new GridLayout(knotenanzahl+1, knotenanzahl+1));
		JLabel textLabel = new JLabel("Distanzmatrix", JLabel.CENTER);
        
		add(textLabel, BorderLayout.NORTH);
		add(labelsPanel, BorderLayout.CENTER);
        
		for (int s = -1; s < knotenanzahl; s++) 
		{
			for (int z = -1; z < knotenanzahl; z++) 
			{
				if(z==-1 && s==-1)
				{
					labelsPanel.add(new JLabel(""));
				}        
				else
				{
					if(z == -1)
					{
						labelsPanel.add(new JLabel((s+1)+"", JLabel.CENTER));
					}
			        else
			        {
			        	 if(s == -1)
			        	 {
			        		 labelsPanel.add(new JLabel((z+1)+"", JLabel.CENTER));
			        	 }
					     else
					     {
					    	 labels[s][z] = new JLabel("0", JLabel.CENTER);
					    	 labels[s][z].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					    	 labelsPanel.add(labels[s][z]);
					     }
			        }
				}
            }
        }
      }
  
	public void aktualisieren()
	{
		int[][] distanzmatrix = Controller.getInstanz().berechneDistanzMatrix();
    
		for (int s = 0; s < knotenanzahl; s++) 
		{
			for (int z = 0; z < knotenanzahl; z++) 
			{
				if(distanzmatrix[s][z] == 0 && s != z)
				{
					labels[s][z].setText("~");
				}
		        else
		        {
		        	labels[s][z].setText(String.valueOf(distanzmatrix[s][z]));
		        }
			}

		}
	}
}

	



