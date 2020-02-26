package tp.Poo;

public class Echange extends Vente implements CalculerPrix {
	Wilaya EchangeWilaya;
	
	
	public Echange(Wilaya echangeWilaya)
	{
		this.EchangeWilaya = echangeWilaya;
	}
	
	public Wilaya getEchangeWilaya() {
		return EchangeWilaya;
	}


	public double calculerPrix(Wilaya w, Bien b) {
		double finPrix=0;
		finPrix = super.calculerPrix(w, b);
		//on doit comparer les deux wilayas pour faire l'instruction suivante //
		if(b.wilaya.compareTo(EchangeWilaya)!=0){
			finPrix = finPrix + ((0.0025)*b.prix);
			}
		return (finPrix);
	}
}
