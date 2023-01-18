package model;

/*
THEORIE
	Aus der Distanzmatrix kann abgelesen werden, wieviele Kanten zwischen Punkt A nach Punkt B liegen 
 	Ist der Eintrag unendlich existiert kein Weg.

ALGORITHMUS:
	Erstelle eine Distanzmatrix 
    Trage in der Distanzmatrix überall dort wo "0" steht "-1" für unendlich ein (Distanz)
   [Verschachtelte Schleife so lange: Durchgang < Kontenanzahl-1]
       Erstellen der Matrix A^1 == POTENZMATRIX
       Prüfung ob in der PotenzMatrix  eine Änderung (>=1) im Vergleich zur Distanzmatrix ist
       Wenn ja, dann dort die Zahl der aktuell berechneten Potenz eingetragen 
       Wenn update dann Änderung auf true 
       checken ob Matrix voll ist
   [Schleifenende]
*/

public class Distanzmatrix 
{
	private Adjazenzmatrix adjazenzmatrix;
	private int[][] distanzMatrix;
	private boolean aenderung;
	private boolean voll;
	
	public Distanzmatrix(Adjazenzmatrix a)
	{
		this.adjazenzmatrix = a;
		this.aenderung = true;
	 	this.voll = false;
	}

//-------- METHODE PRÜFT OB WEGMATRIX VOLL IST --------------------------------------	
	private boolean checkVoll()
	{
		for(int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++)
	    {
			for(int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++)
	        {
				if(distanzMatrix[zeile][spalte] == 0)
	            {
					return false;
	            }
	        }
	    }
		return true;
	}

//-------- METHODE BERECHNET DISTANZMATRIX -----------------------------------------------	
	public int[][] berechneDistanzMatrix() 
	{
		MatrizenMultiplikation multipliziere = new MatrizenMultiplikation(adjazenzmatrix);
		distanzMatrix = new int[adjazenzmatrix.getKnotenanzahl()][adjazenzmatrix.getKnotenanzahl()];

		for (int k = 0; k < adjazenzmatrix.getKnotenanzahl(); k++) 
		{
			aenderung = false;
	 	 	voll = true;
			int[][] potenzMatrix = multipliziere.potenzieren(k + 1); // k+1 weil ja sonst A^0

			for (int zeile = 0; zeile < adjazenzmatrix.getKnotenanzahl(); zeile++) 
			{
				for (int spalte = 0; spalte < adjazenzmatrix.getKnotenanzahl(); spalte++) 
				{					 
					if (distanzMatrix[zeile][spalte] == 0 && potenzMatrix[zeile][spalte] >= 1)
					{
						 // Es wird immer die aktuell berechnete Potenz eingetragen wenn diese != 0 ist 
						distanzMatrix[zeile][spalte] = k + 1; 
						aenderung = true;
					}	     
					  
					if (zeile == spalte)
					{
						distanzMatrix[zeile][spalte] = 0; 
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
	    return distanzMatrix;
	  }

}
