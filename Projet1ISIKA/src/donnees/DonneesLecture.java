package donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import donnees.Stagiaire;

public class DonneesLecture {
	private static Stagiaire stagiaire;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileReader donnneesStagiaires = new FileReader("src/Stagiaires.txt");

			BufferedReader br = new BufferedReader(donnneesStagiaires);

			List<Stagiaire> lesStagiaires = new ArrayList<>();
			//stagiaire = null;
			

			//remplir la liste avec les stagiaires
			while (br.ready()) {
				Stagiaire stagiaire =new Stagiaire(null, null, null, null, 0);
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setPromo(br.readLine());
				stagiaire.setAnnee(Integer.parseInt(br.readLine())); //annee est de type int donc on passe à string à travers parse
				lesStagiaires.add(stagiaire);
				br.readLine();
			}
			
			//afficher la liste avec les stagiaires
			for (Stagiaire stage : lesStagiaires) {
				
				System.out.println(stage);
				
			}

			// System.out.println(satgiaire);

			donnneesStagiaires.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
