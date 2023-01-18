package model;

/*
THEORIE
	Eine Kante heisst Brücke wenn sich nach Wegnahme dieser die Anzahl der Komponenten erhöht

ALGORITHMUS:
	Kanten aus Originalmatrix kopieren - anschliessend Kante löschen
	und checken ob mehr Komponenten vorhanden sind als vorher
*/

public class Bruecken 
{	
	private Adjazenzmatrix adjazenzmatrix;
	private Adjazenzmatrix adjazenzmatrix2;
	
	public Bruecken(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
	}

//-----Diese Methode addet die Kanten der OriginalMatrix ---------------------------------------------	
	private void addKante()
	{
		adjazenzmatrix2 = new Adjazenzmatrix(adjazenzmatrix.getKnotenanzahl());
		for (int s = 0; s < adjazenzmatrix.getKnotenanzahl(); s++) 
 	    {
 	    	for (int z = 0; z < adjazenzmatrix.getKnotenanzahl(); z++) 
 	    	{
 	    		if (adjazenzmatrix.getMatrix()[s][z] == 1)
 	    		{
 	    			adjazenzmatrix2.addKante(s, z);
 	    		}	 	          
 	      }
 	    }
	}
	
	
//-----Diese Methode checkt ob die Kante eine Brücke ist ---------------------------------------------	

	private boolean checkBruecke(int zeile, int spalte) 
	{	
		addKante();
	 	int komponentenanzahl = adjazenzmatrix2.getKomponenten();
	 	adjazenzmatrix2.loescheKante(zeile, spalte);
	 	adjazenzmatrix2.loescheKante(spalte, zeile);

	 	if (adjazenzmatrix2.getKomponenten() > komponentenanzahl)
	 	{
	 		return true;
	 	}	 	      
	 	return false;
	 }

//-----Diese Methode gibt Brücken zurück -------------------------------------------------------------		 
	 public int[][] getBruecken() 
	 {
		 int[][] bruecken = new int[adjazenzmatrix.getKnotenanzahl()][adjazenzmatrix.getKnotenanzahl()];

		 for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++)
		 {
			 for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++)
			 {
				 if (checkBruecke(zeile, spalte))
				 {
					 bruecken[zeile][spalte] = 1; 
				 }		 	         
			 }
		 }
	 	 return bruecken;
	 }	 
}
