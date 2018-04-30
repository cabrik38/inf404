package Classe;
import java.util.ArrayList;

public class InstructionIF extends Instruction {
    private SequenceInstructions _sequenceIF;
    private SequenceInstructions _sequenceELSE;
    private Condition _condition;
    private int _pointeur;
    
    public InstructionIF(Condition _c){
        _sequenceIF = new ArrayList<Instruction>();
        _sequenceELSE = new ArrayList<Instruction>();
        _condition = _c;
        _pointeur = 0;
    }
    
    public int executer(Donnees &d){
        
    }
}
