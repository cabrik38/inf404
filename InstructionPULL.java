package Classe;

public class InstructionPULL extends Instruction {
    private booleann _paramA;
    
    public InstructionPULL(boolean paramA){
        _paramA = paramA;
    }
    
    public int executer(Donnees &d){
        if (paramA){
            d.setA(d.pullSave());
        } else {
            d.setB(d.pullSave());
        }
        return 1;
    }
}
