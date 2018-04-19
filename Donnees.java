import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Donnees {
  private int _A;
  private int _B;
  private ArrayList<int> _in;
  private ArrayList<int> _out;
  private ArrayList<int> _save;
  private ArrayList<int> _res;
  private boolean _stop;

  public Donnees(String nom_fichier){
    File file = new File(nom_fichier);
    ArrayList<String> liste;
    try {
           // FileReader reads text files in the default encoding.
           FileReader fileReader =
               new FileReader(nom_fichier);

           // Always wrap FileReader in BufferedReader.
           BufferedReader bufferedReader =
               new BufferedReader(fileReader);

           while((line = bufferedReader.readLine()) != null) {
               liste.add(line);
            }

           // Always close files.
           bufferedReader.close();
       }
       catch(FileNotFoundException ex) {
           System.out.println(
               "Unable to open file '" +
               fileName + "'");
       }
       catch(IOException ex) {
           System.out.println(
               "Error reading file '"
               + fileName + "'");
           // Or we could just do this:
           // ex.printStackTrace();
       }
    if (liste.size() < 2) {
      printf("Fichier d'entrée incorrect\n");
    } else {
      Scanner sc = new Scanner(liste[0]);
      while (sc.hasNextInt()) {
        _in.add(sc.nextInt());
      }
      sc = new Scanner(liste[1]);
      while (sc.hasNextInt()) {
        _res.add(sc.nextInt());
      }
    }
    _A = 0;
    _B = 0;
    _stop = false;
  }

  public boolean stopped(){ return _stop; }
  public int getA(){ return _A; }
  public void setA(int A){ _A = A; }
  public int getB(){ return _A; }
  public void setB(int B){ _B = B; }
  public int pullIn(){
    if (_in.isEmpty()){
      _stop = true;
    } else {
      int e = _in.get(0);
      _in.remove(0);
      return e;
    }
  }
  public void pushOut(int e){
    _out.add(0, e);
  }
  public int pullSave(){
    if (_save.isEmpty()){
      _stop = true;
    } else {
      int e = _save.get(0);
      _save.remove(0);
      return e;
    }
  }
  public void pushSave(int e){
    _save.add(0, e);
  }
  public int getIn(int id){ return _in.get(id); }
  public int getOut(int id){ return _out.get(id); }
  public int getSave(int id){ return _save.get(id); }
  public int getRes(int id){ return _res.get(id); }
}
