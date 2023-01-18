package model;

/*--------------------------------------------------------------------------------------------------
 ------ Die Wegmatrix zeigt an, ob ein Weg zwischen zwei Punkten eines Graphen besteht -------------
 ---------------------------------------------------------------------------------------------------
*  Die L�nge des Weges wird nicht angezeigt, dies geschieht in der Distanzmatrix
*  L�ngster m�glicher Weg = (Anzahl der Knoten) - 1
*  Wenn Matrix voll ist und keine 0 mehr dann ist der Graph zusammenh�ngend
*  
*  ABBRUCHBEDINGUNGEN
*  1.) Knotenanzahl-1  
*  2.) Keine �nderung mehr
*  3.) Matrix ist voll
*  
*  // ZUSATZ: Die Hochzahl der letzten Multiplikation ist der Durchmesser des Graphen
*  
*  ALGORITHMUS:
*  WEGMATRIX und POTENZMATRIX (f�r MatrizenMultiplikation) werden initialisiert
*  Alles auf 0 nur Diagonale auf 1
*  [Verschachtelte Schleife so lange: Durchgang < Knotenanzahl-1]
*      Erstellen der Matrix A^1 (POTENZMATRIX)
*      In WEGMATRIX wird in der Hauptdiagonalen "1" eingetragen
*      Ist in der Matrix A^1 eine �nderung im Vergleich zur Wegmatrix
*      Wenn ja, dann in Wegmatrix eine "1" eintragen
*      �nderung pr�fen 
*      checken ob Matrix voll ist 
*  [Schleifenende]
*/
public class Wegmatrix 
{
	private Adjazenzmatrix adjazenzmatrix;
	private int[][] wegMatrix;
	private boolean aenderung;
	private boolean voll;
	
	//private int durchmesser;
	
	public Wegmatrix(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
		aenderung = true;
	 	voll = false;
	}
	
//-------- METHODE PR�FT OB WEGMATRIX VOLL IST --------------------------------------
	private boolean checkVoll()
	{
		for(int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++)
	    {
			for(int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++)
	        {
				if(wegMatrix[zeile][spalte] == 0)
	            {
					return false;
	            }
	        }
	    }
		return true;
	}

//-------- METHODE BERECHNET WEGMATRIX -----------------------------------------------	
	public int[][] berechneWegMatrix() 
 	{
		MatrizenMultiplikation multipliziere = new MatrizenMultiplikation(adjazenzmatrix);
 	    wegMatrix = new int[adjazenzmatrix.getKnotenanzahl()][adjazenzmatrix.getKnotenanzahl()];
 	     	    
 		// SCHLEIFE F�R DIE POTENZ
 	 	for (int k = 0; k < adjazenzmatrix.getKnotenanzahl(); k++) 
 	 	{ 
 	 		//durchmesser = i; // letzte Hochzahl der Multiplikation ist Durchmesser
 	 		aenderung = false;
 	 	 	voll = true;
 	 	    int[][] potenzMatrix = multipliziere.potenzieren(k + 1); // i+1 weil ja sonst A^0
 	 	    	
 	 	    // Verschachtelte Schleife l�uft durch die Matrix
 	 	    for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
 	 	    {
 	 	    	for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++) 
 	 	    	{
 	 	    		if (zeile == spalte)
 	 	    		{
 	 	    			wegMatrix[zeile][spalte] = 1;
 	 	    		}	        
 	 	    			
 	 	    		if (potenzMatrix[zeile][spalte] >= 1 && wegMatrix[zeile][spalte] == 0)
 	 	    		{
 	 	    			wegMatrix[zeile][spalte] = 1;
 	 	    			aenderung = true;
 	 	    		}	
 	 	    			
 	 	    	}
 	 	    }
 	 	    	
 	 	    voll = checkVoll();
 	 	    	
 	 	    if(voll)
 	 	    {
 	 	    	break;
 	 	    }
 	 	    if(!aenderung)
 	 	    {
 	 	    	break;
 	 	    }
 	 	    	
  		  }	
		  return wegMatrix;
 	 }
	
	/*// Anderer Weg um Durchmesser zu berechnen
	public int getDurchmesser()
	{
		return durchmesser+1;
	}*/
}