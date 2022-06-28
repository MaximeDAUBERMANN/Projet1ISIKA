package donnees;

public class Stagiaire {
	
	//*****Déclaration des attributs****
	
	private String nom;
	private String prenom;
	private String departement;
	private String promo;
	private String annee;
	
	// ****Initialisation des attributs ****
	public static final int TAILLE_nom = 21;
	public static final int TAILLE_prenom = 20;
	public static final int TAILLE_departement = 2;
	public static final int TAILLE_promo = 11;
	public static final int TAILLE_annee = 4;
	
	//*****Constructeur*****
	public Stagiaire(String nom, String prenom, String departement, String promo, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
	}
	
	
	//*****Getters & Setters*****
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
	
	//**** Affichage*****
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promo=" + promo
				+ ", annee=" + annee + "]";
	}
	
	//****Agrandir les attributs pour avoir tous la meme taille*****
	public String agrandirNom() {
		String nomLong = "";

		if (nom.length()<= TAILLE_nom) {
			nomLong = this.nom;
		
		for (int i = nom.length(); i<TAILLE_nom ; i++) {
			nomLong += " ";
		}
		} else {
			nomLong= this.nom.substring(0,TAILLE_nom);
		}
		return nomLong;
	}
	public String agrandirPrenom() {
		String prenomLong = "";

		if (prenom.length()<= TAILLE_prenom) {
			prenomLong = this.prenom;
		
		for (int i = prenom.length(); i<TAILLE_prenom ; i++) {
			prenomLong += " ";
		}
		} else {
			prenomLong= this.prenom.substring(0,TAILLE_prenom);
		}
		return prenomLong;
	}

	public String agrandirPromo() {
		String promoLong = "";

		if (promo.length()<= TAILLE_promo) {
			promoLong = this.promo;
		
		for (int i = promo.length(); i<TAILLE_promo ; i++) {
			promoLong += " ";
		}
		} else {
			promoLong= this.promo.substring(0,TAILLE_promo);
		}
		return promoLong;
	}
	
	public String agrandirDepartement() {
		String departementLong = "";

		if (departement.length()<= TAILLE_departement) {
			departementLong = this.departement;
		
		for (int i = departement.length(); i<TAILLE_departement; i++) {
			departementLong += " ";
		}
		} else {
			departementLong= this.departement.substring(0,TAILLE_departement);
		}
		return departementLong;
	}
	
	public String agrandirAnnee() {
		String anneeLong = "";

		if (annee.length()<= TAILLE_annee) {
			anneeLong = this.annee;
		
		for (int i = annee.length(); i<TAILLE_annee; i++) {
			anneeLong += " ";
		}
		} else {
			anneeLong= this.annee.substring(0,TAILLE_annee);
		}
		return anneeLong;
	}
	}
