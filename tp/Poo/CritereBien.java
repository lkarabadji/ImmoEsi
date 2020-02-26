package tp.Poo;
import java.util.*;
//Recherche par Type du bien 
public class CritereBien extends Criteres{

    public int nbElements = 0;
    public int getNbElements()
    {
    return this.nbElements;
    }
    private String typeBien ;

    public void setTypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {   
        SortedSet<Bien> resultats = new TreeSet<>();
        //Bien[] b1 = new Bien[500];
        int j = 0;


        Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            Bien b = it.next();
            if(b.getClass().getSimpleName().equalsIgnoreCase(typeBien))
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
        
    }
}