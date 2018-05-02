package Classe;

public class InstructionJUMP extends Instruction {
    private int _param;
    
    public InstructionJUMP(int param){
        _param = param;
    }
    
    public int executer(Donnees &d){
        return _param;
    }
}
