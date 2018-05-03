package Classe;

public class Script {
    private SequenceInstructions _seq;

    public Script(SequenceInstructions seq){
        _seq = seq;
    }

    public void step(Donnees &d){
        if (!d.stopped()){
            _seq.setPointeur(_seq.step(d));
        }
    }
}
