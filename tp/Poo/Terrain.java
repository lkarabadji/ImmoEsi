package tp.Poo;
//import java.util.Scanner;

import java.util.Scanner;

public class Terrain extends Bien {
	String StatutJuridique;
	int NbFacades;


	public void setStatutJuridique(String statutJuridique) {
		StatutJuridique = statutJuridique;
	}

	public void setNbFacades(int nbFacades) {
		NbFacades = nbFacades;
	}

	public String getStatutJuridique() {
		return StatutJuridique;
	}

	public int getNbFacades() {
		return NbFacades;
	}

	public Terrain(String transac, double prix, String w, Proprietaire p, double sup, String adr, int nbfac, String id) {
		super(transac, prix, p, w, sup, adr,id);
		this.NbFacades = nbfac;
	}
	public Terrain( Proprietaire p)
	{
		super(p);
		System.out.println("Nombre de facades :");
		Scanner sc = new Scanner(System.in);
		this.NbFacades = sc.nextInt();
	}
	public void afficher() { 
		super.afficher();
		System.out.println("Statut juridique : "+this.StatutJuridique);
		System.out.println("Nombre de fa�ades : "+this.NbFacades);
	}
	
	public double calculerPrix() {
		double pr=super.prix;
		switch (super.transac) {
		case VENTE:
			Vente v = new Vente();
			pr = v.calculerPrix(this.wilaya, this);
			if((this.NbFacades)>1)
			{
				pr = pr + (((0.005)*((this.NbFacades)-1))*(this.prix));
			}
		break;
		case LOCATION :
			Location c = new Location();
			pr = c.calculerPrix(this.wilaya, this);
			break;
		case ECHANGE :
			Echange e = new Echange(super.WilayaEchange);
			pr = e.calculerPrix(this.wilaya, this);
			if((this.NbFacades)>1)
			{
				pr = pr + (((0.005)*((this.NbFacades)-1))*(this.prix));
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

