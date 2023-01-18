package model;
import java.util.ArrayList;
 
/*
THEORIE
	Erhöht sich die Komponentenanzahl wenn ein Knoten mit seinen inzidenten Kanten weggenommen wird 
	so ist dieser Knoten ein Artikulationspunkt

ALGORITHMUS:
	Kanten aus Originalmatrix kopieren - anschliessend Kante löschen
	und Knoten entfernen >> danach checken ob mehr Komponenten vorhanden sind als vorher
*/

public class Artikulationen 
{
	public Adjazenzmatrix adjazenzmatrix;
	private Adjazenzmatrix adjazenzmatrix2;
	
	public Artikulationen(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
	}

//--------------- Methode kopiert alle Kanten in eine weitere Adjazenzmatrix ---------------------------------- 
	private void copyKante()
	{
		 adjazenzmatrix2 = new Adjazenzmatrix(adjazenzmatrix.getKnotenanzahl());
		for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
	    {
	    	for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++) 
	    	{	    		
	    			adjazenzmatrix2.getMatrix()[zeile][spalte] = adjazenzmatrix.getMatrix()[zeile][spalte]; 
	    		
	    	}
	    }
	}

//--------------- Methode prüft ok Knoten eine Artikulation ist ----------------------------------------------- 	
	private boolean checkArtikulation(int knoten) 
	{
		copyKante();
	    int komponentenanzahl = adjazenzmatrix2.getKomponenten(); 
	           
	    for (int k = 0; k < adjazenzmatrix.getKnotenanzahl(); k++) 
	    {
	      // Kante wird geloescht - auch aus Diagonale
	      adjazenzmatrix2.loescheKante(knoten, k); 
	      adjazenzmatrix2.loescheKante(k, knoten);	    
	    }
	    
	    // 1 Knoten wird entfernt | komponentenanzahl == alle Komponenten
	    if ((adjazenzmatrix2.getKomponenten()-1)  > komponentenanzahl) // Pruefung ob mehr Komponenten vorhanden als vorher 
	    {
	    	return true;
	    }
	    return false;
	  }

//--------------- Methode gibt Artikulation zurück ------------------------------------------------------------ 
	  public ArrayList<Integer> getArtikulationen() 
	  {
	    ArrayList<Integer> artikulationen = new ArrayList<Integer>();

	    for (int knoten = 0; knoten < adjazenzmatrix.getKnotenanzahl(); knoten++) 
	    {
	      if (checkArtikulation(knoten))
	      {
	    	  artikulationen.add(knoten);
	      }	        
	    }
	    return artikulationen;
	  }	
}
