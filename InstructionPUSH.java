package Classe;

public class InstructionPUSH extends Instruction {
    private booleann _paramA;
    
    public InstructionPUSH(boolean paramA){
        _paramA = paramA;
    }
    
    public int executer(Donnees &d){
        if (paramA){
            d.pushSave(d.getA());
        } else {
            d.pushSave(d.getB());
        }
        return 1;
    }
}
