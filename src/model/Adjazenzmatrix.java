package model;

/*Diese Klasse bietet die Mšglichkeit einen Graphen als Baumstruktur darzustellen
Bei einem ungerichteten Graphen muss man nur eine HŠlfte der Matrix betrachten
*/

public class Adjazenzmatrix 
{
	private int knotenanzahl;
	private int [][] matrix;
	private Komponenten komp;
	
	public Adjazenzmatrix(int knotenanzahl)
	{
		setKnotenanzahl(knotenanzahl);
		//matrix = new int[getKnotenanzahl()][getKnotenanzahl()];
		komp = new Komponenten(this);
	}
	
// -------GETTER  ------------------------------------------------------------------------------------------------	
	public int getKnotenanzahl() 
	{
		return knotenanzahl;
	}
	
	public int[][] getMatrix() 
	{
		return matrix;
	}

// -------SETTER  ------------------------------------------------------------------------------------------------
	public void setKnotenanzahl(int knotenanzahl) 
	{
		if(knotenanzahl >= 2 && knotenanzahl <= 15)
		{
			this.knotenanzahl = knotenanzahl;
			matrix = new int[knotenanzahl][knotenanzahl];
		}
		
		else 
		{
			throw new IllegalArgumentException("Geben Sie eine Knotenanzahl zwischen 2 und 15 ein: " + "FEHLER");
		}		
	}
	
	/*public void setMatrix(int[][] matrix) 
	{
		//matrix.length == getKnotenanzahl() && 
		if(matrix!= null)
		{
			this.matrix = matrix;
		}
		else 
		{
			throw new IllegalArgumentException("Nullreferenz");
		}
		
		
	}*/

// ------- MATRIX AUSGABE F†R TEST ------------------------------------------------------------------------------------------------
/* 	public static void matrixAusgeben(String name, int[][] matrix) 
	{
	    System.out.println(name);
	    for (int s = 0; s < matrix.length; s++) 
	    {
	    	for (int z = 0; z < matrix.length; z++) 
	    	{
	    		System.out.print(matrix[s][z]);
	    	}
	    	System.out.println();

	    }
	    	System.out.println("---------------");
	}*/
 	 	
// ------- KANTE HINZUFUEGEN, LOESCHEN, KANTENZAHL  ----------------------------------------------------------------
 	public void addKante(int zeile, int spalte)
 	{ 
 		if(zeile >=0 && zeile < getKnotenanzahl())
 		{
 			if (spalte >=0 && spalte < getKnotenanzahl()) 
 			{
 				matrix[zeile][spalte] = 1;
			} 
 			else 
 			{
 				throw new IllegalArgumentException("Spalte muss >= 0 und < Knotenanzahl sein!!");
			}
  		}
 		else 
 		{
 			throw new IllegalArgumentException("Zeile muss >= 0 und < Knotenanzahl sein!!");
		}		
 	}
 	
 	
 	public void loescheKante(int zeile, int spalte)
 	{
 		if(zeile >=0 && zeile < getKnotenanzahl())
 		{
 			if (spalte >=0 && spalte < getKnotenanzahl()) 
 			{
 				matrix[zeile][spalte] = 0;
			} 
 			else 
 			{
 				throw new IllegalArgumentException("Spalte muss >= 0 und < Knotenanzahl sein!!");
			}
  		}
 		else 
 		{
 			throw new IllegalArgumentException("Zeile muss >= 0 und < Knotenanzahl sein!!");
		}		 		
 	}
 	
 	public int getKantenzahl(int zeile, int spalte)
 	{
 		if(zeile >=0 && zeile < getKnotenanzahl())
 		{
 			if (spalte >=0 && spalte < getKnotenanzahl()) 
 			{
 				return matrix[zeile][spalte];
			} 
 			else 
 			{
 				throw new IllegalArgumentException("Spalte muss >= 0 und < Knotenanzahl sein!!");
			} 			
 		}
 		else 
 		{
 			throw new IllegalArgumentException("Zeile muss >= 0 und < Knotenanzahl sein!!");
		}		
 	} 	
 
// ------- ARBEITSMETHODEN ------------------------------------------------------------------------------------------
 	public int getKomponenten()
 	{
 		return komp.berechneKomponenten().size();
 	}
 	
}

