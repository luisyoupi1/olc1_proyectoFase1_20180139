package olc1.proyecto.analizadores;

import java_cup.runtime.Symbol;
import java.util.ArrayList;
import java.util.List;

%%

%public
%class Lexer
%cup
%unicode
%line
%column
%ignorecase

%{
  public List<String> erroresLexicos = new ArrayList<>();

  private Symbol sym(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }

  private Symbol sym(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }

  private void lexError(String msg) {
    erroresLexicos.add("Línea " + (yyline + 1) + ", Col " + (yycolumn + 1) + ": " + msg + " -> '" + yytext() + "'");
  }
%}

NL      = \r\n|\r|\n
WS      = [ \t\f]+
ID      = [a-zA-Z_][a-zA-Z0-9_]*

DIG     = [0-9]
INT     = {DIG}+
DOUBLE  = {DIG}+ "." {DIG}+

STR     = \"([^\"\\]|\\.)*\"
CHAR    = \'([^\'\\]|\\.)\'

LINEC   = "//".*
BLOCKC  = "/*"([^*]|\*+[^*/])*\*+"/"

%%

{WS}            { /* ignore */ }
{NL}            { /* ignore */ }
{LINEC}         { /* ignore */ }
{BLOCKC}        { /* ignore */ }

/* ===== palabras reservadas ===== */
"var"           { return sym(sym.VAR); }

"int"           { return sym(sym.INT); }
"double"        { return sym(sym.DOUBLE); }
"bool"          { return sym(sym.BOOL); }
"char"          { return sym(sym.CHAR); }
"string"        { return sym(sym.STRING); }
"void"          { return sym(sym.VOID); }

"true"          { return sym(sym.TRUE); }
"false"         { return sym(sym.FALSE); }

"if"            { return sym(sym.IF); }
"else"          { return sym(sym.ELSE); }

"while"         { return sym(sym.WHILE); }
"do"            { return sym(sym.DO); }

"for"           { return sym(sym.FOR); }

"break"         { return sym(sym.BREAK); }
"continue"      { return sym(sym.CONTINUE); }
"return"        { return sym(sym.RETURN); }

"print"         { return sym(sym.PRINT); }

/* START main(); */
"START"         { return sym(sym.START); }

/* ===== switch ===== */
"switch"        { return sym(sym.SWITCH); }
"case"          { return sym(sym.CASE); }
"default"       { return sym(sym.DEFAULT); }

/* ===== listas ===== */
"new"           { return sym(sym.NEW); }
"List"          { return sym(sym.LIST); }
"append"        { return sym(sym.APPEND); }
"remove"        { return sym(sym.REMOVE); }
"find"          { return sym(sym.FIND); }

/* ===== operadores multi-char ===== */
"**"            { return sym(sym.POT); }

"=="            { return sym(sym.IGUAL); }
"!="            { return sym(sym.DIF); }
"<="            { return sym(sym.MENIG); }
">="            { return sym(sym.MAYIG); }

"&&"            { return sym(sym.AND); }
"||"            { return sym(sym.OR); }

"++"            { return sym(sym.INC); }
"--"            { return sym(sym.DEC); }

/* ===== operadores 1-char ===== */
"+"             { return sym(sym.MAS); }
"-"             { return sym(sym.MENOS); }
"*"             { return sym(sym.POR); }
"/"             { return sym(sym.DIV); }
"%"             { return sym(sym.MOD); }

"<"             { return sym(sym.MEN); }
">"             { return sym(sym.MAY); }

"!"             { return sym(sym.NOT); }
"="             { return sym(sym.ASIGN); }

/* ===== signos ===== */
";"             { return sym(sym.PTCOMA); }
":"             { return sym(sym.DOSP); }
","             { return sym(sym.COMA); }

"("             { return sym(sym.PAR_ABRE); }
")"             { return sym(sym.PAR_CIERRA); }

"{"             { return sym(sym.LLAVE_ABRE); }
"}"             { return sym(sym.LLAVE_CIERRA); }

"["             { return sym(sym.COR_ABRE); }
"]"             { return sym(sym.COR_CIERRA); }

"."             { return sym(sym.PUNTO); }
"^"             { return sym(sym.XOR); }


/* ===== literales ===== */
{DOUBLE}        { return sym(sym.DOUBLE_LIT, Double.valueOf(yytext())); }
{INT}           { return sym(sym.INT_LIT, Integer.valueOf(yytext())); }

{STR}           {
                  String raw = yytext();
                  String val = raw.substring(1, raw.length()-1);
                  return sym(sym.STRING_LIT, val);
                }

{CHAR}          {
                  String raw = yytext();
                  String val = raw.substring(1, raw.length()-1);
                  return sym(sym.CHAR_LIT, val);
                }

/* ===== IDs ===== */
{ID}            { return sym(sym.ID, yytext()); }

/* ===== error ===== */
.               { lexError("Símbolo no válido"); }

<<EOF>>         { return sym(sym.EOF); }
