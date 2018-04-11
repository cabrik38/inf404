
public enum EtatAutomate { E_INIT, E_FIN, E_INF, E_1I, E_2E, E_2L, E_2S, E_3O, E_3U, E_4P, E_4U, E_4L, E_5J, E_5U, E_5M };

public class Analyseur {
	Fichier _fichier;
	Lexeme _lexeme_en_cours;
	
	public Analyseur(String nom_fichier) {
		demarrer(nom_fichier);
	}
	
	public void demarrer(String nom_fichier) {
		_fichier = new Fichier();
		_fichier.demarrer(nom_fichier);
		avancer();
	}
	
	public void avancer() {
		reconnaitre_lexeme();
	}
	
	public Lexeme lexeme_courant() {
		return _lexeme_en_cours;
	}
	
	public int fin_de_sequence() {
		return lexeme_en_cours.nature == FIN_SEQUENCE;
	}
	
	public void arreter() {
		_fichier.arreter();
	}
	
	private void reconnaitre_lexeme() {
		EtatAutomate etat = E_INIT;
		
		while (est_separateur(_fichier.caractere_courant())) {
			_fichier.avancer();
     	}

     	lexeme_en_cours.chaine[0] = '\0';

      	while (etat != E_FIN) {
			
		}
	}
}
