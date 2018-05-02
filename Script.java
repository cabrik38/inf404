package Classe;

public class Script {
      private SequenceInstructions _seq;
      
      public Script(){}
      
      public void step(Donnees &d){
            if (!d.stopped()){
                  _seq.setPointeur(_seq.step(d));
            }
      }
}
