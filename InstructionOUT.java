package Classe;

public class InstructionOUT extends Instruction {
    private booleann _paramA;
    
    public InstructionOUT(boolean paramA){
        _paramA = paramA;
    }
    
    public int executer(Donnees &d){
        if (_paramA){
            d.pushOut(d.getA());
        } else {
            d.pushOut(d.getB());
        }
        return 1;
    }
}
