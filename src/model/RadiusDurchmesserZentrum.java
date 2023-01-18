package model;
import java.util.ArrayList;

/*
THEORIE
	RADIUS ist das Minimum der Exzentrizit�ten - muss mindestens 1/2 und maximal gleich dem Durchmesser sein
	DURCHMESSER ist das Maximum aller Exzentrizit�ten
	ZENTRUM sind alle Knoten die gleich dem Radius sind

ALGORITHMUS:
	RADIUS: Es wird immer die niedrigste Exzentrizit�t gespeichert 
 	ODER Bedingung (radius 0) damit man auch mit Startwert 0 in die Schleife kommt
 	
 	DURCHMESSER: Es wird immer die h�chste Exzentrizit�t gespeichert
 	
 	ZENTRUM: Alle Knoten gleich gro� dem Radius werden gespeichert
*/

public class RadiusDurchmesserZentrum 
{
	private Exzentrizitaeten exzentrititaeten;

	public RadiusDurchmesserZentrum(Exzentrizitaeten exzentrititaeten)
	{
		this.exzentrititaeten = exzentrititaeten;
	}
	
	// niedrigsteExzentrizit�t
	public int berechneRadius() 
	{
 	    int[] exzentritaet = exzentrititaeten.berechneExzentrizitaeten();
 	    int radius = exzentritaet[0];

 	    for (int s = 0; s < exzentritaet.length; s++) 
 	    {
 	    	if (exzentritaet[s] < radius && exzentritaet[s] > 0 || radius == 0)
 	    	{
 	    		radius = exzentritaet[s];
 	    	} 	        
 	    }
 	    return radius;
 	}

	// h�chste Exzentrizit�t
	public int berechneDurchmesser() 
	{ 	
		int[] exzentritaet = exzentrititaeten.berechneExzentrizitaeten();
 	    int durchmesser = exzentritaet[0];

 	    for (int s = 0; s < exzentritaet.length; s++) 
 	    {
  	    	if (exzentritaet[s] > durchmesser)
 	    	{
 	    		durchmesser = exzentritaet[s];
 	    	}
  	    }
 	    return durchmesser;
 	 }
 	  
 	 // menge der Knoten die gleich gro� dem Radius sind
 	 public ArrayList<Integer> berechneZentrum() 
 	 {
 	 
 		 ArrayList<Integer> zentrum = new ArrayList<Integer>();
 		 int[] exzentritaet = exzentrititaeten.berechneExzentrizitaeten();
 		 int radius = berechneRadius();

 		 for (int s = 0; s < exzentritaet.length; s++) 
 		 { 	 	    	
 			 if (exzentritaet[s] == radius && exzentritaet[s] > 0)
 			 {
 				zentrum.add(s);
 			 }
 		 }
 		 return zentrum;
 	  }
}
