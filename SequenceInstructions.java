package Classe;
import java.util.ArrayList;

public class SequenceInstructions {
    private ArrayList<Instruction> _sequence;
    private int _pointeur;
    
    public SequenceInstructions(){
        _sequence = new ArrayList<Instruction>();
        _pointeur = 0;
    }
    
    public int step(Donnees &d){
        int i = _sequence.get(_pointeur).executer(d);
        _pointeur = _pointeur + i;
        if (_pointeur >= _sequence.size()){
            return _pointeur - _sequence.size() + 1;
        }
        return 0;
    }
}
