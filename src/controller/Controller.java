package controller;
import java.util.ArrayList;

import model.*;
import view.*;

/*1.) Einstiegspunkt in das Programm ist die Klasse "KnotenEingabeFenster"
welche den Controller aufruft.
2.) Der Controller erzeugt das Hauptfenster und initialisiert die Adjazenzmatrix mit der zugehoerigen Knotenanzahl*/

public class Controller 
{
	// Instanzvariablen fuer hauptfenster und matrix
	 private Hauptfenster hauptfenster;
	 private Adjazenzmatrix adjazenzmatrix;
	 // KONSTANTE 
	 private static final Controller INSTANZ = new Controller();
	 
	 private Wegmatrix wegmatrix;
	 private Distanzmatrix distanzmatrix;
	 private Komponenten komponente;
	 private Artikulationen artikulation;
	 private Bruecken bruecke;
	 private Bloecke block;
	 private Exzentrizitaeten exzentrizitaet;
	 private RadiusDurchmesserZentrum rdz;
	
	 // Adjazenzmatrix wird im Konstruktor erzeugt 
	 public Controller() 
	 {
		 adjazenzmatrix = new Adjazenzmatrix(15); 
		 wegmatrix = new Wegmatrix(adjazenzmatrix);
		 distanzmatrix = new Distanzmatrix(adjazenzmatrix);
		 komponente = new Komponenten(adjazenzmatrix);
		 artikulation = new Artikulationen(adjazenzmatrix);
		 bruecke = new Bruecken(adjazenzmatrix);
		 block = new Bloecke(adjazenzmatrix, komponente, artikulation);
		 exzentrizitaet = new Exzentrizitaeten(adjazenzmatrix);
		 rdz = new RadiusDurchmesserZentrum(exzentrizitaet);
	 }
	 
// -------------- GETTER -------------------------------------------------------------
	 public static Controller getInstanz() 
	 {
	   return INSTANZ;
	 }
	  
	 public Hauptfenster getHauptfenster() 
	 {
	    return hauptfenster;
	 }
	  
	 public int getKnotenanzahl() 
	 {
	    return adjazenzmatrix.getKnotenanzahl();
	 }
	 
	 public Adjazenzmatrix getAdjazenzmatrix() 
	 {
		return adjazenzmatrix;
	 }

	// -------------- SETTER -------------------------------------------------------------
	 public void setHauptfenster(Hauptfenster hauptfenster) 
	 {
		 if (hauptfenster != null)
		 {
			 this.hauptfenster = hauptfenster; 
		 }
		   
		 else 
		 {
			 throw new IllegalArgumentException("Nullreferenz für Hauptfenster!");
		 }  
	 }
	 
	public void setAdjazenzmatrix(Adjazenzmatrix adjazenzmatrix) 
	{
		if(adjazenzmatrix != null)
		{
			this.adjazenzmatrix = adjazenzmatrix;
		}
		else 
		{
			throw new IllegalArgumentException("Nullreferenz für Adjazenzmatrix!");
		}
	}
		  

// ------- PRÜFUNG ERFOLGT IN ADJAZENZMATRIX -----------------------------------------------------------------------
     public void setKnotenanzahl(int knotenanzahl) 
	 {
		   adjazenzmatrix.setKnotenanzahl(knotenanzahl);

	 } 
     
// ------- KANTE HINZUFUEGEN, LOESCHEN, KANTENZAHL  ----------------------------------------------------------------
    public void addKante(int spalte, int zeile)
  	{
    	 adjazenzmatrix.addKante(spalte, zeile);
  	}
    
    public void getKantenzahl(int spalte, int zeile)
  	{
    	 adjazenzmatrix.getKantenzahl(spalte, zeile);
  	}
    
    public void loescheKante(int spalte, int zeile)
  	{
    	 adjazenzmatrix.loescheKante(spalte, zeile);
  	}
    
// ------- WEGMATRIX BERECHNEN  -----------------------------------------------------------------------------------
    
    public int [][] berechneWegMatrix()
    {
    	return wegmatrix.berechneWegMatrix();
    }
    
// ------- DISTANZMATRIX BERECHNEN  -----------------------------------------------------------------------------------
    public int [][] berechneDistanzMatrix()
    {
    	return distanzmatrix.berechneDistanzMatrix();
    }
      
 	
// ------- BERECHNUNGEN ---------------------------------------------------------------------------------------------
 	public void berechnungenAusgabe()
 	{
 		StringBuilder berechnungen = new StringBuilder();
 		ArrayList<ArrayList<Integer>> komponenten = komponente.berechneKomponenten();
 		ArrayList<Integer> artikulationen = artikulation.getArtikulationen();
 		int[][] bruecken = bruecke.getBruecken();
 		int[] exzentrizitaeten = exzentrizitaet.berechneExzentrizitaeten();
 		ArrayList<Integer> zentrum = rdz.berechneZentrum(); 
 		
//----------------- AUSGABE KOMPONENTEN -----------------------------------------------
 		berechnungen.append("\n");  
 		int i = 0;
		  for( @SuppressWarnings("unused") ArrayList<Integer> komp : komponenten)
		  {
			  berechnungen.append(" Komponente ").append(i+1).append(": [");		   
			  for( Integer knoten : komponenten.get(i))
			  {
				  berechnungen.append(knoten+1);
				  if((komponenten.get(i).indexOf(knoten)+1) != komponenten.get(i).size())
				  {
					  berechnungen.append(", ");
				  }		     
			  }
			  berechnungen.append("] ");
			  i++;
		  }		  
 		  
//----------------- AUSGABE ARTIKULATIONEN -----------------------------------------------		  
		  if(artikulationen.size() == 0) 
		  {
			  berechnungen.append("\n     Artikulationen: nicht vorhanden\n");
		  }
		  else 
		  {
	 		  berechnungen.append("\n     Artikulationen: Knoten[");
	 		  for( Integer artikulation : artikulationen)
	 		  {
	 			  berechnungen.append(artikulation+1);
	 			  if((artikulationen.indexOf(artikulation)+1) != artikulationen.size())
	 			  {
	 				 berechnungen.append(", ");
	 			  }	 		    
	 		  }
	 		  berechnungen.append("]\n");
		  }

//----------------- AUSGABE BRUECKEN -----------------------------------------------			  
		  berechnungen.append("     Brücken: ");
 		  
 		  int temp = 0;
 		  for (int zeile = 0; zeile < bruecken.length; zeile++) 
 			  for (int spalte = 0; spalte < bruecken.length; spalte++) 
 				  if(bruecken[zeile][spalte] == 1)
 				  {
 					  bruecken[spalte][zeile] = 0;
 					  temp = 1;
 					  berechnungen.append(" Kante[").append(zeile+1).append(", ").append(spalte+1).append("] ");
 				  }
 		 if(temp == 0) berechnungen.append("nicht vorhanden");
 		  
 		  berechnungen.append("\n");

//----------------- AUSGABE BLOECKE ------------------------------------------------ 		  
 		 berechnungen.append("     Blöcke: "+block.berechneBlockAnzahl()).append("\n");
 		
//----------------- AUSGABE EXZENTRIZITAETEN ------------------------------------------------  		 
 		if(komponente.checkZusammenhaengend())
		  {
		  berechnungen.append("     Exzentrizitaeten: ");
		  
		  for(int j =0; j < exzentrizitaeten.length; j++)
		  {
			  berechnungen.append(" Knoten ").append(j+1).append(": ").append(exzentrizitaeten[j]).append("   ");
		    
		  }
		  
		  berechnungen.append("\n     Radius: ").append(rdz.berechneRadius());
		  berechnungen.append("\n     Durchmesser: ").append(rdz.berechneDurchmesser());
		  
		  berechnungen.append("\n     Zentrum: Knoten[");
		  
		  
		  for(int k = 0; k < zentrum.size(); k++)
		  {if(k+1 != zentrum.size())
		    berechnungen.append(zentrum.get(k)+1).append(",");
		  else
		    berechnungen.append(zentrum.get(k)+1);
		  }
		   berechnungen.append("]").append("\n");
		  // berechnungen.append("Anderer Durchmesser" + wegmatrix.getDurchmesser());
		  }
		  else
		  berechnungen.append("     Exzentrizitaeten, Radius, Durchmesser und Zentrum nicht berechnet weil");
 		 if(komponente.checkZusammenhaengend())
		  {
			  berechnungen.append("     Graph ist zusammenhängend"); 
		  }
		  else 
		  {
			  berechnungen.append("\n     Graph nicht zusammenhängend ist");
		  }
 		  hauptfenster.getBerechnungen().setText(berechnungen.toString());
 			
 		} 	
}


