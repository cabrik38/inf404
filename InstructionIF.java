package Classe;
import java.util.ArrayList;

public class InstructionIF extends Instruction {
    private SequenceInstructions _sequenceIF;
    private SequenceInstructions _sequenceELSE;
    private Condition _condition;
    private int _pointeur;
    
    public InstructionIF(Condition cond){
        _sequenceIF = new ArrayList<Instruction>();
        _sequenceELSE = new ArrayList<Instruction>();
        _condition = cond;
        _pointeur = 0;
    }
    
    public int executer(Donnees &d){
        
    }
}
