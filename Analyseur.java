
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
							case 'a':
								_lexeme_en_cours.nature = A;
								etat = E_FIN;
								break;
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
								_lexeme_en_cours.nature = ERREUR;
								etat = E_FIN;
						}
						_fichier.avancer();
					}
					break;
				case E_ENTIER:
					if (_fichier.caractere_courant() < '0' || _fichier.caractere_courant() > '9') {
						etat = E_FIN;
					} else {
						_lexeme_en_cours.valeur = _lexeme_en_cours.valeur * 10 + _fichier.caractere_courant() - '0';
						_fichier.avancer();
					}
					break;
				case E_EGAL:
					if (_fichier.caractere_courant() == '=') {
						_lexeme_en_cours.nature = EGAL;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = AFFECT;
						etat = E_FIN;
					}
					break;
				case E_EXCL:
					if (_fichier.caractere_courant() == '=') {
						_lexeme_en_cours.nature = DIFF;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_INF:
					if (_fichier.caractere_courant() == '=') {
						_lexeme_en_cours.nature = INFE;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = INF;
						etat = E_FIN;
					}
					break;
				case E_SUP:
					if (_fichier.caractere_courant() == '=') {
						_lexeme_en_cours.nature = SUPE;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = SUP;
						etat = E_FIN;
					}
					break;
				case E_1I:
					if (_fichier.caractere_courant() == 'f') {
						_lexeme_en_cours.nature = IF;
						etat = E_FIN;
						_fichier.avancer();
					} else if (_fichier.caractere_courant() == 'n') {
						_lexeme_en_cours.nature = IN;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_2E:
					if (_fichier.caractere_courant() == 'l') {
						etat = E_2L;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_2L:
					if (_fichier.caractere_courant() == 's') {
						etat = E_2S;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_2S:
					if (_fichier.caractere_courant() == 'e') {
						_lexeme_en_cours.nature = ELSE;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_3O:
					if (_fichier.caractere_courant() == 'u') {
						etat = E_3U;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_3U:
					if (_fichier.caractere_courant() == 't') {
						_lexeme_en_cours.nature = OUT;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_4P:
					if (_fichier.caractere_courant() == 'u') {
						etat = E_4U;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_4U:
					if (_fichier.caractere_courant() == 'l') {
						etat = E_4L;
						_fichier.avancer();
					} else if (_fichier.caractere_courant() == 's') {
						etat = E_4S;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_4L:
					if (_fichier.caractere_courant() == 'l') {
						_lexeme_en_cours.nature = PULL;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_4S:
					if (_fichier.caractere_courant() == 'h') {
						_lexeme_en_cours.nature = PUSH;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_5J:
					if (_fichier.caractere_courant() == 'u') {
						etat = E_5U;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_5U:
					if (_fichier.caractere_courant() == 'm') {
						etat = E_5M;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
				case E_5M:
					if (_fichier.caractere_courant() == 'p') {
						_lexeme_en_cours.nature = JUMP;
						etat = E_FIN;
						_fichier.avancer();
					} else {
						_lexeme_en_cours.nature = ERREUR;
						etat = E_FIN;
					}
					break;
			}
		}
	}

	private boolean est_separateur(char c) {
			return c == ' ' || c == '\t' || c == '\n';
	}
}
