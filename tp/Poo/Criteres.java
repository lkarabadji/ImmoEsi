package tp.Poo;

import java.util.SortedSet;
import java.util.TreeSet;
public abstract class Criteres{
     protected int nbElements;
     abstract public SortedSet<Bien> rechercher(SortedSet<Bien> bList);
}