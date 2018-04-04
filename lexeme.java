public enum Nature_Lexeme{
  A,
  B,
  IF,
  ELSE,
  INF,
  SUP,
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
  ENTIER;
}

public class Lexeme{
  Nature_Lexeme nature;
  int valeur; //pour le jump
}
