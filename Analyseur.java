package Classe;

public class Analyseur {
    private Fichier _fichier;
    private Lexeme _lexeme_en_cours;

    public Analyseur(String nom_fichier) {
        demarrer(nom_fichier);
    }

    public void demarrer(String nom_fichier) {
        _fichier = new Fichier();
        _fichier.demarrer(nom_fichier);
        _lexeme_en_cours = new Lexeme();
        avancer();
        rec_script();
    }
    
    /** ANALYSE LEXICALE **/

    public void avancer() {
        reconnaitre_lexeme();
    }

    public Lexeme lexeme_courant() {
        return _lexeme_en_cours;
    }

    public boolean fin_de_sequence() {
        return _lexeme_en_cours.nature == Nature_Lexeme.FIN_SEQUENCE;
    }

    public void arreter() {
        _fichier.arreter();
    }

    private void reconnaitre_lexeme() {
        EtatAutomate etat = EtatAutomate.E_INIT;

        while (est_separateur(_fichier.caractere_courant())) {
            _fichier.avancer();
        }

        while (etat != EtatAutomate.E_FIN) {
            switch (etat) {
                case E_INIT:
                    if (_fichier.fin_de_sequence()) {
                        _lexeme_en_cours.nature = Nature_Lexeme.FIN_SEQUENCE;
                        etat = EtatAutomate.E_FIN;
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
                                _lexeme_en_cours.nature = Nature_Lexeme.ENTIER;
                                _lexeme_en_cours.valeur = _fichier.caractere_courant() - '0';
                                etat = EtatAutomate.E_ENTIER;
                                break;
                            case 'a':
                                _lexeme_en_cours.nature = Nature_Lexeme.A;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case 'b':
                                _lexeme_en_cours.nature = Nature_Lexeme.B;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '+':
                                _lexeme_en_cours.nature = Nature_Lexeme.PLUS;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '-':
                                _lexeme_en_cours.nature = Nature_Lexeme.MOINS;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '*':
                                _lexeme_en_cours.nature = Nature_Lexeme.MUL;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '/':
                                _lexeme_en_cours.nature = Nature_Lexeme.DIV;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '(':
                                _lexeme_en_cours.nature = Nature_Lexeme.PARO;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case ')':
                                _lexeme_en_cours.nature = Nature_Lexeme.PARF;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '{':
                                _lexeme_en_cours.nature = Nature_Lexeme.ACCO;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '}':
                                _lexeme_en_cours.nature = Nature_Lexeme.ACCF;
                                etat = EtatAutomate.E_FIN;
                                break;
                            case '=':
                                etat = EtatAutomate.E_EGAL;
                                break;
                            case '!':
                                etat = EtatAutomate.E_EXCL;
                                break;
                            case '<':
                                etat = EtatAutomate.E_INF;
                                break;
                            case '>':
                                etat = EtatAutomate.E_SUP;
                                break;
                            case 'i':
                                etat = EtatAutomate.E_1I;
                                break;
                            case 'e':
                                etat = EtatAutomate.E_2E;
                                break;
                            case 'o':
                                etat = EtatAutomate.E_3O;
                                break;
                            case 'p':
                                etat = EtatAutomate.E_4P;
                                break;
                            case 'j':
                                etat = EtatAutomate.E_5J;
                                break;
                            default:
                                _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                                etat = EtatAutomate.E_FIN;
                        }
                        _fichier.avancer();
                    }
                    break;
                case E_ENTIER:
                    if (_fichier.caractere_courant() < '0' || _fichier.caractere_courant() > '9') {
                        etat = EtatAutomate.E_FIN;
                    } else {
                        _lexeme_en_cours.valeur = _lexeme_en_cours.valeur * 10 + _fichier.caractere_courant() - '0';
                        _fichier.avancer();
                    }
                    break;
                case E_EGAL:
                    if (_fichier.caractere_courant() == '=') {
                        _lexeme_en_cours.nature = Nature_Lexeme.EGAL;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.AFFECT;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_EXCL:
                    if (_fichier.caractere_courant() == '=') {
                        _lexeme_en_cours.nature = Nature_Lexeme.DIFF;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_INF:
                    if (_fichier.caractere_courant() == '=') {
                        _lexeme_en_cours.nature = Nature_Lexeme.INFE;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.INF;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_SUP:
                    if (_fichier.caractere_courant() == '=') {
                        _lexeme_en_cours.nature = Nature_Lexeme.SUPE;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.SUP;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_1I:
                    if (_fichier.caractere_courant() == 'f') {
                        _lexeme_en_cours.nature = Nature_Lexeme.IF;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else if (_fichier.caractere_courant() == 'n') {
                        _lexeme_en_cours.nature = Nature_Lexeme.IN;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_2E:
                    if (_fichier.caractere_courant() == 'l') {
                        etat = EtatAutomate.E_2L;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_2L:
                    if (_fichier.caractere_courant() == 's') {
                        etat = EtatAutomate.E_2S;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_2S:
                    if (_fichier.caractere_courant() == 'e') {
                        _lexeme_en_cours.nature = Nature_Lexeme.ELSE;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_3O:
                    if (_fichier.caractere_courant() == 'u') {
                        etat = EtatAutomate.E_3U;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_3U:
                    if (_fichier.caractere_courant() == 't') {
                        _lexeme_en_cours.nature = Nature_Lexeme.OUT;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_4P:
                    if (_fichier.caractere_courant() == 'u') {
                        etat = EtatAutomate.E_4U;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_4U:
                    if (_fichier.caractere_courant() == 'l') {
                        etat = EtatAutomate.E_4L;
                        _fichier.avancer();
                    } else if (_fichier.caractere_courant() == 's') {
                        etat = EtatAutomate.E_4S;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_4L:
                    if (_fichier.caractere_courant() == 'l') {
                        _lexeme_en_cours.nature = Nature_Lexeme.PULL;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_4S:
                    if (_fichier.caractere_courant() == 'h') {
                        _lexeme_en_cours.nature = Nature_Lexeme.PUSH;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_5J:
                    if (_fichier.caractere_courant() == 'u') {
                        etat = EtatAutomate.E_5U;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_5U:
                    if (_fichier.caractere_courant() == 'm') {
                        etat = EtatAutomate.E_5M;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
                case E_5M:
                    if (_fichier.caractere_courant() == 'p') {
                        _lexeme_en_cours.nature = Nature_Lexeme.JUMP;
                        etat = EtatAutomate.E_FIN;
                        _fichier.avancer();
                    } else {
                        _lexeme_en_cours.nature = Nature_Lexeme.ERREUR;
                        etat = EtatAutomate.E_FIN;
                    }
                    break;
            }
        }
    }

    private boolean est_separateur(char c) {
        return c == ' ' || c == '\t' || c == '\n';
    }
    
    /** ANALYSE SYNTAXIQUE **/
    
    private void rec_script(SequenceInstructions &seq){
		if (rec_instruction(seq)){
			rec_script(seq);
		}
	}
	
	private boolean rec_instruction(SequenceInstructions &seq){
		if (lexeme_courant().nature == IF){
			avancer();
			if (lexeme_courant().nature != PARO){
				erreur_syntaxique();
			}
			avancer();
			Condition cond;
			rec_condition(cond);
			if (lexeme_courant().nature != PARF){
				erreur_syntaxique();
			}
			avancer();
			if (lexeme_courant().nature != ACCO){
				erreur_syntaxique();
			}
			avancer();
			SequenceInstructions seqIF = new SequenceInstructions();
			rec_script(seqIF);
			if (lexeme_courant().nature != ACCF){
				erreur_syntaxique();
			}
			avancer();
			SequenceInstructions seqELSE = new SequenceInstructions();
			rec_suite_if(seqELSE);
			InstructionIF i = new InstructionIF(cond, seqIF, seqELSE);
			seq.addInstruction(i);
		} else if (!rec_affectation(seq)){
			rec_fonction(seq);
		}
	}
	
	private void rec_suite_if(SequenceInstructions &seqELSE){
		if (lexeme_courant().nature == ELSE){
			avancer();
			if (lexeme_courant().nature != ACCO){
				erreur_syntaxique();
			}
			avancer();
			rec_script(seqELSE);
			if (lexeme_courant().nature != ACCF){
				erreur_syntaxique();
			}
			avancer();
		}
	}
	
	private void rec_condition(Condition &cond){
		Operation op1;
		rec_operation(op1);
		Nature_Lexeme sym;
		rec_symbole(sym);
		Operation op2;
		rec_operation(op2);
		cond = new Condition(op1, sym, op2);
	}
	
	private boolean rec_affectation(SequenceInstructions &seq){
		boolean paramA = true;
		if (!rec_variable(paramA)){
			return false;
		}
		if (lexeme_courant().nature != AFFECT){
			return false;
		}
		avancer();
		Operation op;
		rec_operation(op);
		InstructionAFFECT i = new InstructionAFFECT(paramA, op);
		seq.addInstruction(i);
		return true;
	}
	
	private void rec_fonction(SequenceInstructions &seq){
		switch (lexeme_courant()){
			case IN:
				if (lexeme_courant().nature != PARO){
					erreur_syntaxique();
				}
				avancer();
				rec_variable();
				if (lexeme_courant().nature != PARF){
					erreur_syntaxique();
				}
				avancer();
				break;
			case OUT:
				if (lexeme_courant().nature != PARO){
					erreur_syntaxique();
				}
				avancer();
				rec_variable();
				if (lexeme_courant().nature != PARF){
					erreur_syntaxique();
				}
				avancer();
				break;
			case PULL:
				if (lexeme_courant().nature != PARO){
					erreur_syntaxique();
				}
				avancer();
				rec_variable();
				if (lexeme_courant().nature != PARF){
					erreur_syntaxique();
				}
				avancer();
				break;
			case PUSH:
				if (lexeme_courant().nature != PARO){
					erreur_syntaxique();
				}
				avancer();
				rec_variable();
				if (lexeme_courant().nature != PARF){
					erreur_syntaxique();
				}
				avancer();
				break;
			case JUMP:
				if (lexeme_courant().nature != PARO){
					erreur_syntaxique();
				}
				avancer();
				rec_nombre();
				if (lexeme_courant().nature != PARF){
					erreur_syntaxique();
				}
				avancer();
				break;
			default:
				erreur_syntaxique();
		}
	}
	
	private void rec_operation(Operation &op){
		Nature_Lexeme fact1, sym, fact2;
		int val1, val2;
		rec_facteur(fact1, val1);
		rec_suite_operation(sym, fact2, val2);
		op = new Operation(fact1, val1, sym, fact2, val2);
	}
	
	private void rec_suite_operation(Nature_Lexeme &sym, Nature_Lexeme &fact, int &val){
		if (rec_operateur(sym)){
			rec_facteur(fact, val);
		} else {
			sym = Nature_Lexeme.PLUS;
			fact = Nature_Lexeme.ENTIER;
			val = 0;
		}
	}
	
	private void rec_facteur(Nature_Lexeme &fact, int &val){
		boolean paramA = true;
		if (rec_variable(paramA)){
			if (paramA){
				fact = Nature_Lexeme.A;
			} else {
				fact = Nature_Lexeme.B;
			}
		} else {
			fact = Nature_Lexeme.ENTIER;
			rec_nombre(val);
		}
	}
	
	private void rec_symbole(Nature_Lexeme &sym){
		switch (lexeme_courant().nature){
			case EGAL:
			case DIFF:
			case INF:
			case INFE:
			case SUP:
			case SUPE:
				sym = lexeme_courant().nature;
				break;
			default:
				erreur_syntaxique();
		}
		avancer();
	}
	
	private boolean rec_variable(boolean &paramA){
		if (lexeme_courant().nature == A){
			avancer();
			paramA = true;
			return true;
		} else if (lexeme_courant().nature == B){
			avancer();
			paramA = false;
			return true;
		} else {
			return false;
		}
	}
	
	private void rec_nombre(int &val){
		if (lexeme_courant().nature == MOINS){
			avancer();
			if (lexeme_courant().nature == ENTIER){
				val = -lexeme_courant().valeur;
				avancer();
			} else {
				erreur_syntaxique();
			}
		} else if (lexeme_courant().nature == ENTIER){
			val = lexeme_courant().valeur;
			avancer();
		} else {
			erreur_syntaxique();
		}
	}
	
	private boolean rec_operateur(Nature_Lexeme &op){
		switch (lexeme_courant().nature){
			case PLUS:
			case MOINS:
			case MUL:
			case DIV:
				op = lexeme_courant().nature;
				break;
			default:
				return false;
		}
		avancer();
		return true;
	}
	
	private void erreur_syntaxique(){
		System.out.println("Erreur de syntaxe\n");
	}
}

