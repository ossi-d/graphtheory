package model;

/*
THEORIE
	POTENZMATRIX gibt den kürzesten Weg von einem Knoten zu einem anderen an
	POTENZMATRIX ist immer links ADJAZENZMATRIX ist rechts

ALGORITHMUS:
	Zeile * Spalte
*/

public class MatrizenMultiplikation 
{
	private Adjazenzmatrix adjazenzmatrix;
	private int [][] hilfsmatrix;
	private int[][] ergebnisMatrix;
	
	public MatrizenMultiplikation(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
		hilfsmatrix = new int[adjazenzmatrix.getKnotenanzahl()][adjazenzmatrix.getKnotenanzahl()];
		ergebnisMatrix = new int[adjazenzmatrix.getKnotenanzahl()][adjazenzmatrix.getKnotenanzahl()];
	}
	
	
	
//-------- Methode kopiert alle Eintraege aus der Adjazenzmatrix -----------------------------------------
	private int[][] copyEintraege()
	{
		for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
 	    {
			for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte ++) 
 	    	{
				int wert = adjazenzmatrix.getMatrix()[zeile][spalte];
				hilfsmatrix[zeile][spalte] = wert;
 	    	}
 	    }
  	    return hilfsmatrix;
 	}

//-------- Methode kopiert alle Ergebnisse aus der ErgebnisMatrix in die HilfsMatrix ----------------------	
	private int[][] updateEintraege()
	{
		for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
	    {
			for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++) 
	    	{
				hilfsmatrix[zeile][spalte] = ergebnisMatrix[zeile][spalte];
	    	}
	    }
		return hilfsmatrix;
	}

//-------- MATRIZENMULTIPLIKATION -----------------------------------------------------------------------		
	public int[][] potenzieren(int potenz) 
	{
		if(potenz > 0)
		{
			hilfsmatrix = copyEintraege(); // Werte aus Adjazenzmatrix werden übertragen
		    if (potenz == 1) // ABBRUCHBEDINGUNG
			{
		    	return hilfsmatrix;
			}
			 
		    // Schleife für A^k Durchläufe		    
		    for (int k = 1; k < potenz; k++) 
			{
		    	// eigentliche MULTIPLIKATION passiert in der Inneren Schleife
		    	for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
				{
		    		for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++) 
					{
		    			int ergebnis = 0;		        	
						for (int zeileSpalte = 0; zeileSpalte < adjazenzmatrix.getKnotenanzahl(); zeileSpalte++) 
						{
							ergebnis +=  hilfsmatrix[zeile][zeileSpalte] * adjazenzmatrix.getMatrix()[zeileSpalte][spalte];
						}
						// Ergebnis wird in ErgebnisMatrix eingetragen
						ergebnisMatrix[zeile][spalte] = ergebnis;
					}
				}
				 // Sobald ein Wert berechnet ist wird Ergebnismatrix in Hilfsmatrix kopiert 
				 // mit der Hilfsmatrix (POTENZMATRIX) wird dann die nächste POTENZ errechnet
				 hilfsmatrix = updateEintraege();
			 }
			 return ergebnisMatrix;
		 }
		 else 
		 {
			 throw new IllegalArgumentException("Potenz muss größer 0 sein!!");			
		 }
	}
		 
}
