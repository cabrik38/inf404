package Classe;

public class InstructionAFFECT extends Instruction {
    private booleann _paramA;
    private Operation _op;
    
    public InstructionAFFECT(boolean paramA, Operation op){
        _paramA = paramA;
        _op = op;
    }
    
    public int executer(Donnees &d){
        if (_paramA){
            d.setA(_op.evaluer(d));
        } else {
            d.setB(_op.evaluer(d));
        }
        return 1;
    }
}
