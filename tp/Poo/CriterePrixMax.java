package tp.Poo;

import java.util.*;
public class CriterePrixMax extends Criteres{
    public int nbElements = 0;
    public int getNbElements()
    {
    return this.nbElements;
    }
    private double prixMax = 0d;

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {
        SortedSet<Bien> resultats = new TreeSet<>();
        int j = 0;

        Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            Bien b = it.next();
            if(b.calculerPrix() <= prixMax)
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
    }

}