package model;

/*
THEORIE
	Block ist ein zusammenhängender Graph der keine Artikulationen hat und keinen Obergraph mit Artikulationen

ALGORITHMUS:
	Anzahl Komponenten und Anzahl Artikulationen holen - anschließend addieren
*/

public class Bloecke 
{
	private Komponenten komponenten;
	private Artikulationen artikulationen;
	public Bloecke(Adjazenzmatrix a,Komponenten komponenten,Artikulationen artikulationen)
	{
		this.komponenten = komponenten;
		this.artikulationen = artikulationen;
	}
	
	public int berechneBlockAnzahl() 
 	{
 	    int artikulation;
 	    artikulation = komponenten.berechneKomponenten().size() + artikulationen.getArtikulationen().size();

 	    return artikulation;
 	}
}
