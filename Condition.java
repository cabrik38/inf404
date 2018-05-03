package Classe;

public class Condition {
    private Operation _op1;
    private Operation _op2;
    private Nature_Lexeme _symbole;
    private boolean _vraie;
    
    public Condition(Operation op1, Nature_Lexeme sym, Operation op2){
        _op1 = op1;
        _symbole = sym;
        _op1 = op1;
        _vraie = false;
    }
    
    public void update(Donnees d){
        int a = _op1.evaluer(d);
        int b = _op2.evaluer(d);
        switch (_symbole){
            case INF:
                _vraie = a < b;
                break;
            case INFE:
                _vraie = a <= b;
                break;
            case SUP:
                _vraie = a > b;
                break;
            case SUPE:
                _vraie = a >= b;
                break;
            case EGAL:
                _vraie = a == b;
                break;
            case DIFF:
                _vraie = a != b;
                break;
            default:
                _vraie = false;
        }
    }
    
    public boolean estVraie(){
        return _vraie;
    }
}
