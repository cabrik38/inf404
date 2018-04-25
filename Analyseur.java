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
    }

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
}
