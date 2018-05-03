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
        } else if (_pointeur < 0){
            return _pointeur;
        }
        return 0;
    }
    
    public void addInstruction(Instruction I){
        _sequence.add(I);
    }
    
    public void setPointeur(int pointeur){ 
        if (pointeur > 0){
            _pointeur = (pointeur - 1) % _sequence.size(); 
        } else if (pointeur < 0){
            _pointeur = (_sequence.size() + pointeur) % _sequence.size();
        }
    }
}
