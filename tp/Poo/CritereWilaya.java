package tp.Poo;
import java.util.*;
public class CritereWilaya extends Criteres{
    public int nbElements = 0;
    public int getNbElements()
    {
    return this.nbElements;
    }
    private String w;
    public void setW(String nv)
    {
        this.w = nv;
    }
    public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {
        SortedSet<Bien> resultats = new TreeSet<>();
        int j = 0 ;
        Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            Bien b = it.next();
            if(b.getwilaya().toString().equalsIgnoreCase(w))
            {
                resultats.add(b);
                j++;
            }
            
			
		}
        this.nbElements = j;
        return resultats;
        
    }
}