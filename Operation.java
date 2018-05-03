package Classe;

public class Operation {
    private Nature_Lexeme _fact1;
    private int _val1;
    private Nature_Lexeme _fact2;
    private int _val2;
    private Nature_Lexeme _operateur;
    
    public Operation(Nature_Lexeme fact1, int val1, Nature_Lexeme op, Nature_Lexeme fact2, int val2){
        _fact1 = fact1;
        _val1 = val1;
        _operateur = op;
        _fact2 = fact2;
        _val2 = val2;
    }
    
    public void evaluer(Donnees d){
        int a, b, r;
        switch (_fact1){
            case A:
                a = d.getA();
                break;
            case B:
                a = d.getB();
                break;
            case ENTIER:
                a = _val1;
                break;
            default:
                a = 0;
        }
        switch (_fact2){
            case A:
                b = d.getA();
                break;
            case B:
                b = d.getB();
                break;
            case ENTIER:
                b = _val2;
                break;
            default:
                b = 0;
        }
        switch (_operateur){
            case PLUS:
                r = a + b;
                break;
            case MOINS:
                r = a - b;
                break;
            case MUL:
                r = a * b;
                break;
            case DIV:
                r = a / b;
                break;
            default:
                r = 0;
        }
        return r;
    }
}
