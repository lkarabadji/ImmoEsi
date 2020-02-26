package tp.Poo;

import java.util.Iterator;
import java.util.TreeSet;


public class Proprietaire  implements Comparable<Proprietaire>{
	private String nom,prenom, email, adresse, tel,id;
	private TreeSet<Bien> listeBienP = new TreeSet<Bien>();
	private int NbBiens=0;
	

	public Proprietaire(String nom, String prenom, String email, String adresse, String tel,String id) {
 		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
		this.id = id;

	}
	public int getNbBiens(){return this.NbBiens;}
	public String getNumTel(){return  this.tel;}
	public TreeSet<Bien> getListeBienP(){return  this.listeBienP;}

	public int compareTo(Proprietaire p) {
		if (((this.nom).compareTo(p.getNom() )!= 0 )||( (this.prenom).compareTo(p.getPrenom()) ) != 0){
			return (  (this.nom).compareToIgnoreCase(p.getNom() ));
		}
		else {
			 if (this.equals(p)) 
				 return 0;
			 else return -1;
		}
	}
	/**************************************/
	public boolean equals(Object b) {
		if (((this.nom).compareToIgnoreCase(((Proprietaire)b).getNom()) == 0) &&((this.prenom.compareToIgnoreCase(((Proprietaire)b).getPrenom())) == 0))
			return true;
		else return false;

	}
	 public  Boolean ajouterBien(Bien b) {
		 return  listeBienP.add(b);
		//this.NbBiens ++ ;
 	}
	
	public void afficherCoord() {
		System.out.println("Nom proprietaire : "+this.nom);
		System.out.println("Prenom proprietaire : "+this.prenom);
		System.out.println("Email : "+this.email);
		System.out.println("Adresse : "+this.adresse);
		System.out.println("Numero de telephone : "+this.tel);
	}
	
	public void afficherBienP() {
		Iterator<Bien> iter = listeBienP.iterator();
		while(iter.hasNext()) {
			Bien b = iter.next();
			b.afficherSansDetails();
			}
	}
	public  Boolean supprimerBienP(Bien b) {
		return listeBienP.remove(b);

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public  String getAdresse(){return  this.adresse;}
	public String getEmail(){return  this.email;}
	public String getTel(){return  this.tel;}
	public String getId() {
		return this.id;
	}
}
