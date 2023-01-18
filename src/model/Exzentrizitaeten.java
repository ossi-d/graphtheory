package model;

/*
THEORIE
	KŸrzeste Weg eines Knoten zum weitest entfernten Knoten

ALGORITHMUS:
	Es wird immer der hšchste Eintrag aus der DistanzMatrix gespeichert
*/

public class Exzentrizitaeten 
{
	private Adjazenzmatrix adjazenzmatrix;
	private Distanzmatrix dm;
	
	public Exzentrizitaeten(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
		dm = new Distanzmatrix(adjazenzmatrix);
	}

	public int[] berechneExzentrizitaeten() 
	{
 	    int[][] distanzmatrix = dm.berechneDistanzMatrix();
 	    int[] ergebnisMatrix = new int[adjazenzmatrix.getKnotenanzahl()];
 	    int wert = 0;

 	    for (int zeile = 0; zeile < adjazenzmatrix.getMatrix().length; zeile++) 
 	    {
 	    	for (int spalte = 0; spalte < adjazenzmatrix.getMatrix().length; spalte++) 
 	    	{
 	    		if (distanzmatrix[zeile][spalte] > wert)
 	    		{
 	    			wert = distanzmatrix[zeile][spalte];
 	    		}
 	    }
 	    	ergebnisMatrix[zeile] = wert;
 	    	wert = 0;
 	    }
 	    return ergebnisMatrix;
 	}
}
