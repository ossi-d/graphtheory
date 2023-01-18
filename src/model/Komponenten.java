package model;
import java.util.ArrayList;

/*
THEORIE
	Komponenten einer Matrix sind die Menge aller Knoten die durch einen Weg verbunden sind

ALGORITHMUS:
	Man speichert die unterschiedlichen Zeilen  ab
    Wenn wegMatrix Eintrag 1 hat und Zeilenarray an dieser Position 0
    Dann füge den Knoten bei der ArrayList<Integer> komponenteNeu hinzu
    und setze beim ZeilenArray eine 1 >> einzelKomponenten[spalte] = 1
    Wenn komponenteNeu.size()!= 0 ist wird Komponente geaddet >>  komponenten.add(komponenteNeu)
*/

public class Komponenten 
{
	public Adjazenzmatrix adjazenzmatrix;
	private Wegmatrix wm;
	private ArrayList<ArrayList<Integer>> komponenten;

	public Komponenten(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
		this.wm = new Wegmatrix(adjazenzmatrix); 
	}

	public ArrayList<ArrayList<Integer>> berechneKomponenten() 
	{
		//verschachtelte ARRAYLIST speichert ALLE Komponenten 
		komponenten = new ArrayList<ArrayList<Integer>>(); 
		int[][] wegmatrix = wm.berechneWegMatrix();	
		 
		// speichert zeilenweise die zugehörigen Knoten der jeweiligen Komponenten ab 
		int[] einzelKomponenten = new int[adjazenzmatrix.getKnotenanzahl()]; 
		 
		for (int zeile = 0; zeile < wegmatrix.length; zeile++) 
		{ 
			// weitere ArrayList zum Aufnehmen einer neuen Komponente 
			// >> speichert immer nur 1 Komponente und wird deshalb jedesmal neu erzeugt
			ArrayList<Integer> komponenteNeu = new ArrayList<>();
			 
			for (int spalte = 0; spalte < wegmatrix.length; spalte++) 
			{
				// Wenn ein Eintrag (1)in der Wegmatrix ist und in der ArrayList noch keine 1 steht
				//wenn Array schon auf 1 gesetzt wurde dann befindet sich der Knoten bereits in einer Komponente!!!!
				// ganze Zeile wird geprüft ob Knoten bereits in einer Komponente ist!!!
				if (wegmatrix[zeile][spalte] == 1 && einzelKomponenten[spalte] != 1) //prueft ob eine 1 in der Wegmatrix steht
				{
					// Knoten wird einer Komponente hinzugefügt
					komponenteNeu.add(spalte); 					 
							
					// merkt sich welcher Knoten zu welcher Komponente gehört
					einzelKomponenten[spalte] = 1; 
				}
			}
			// wenn Arraylist komponente nicht 0 ist wird die Komponenten - ArrayList in der verschachtelten ArrayList gespeichert
			if (komponenteNeu.size() != 0)
			{
				komponenten.add(komponenteNeu);
			}
	    }
		// enthält alle Komponenten
	    return komponenten;
	  }
	
	public int getSize()
	{
		return komponenten.size();
	}
	 	 	
	 public boolean checkZusammenhaengend() 
	 {
	    ArrayList<ArrayList<Integer>> komponenten = berechneKomponenten();

	    if (komponenten.size() > 1) // prueft ob mehr als eine Komponente vorhanden ist
	    {
	    	 return false;
	    } 	     
	    return true;
	  }
	 

}


