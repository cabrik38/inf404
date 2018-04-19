
public enum EtatAutomate { E_INIT, E_FIN, E_ENTIER, E_EGAL, E_EXCL, E_INF, E_SUP, E_1I, E_2E, E_2L, E_2S, E_3O, E_3U, E_4P, E_4U, E_4L, E_4S, E_5J, E_5U, E_5M };

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
		return _lexeme_en_cours.nature == FIN_SEQUENCE;
	}

	public void arreter() {
		_fichier.arreter();
	}

	private void reconnaitre_lexeme() {
		EtatAutomate etat = E_INIT;

		while (est_separateur(_fichier.caractere_courant())) {
			_fichier.avancer();
    }

    while (etat != E_FIN) {
			switch (etat) {
				case E_INIT:
					if (_fichier.fin_de_sequence()) {
						_lexeme_en_cours.nature = FIN_SEQUENCE;
						etat = E_FIN;
					} else {
						switch (_fichier.caractere_courant()) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								_lexeme_en_cours.nature = ENTIER;
								_lexeme_en_cours.valeur = _fichier.caractere_courant() - '0';
								etat = E_ENTIER;
								break;
							case 'A':
							case 'a':
								_lexeme_en_cours.nature = A;
								etat = E_FIN;
								break;
							case 'B':
							case 'b':
								_lexeme_en_cours.nature = B;
								etat = E_FIN;
								break;
							case '+':
								_lexeme_en_cours.nature = PLUS;
								etat = E_FIN;
								break;
							case '-':
								_lexeme_en_cours.nature = MOINS;
								etat = E_FIN;
								break;
							case '*':
								_lexeme_en_cours.nature = MUL;
								etat = E_FIN;
								break;
							case '/':
								_lexeme_en_cours.nature = DIV;
								etat = E_FIN;
								break;
							case '(':
								_lexeme_en_cours.nature = PARO;
								etat = E_FIN;
								break;
							case ')':
								_lexeme_en_cours.nature = PARF;
								etat = E_FIN;
								break;
							case '{':
								_lexeme_en_cours.nature = ACCO;
								etat = E_FIN;
								break;
							case '}':
								_lexeme_en_cours.nature = ACCF;
								etat = E_FIN;
								break;
							case '=':
								etat = E_EGAL;
								break;
							case '!':
								etat = E_EXCL;
								break;
							case '<':
								etat = E_INF;
								break;
							case '>':
								etat = E_SUP;
								break;
							case 'i':
								etat = E_1I;
								break;
							case 'e':
								etat = E_2E;
								break;
							case 'o':
								etat = E_3O;
								break;
							case 'p':
								etat = E_4P;
								break;
							case 'j':
								etat = E_5J;
								break;
							default:

						}
						_fichier.avancer();
					}
					break;
				case E_ENTIER:
					break;
				case E_EGAL:
					break;
				case E_EXCL:
					break;
				case E_INF:
					break;
				case E_SUP:
					break;
				case E_1I:
					break;
				case E_2E:
					break;
				case E_2L:
					break;
				case E_2S:
					break;
				case E_3O:
					break;
				case E_3U:
					break;
				case E_4P:
					break;
				case E_4U:
					break;
				case E_4L:
					break;
				case E_4S:
					break;
				case E_5J:
					break;
				case E_5U:
					break;
				case E_5M:
					break;
			}
		}
	}

	private boolean est_separateur(char c) {
			return c == ' ' || c == '\t' || c == '\n';
	}
}
