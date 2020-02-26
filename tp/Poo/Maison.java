package tp.Poo;

public class Maison extends BHabitable {
	protected int nbetages ;
	protected boolean garage = false, jardin = false, piscine = false;
	protected double superficieHabitable ;
	
	
	
	public Maison(String transac,double prix, String w,Proprietaire p, double sup, String adr, int nbpieces,String id) {
		super(transac, prix, p, w, sup,adr, nbpieces,id);
	}
	public Maison(Proprietaire p)
	{
		super(p);
	}

	//au lieu de les mettre comme param�tres on utilise des setters pour ajouter un jardin,garage, piscine
	public void setGarage() {
		this.garage = true;
	}

	public int getNbetages() {
		return nbetages;
	}

	public boolean isGarage() {
		return garage;
	}

	public boolean isJardin() {
		return jardin;
	}

	public void setNbetages(int nbetages) {
		this.nbetages = nbetages;
	}

	public void setGarage(boolean garage) {
		this.garage = garage;
	}

	public void setJardin(boolean jardin) {
		this.jardin = jardin;
	}

	public void setPiscine(boolean piscine) {
		this.piscine = piscine;
	}

	public boolean isPiscine() {
		return piscine;
	}

	public double getSuperficieHabitable() {
		return superficieHabitable;
	}

	public void setJardin() {
		this.jardin = true;
	}
	public void setSuperficieHabitable(double s){this.superficieHabitable = s;}


	public void setPiscine() {
		this.piscine = true;
	}

	//on affiche toutes les informations du bien
	public void afficher() {
		super.afficher();
		System.out.println("Nombre d'�tages : "+this.nbetages);
		if (jardin) System.out.println("Poss�de un jardin");
		if (garage) System.out.println("Poss�de un garage");
		if (piscine) System.out.println("Poss�de une piscine");
		System.out.println("Superficie : "+this.superficie);
		
	}
	
	public double calculerPrix() {
		double pr=super.prix;
		switch (super.transac) {
		case VENTE:
			Vente v = new Vente();
			pr = v.calculerPrix(this.wilaya, this);
			if( (this.jardin)|| (this.garage)|| (this.piscine))
			{
				pr = pr + ((0.005)*this.prix);
			}
		break;
		case LOCATION :
			Location c = new Location();
			pr = c.calculerPrix(this.wilaya, this);
			if(this.piscine) pr = pr + 50000;
			break;
		case ECHANGE :
			Echange e = new Echange(super.getWilayaEchange());
			pr = e.calculerPrix(this.wilaya, this);
			if( (this.jardin)|| (this.garage)|| (this.piscine))
			{
				pr = pr + ((0.005)*this.prix);
			}
			break;
			
		default : pr =0;
		}
		return pr;
	}
	
	public void afficherP(Proprietaire P) {
		
		System.out.println("Liste des biens de "+P.getNom()+ P.getPrenom()+" :");
		P.afficherBienP();
	}
	

}
