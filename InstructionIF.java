package Classe;
import java.util.ArrayList;

public class InstructionIF extends Instruction {
    private SequenceInstructions _sequenceIF;
    private SequenceInstructions _sequenceELSE;
    private Condition _condition;
    private int _pointeur;
    
    public InstructionIF(Condition cond){
        _sequenceIF = new SequenceInstructions();
        _sequenceELSE = new SequenceInstructions();
        _condition = cond;
        _pointeur = 0;
    }
    
    public int executer(Donnees &d){
        if (_pointeur == 0){
            _condition.update();
            _sequenceIF.setPointeur(1);
            _sequenceELSE.setPointeur(1);
            _pointeur = 1;
        } else {
            if (_condition.estVraie()){
                int s = _sequenceIF.step();
            } else {
                int s = _sequenceELSE.step();
            }
            if (s == -1){
                _pointeur = 0;
            } else if (s < -1){
                _pointeur = 0;
                return s + 1;
            } else if (s > 0){
                _pointeur = 0;
                return s;
            }
        }
        return 0;
    }
}
