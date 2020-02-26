package tp.Poo;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;





//Recherche par nombre de pieces Minimale
public class CritereNbrPieces extends Criteres
{
    private int nbrPieces = 0;
    public void setNbrPieces(int n){
        this.nbrPieces = n;
    }
    public int nbElements = 0;
    public int getNbElements()
    {
    return this.nbElements;
    }

     public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {   
        SortedSet<Bien> resultats = new TreeSet<>();
        int j = 0;

		Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            BHabitable b = (BHabitable)it.next();
            if(b.getnbpieces() >= nbrPieces)
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
    }
    //public Bien[] rechercher(Bien[] b){}
}

