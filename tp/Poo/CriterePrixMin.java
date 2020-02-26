package tp.Poo;

import java.util.*;
public class CriterePrixMin extends Criteres{

    public int nbElements = 0;
    private double prixMin = 0;

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

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
            Bien b = it.next();
            System.out.println("classe : " + b.getClass().getSimpleName());
            if(b.calculerPrix() >= prixMin)
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
    }

}