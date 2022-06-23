package fr.isika.cda18.projet1.stagiaires;

public class Stagiaires {

	private String nom;
	private String prenom;
	private String departement;
	private String promo;
	private String annee;

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

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public Stagiaires(String nom, String prenom, String departement, String promo, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
	}

	@Override
	public String toString() {
		return "Stagiaires [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promo=" + promo
				+ ", annee=" + annee + "]";
	}
//bonjour
}
