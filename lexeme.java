public enum Nature_Lexeme{
  A,
  B,
  IF,
  ELSE,
  INF,
  INFE,
  SUP,
  SUPE,
  EGAL,
  DIFF,
  AFFECT,
  PLUS,
  MOINS,
  MUL,
  DIV,
  PARO,
  PARF,
  ACCO,
  ACCF,
  IN,
  OUT,
  PULL,
  PUSH,
  JUMP,
  ENTIER,
  ERREUR;
}

public class Lexeme{
  private Nature_Lexeme nature;
  private int valeur; //pour le jump
}
