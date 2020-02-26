package tp.Poo;

import java.util.*;

public class CritereTransaction extends Criteres{
    public int nbElements;

    private String transaction;
    public void setTransaction(String t)
    {
        this.transaction = t;
    }
    public int getNbElements()
    {
    return this.nbElements;
    }
    public SortedSet<Bien> rechercher(SortedSet<Bien> bList)
    {
        SortedSet<Bien> resultats = new TreeSet<>();
        int j = 0;
        Transaction tr = Transaction.valueOf(this.transaction.toUpperCase());
        Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
            Bien b = it.next();
            if(b.getTransaction()  == tr)
            {
                resultats.add(b);
                j++;
            }
            
			
        }
        System.out.println(j);
        return resultats;

    }

}