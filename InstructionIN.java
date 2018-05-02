package Classe;

public class InstructionIN extends Instruction {
    private booleann _paramA;
    
    public InstructionIN(boolean paramA){
        _paramA = paramA;
    }
    
    public int executer(Donnees &d){
        if (paramA){
            d.setA(d.pullIn());
        } else {
            d.setB(d.pullIn());
        }
        return 1;
    }
}
