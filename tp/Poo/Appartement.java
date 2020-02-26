package tp.Poo;

public class Appartement extends BHabitable {
	protected int etage ;
	protected String emplacement ;
	protected boolean ascenseur = false ;
	Typeappart typeAppart ;
	
	
	
	public Appartement(String transac,double prix,Proprietaire p, String w, double sup,String adr, int nbpieces,int etage,String id) {
		super(transac, prix, p, w, sup,adr, nbpieces,id);
		this.etage = etage ;
	}

	public void setTypeAppart(String appart) {
		this.typeAppart = Typeappart.valueOf(appart);
	}

	public void setAscenseur() {
		this.ascenseur = true;
	}

	
	public void afficher() {
		super.afficher();
		System.out.println("Etage : "+this.etage);
		if(ascenseur) System.out.println("Possede un ascenseur");
		else System.out.println("Abscence d'ascenseur !");
		System.out.println("Emplacement : "+this.emplacement);
		System.out.println("Type d'appaartement : "+this.typeAppart);
	
	}
	
	public double calculerPrix() {
		double pr=super.prix;
		switch (super.transac) {
		case VENTE:
			Vente v = new Vente();
			pr = v.calculerPrix(this.wilaya, this);
			if((etage>=0)&&(etage<=2))
			{
				pr = pr + 50000;
			}
		break;
		case LOCATION :
			Location c = new Location();
			pr = c.calculerPrix(this.wilaya, this);
			if((etage>=0)&&(etage<=2)) 
				pr = pr + 5000;
			if((etage>=6)&&!(ascenseur)) 
			{
				pr = pr - (500*(this.superficie));
			}
			break;
		case ECHANGE :
			Echange e = new Echange(super.WilayaEchange);
			
			pr = e.calculerPrix(this.wilaya, this);
			break;
			
		default : pr =0;
		
		}
		return pr;
	}
	
	
	public void afficherP(Proprietaire P) {
		
		System.out.println("Liste des biens de "+P.getNom()+ P.getPrenom()+" :");
		P.afficherBienP();
	}

	public int getEtage() {
		return etage;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public boolean getAscenseur() {
		return ascenseur;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public boolean isAscenseur() {
		return ascenseur;
	}

	public void setAscenseur(boolean ascenseur) {
		this.ascenseur = ascenseur;
	}

	public void setTypeAppart(Typeappart typeAppart) {
		this.typeAppart = typeAppart;
	}

	public Typeappart getTypeAppart() {
		return typeAppart;
	}
}
