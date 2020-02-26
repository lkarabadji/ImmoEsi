package tp.Poo;
import java.util.*;
import java.util.Scanner;
public class CritereSuperficieMinimale extends Criteres{

    public int nbElements = 0;
    public int getNbElements()
    {
    return this.nbElements;
    }
    private Double superficie = 0d;
    public  void setSuperficie(Double s)
    {
        this.superficie = s;
    }
    public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {
        SortedSet<Bien> resultats = new TreeSet<>();
        Bien[] b2 = new Bien[500];
        int j = 0;
        System.out.println("Veuillez saisir la Superficie du bien Minimale ");
        Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            Bien b = it.next();
            
            if(b.getsuperficie() >= this.superficie)
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
    }
    

}