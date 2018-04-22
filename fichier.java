import java.io.*;
import java.util.*;

public class Fichier{
  private  int carCour;  	// caractere courant
  private  unsigned int ligneCour, colonneCour;  // numeros de ligne et colonne
  private  File file;		// le fichier d'entree
  private ArrayList<String> liste;
  private boolean theEnd;
  public Fichier(){} // le constructeur qui permet d'initialiser une instance de ma classe /!\ a faire a chaque fois !

  public void demarrer(String nom_fichier){
    theEnd=false;
    file= new File(nom_fichier);
    ligneCour=1;
    colonneCour= 0;
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
    avancer();
  }

  public void avancer() {
    if(ligneCour<liste.size()){
      carCour=liste[ligneCour-1]
      if(carCour !='\n'){
        colonneCour = colonneCour +1;
      }else{
        colonneCour =1;
        ligneCour= ligneCour +1 ;
      }
    }else{
      theEnd=true;
      carCour=' ';
    }
  }


  public char caractere_courant() {
    return Character.toLowerCase(CarCour);
  }

  /* --------------------------------------------------------------------- */

  public boolean fin_de_sequence()  {
    return theEnd;
  }

  /* --------------------------------------------------------------------- */

  public unsigned int numero_ligne() {
    return LigneCour;
  }

  /* --------------------------------------------------------------------- */

  public unsigned int numero_colonne() {
    return ColonneCour ;
  }

  /* --------------------------------------------------------------------- */

  public void arreter() {
    file.close() ;
  }

  /* --------------------------------------------------------------------- */

}
