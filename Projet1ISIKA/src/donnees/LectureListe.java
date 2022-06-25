package donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import donnees.Stagiaire;





public class LectureListe {
	private static Stagiaire stagiaire;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileReader donnneesStagiaires = new FileReader("src/Stagiaires.txt");

			BufferedReader br = new BufferedReader(donnneesStagiaires);

			List<Stagiaire> lesStagiaires = new ArrayList<>();
			//stagiaire = null;
			
			int i=0;
			//remplir la liste avec les stagiaires
			while (i<20) {
				Stagiaire stagiaire =new Stagiaire(null, null, null, null, null);
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setPromo(br.readLine());
				stagiaire.setAnnee(br.readLine()); //annee est de type int donc on passe à string à travers parse
				lesStagiaires.add(stagiaire);
				br.readLine();
				i++;
			}
			
			//afficher la liste avec les stagiaires
			for (Stagiaire stage : lesStagiaires) {
				
				System.out.println(stage);
				
			}

			// System.out.println(satgiaire);

			donnneesStagiaires.close();
			RandomAccessFile raf = new RandomAccessFile("src/arbreStagiaires.bin", "rw");
			for (Stagiaire stage : lesStagiaires) {
				raf.writeChars(stage.agrandirNom());
				raf.writeChars(stage.agrandirPrenom());
				raf.writeChars(stage.getDepartement());
				raf.writeChars(stage.agrandirPromo());
				raf.writeChars(stage.getAnnee());

			}
			raf.seek(0);
			for (int j =0; j < raf.length()/Stagiaire.TAILLE_nom; j++) { //taille nom est le plus grand
				String stage = " ";
				//je lis les x caractères de l'attribut string
				for (int k=0; k < Stagiaire.TAILLE_nom ; k++) {
					stage += raf.readChar();
				}
				for (int k=0; k < Stagiaire.TAILLE_prenom ; k++) {
					stage += raf.readChar();
				}
				for (int k=0; k < Stagiaire.TAILLE_departement ; k++) {
					stage += raf.readChar();
				}
				stage += " ";
				for (int k=0; k < Stagiaire.TAILLE_promo ; k++) {
					stage += raf.readChar();
				}
				for (int k=0; k < Stagiaire.TAILLE_annee ; k++) {
					stage += raf.readChar();
				}
				
			
						
			System.out.println("Stagiaire" + j+ " : "+ stage);
		} 
			raf.close();
			}catch (IOException e) {
			e.printStackTrace();
		}
		

		

		
		
	}

}
