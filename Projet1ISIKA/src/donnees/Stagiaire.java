package donnees;

public class Stagiaire {
	private String nom;
	private String prenom;
	private String departement;
	private String promo;
	private int annee;
	
	public static final int TAILLE_nom = 21;
	public static final int TAILLE_prenom = 20;
	public static final int TAILLE_departement = 2;
	public static final int TAILLE_promo = 11;
	public static final int TAILLE_annee = 4;
	
	public Stagiaire(String nom, String prenom, String departement, String promo, int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
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
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public static int getTailleNom() {
		return TAILLE_nom;
	}
	public static int getTaillePrenom() {
		return TAILLE_prenom;
	}
	public static int getTailleDepartement() {
		return TAILLE_departement;
	}
	public static int getTaillePromo() {
		return TAILLE_promo;
	}
	public static int getTailleAnnee() {
		return TAILLE_annee;
	}
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promo=" + promo
				+ ", annee=" + annee + "]";
	}
	
	
	
	}
