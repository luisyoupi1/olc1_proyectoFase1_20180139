package olc1.proyecto.analizadores;

import java_cup.runtime.Symbol;
import java.util.ArrayList;
import java.util.List;

%%

%class Lexer
%unicode
%public
%cup
%line
%column

%{

    public List<String> erroresLexicos = new ArrayList<>();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private void errorLexico(String lexema) {
        erroresLexicos.add(
            "Error léxico: \"" + lexema + "\" en línea "
            + (yyline + 1) + ", columna " + (yycolumn + 1)
        );
    }
%}

/* ===== DEFINICIONES ===== */

DIGIT      = [0-9]
INT        = {DIGIT}+
DOUBLE     = {DIGIT}+"."{DIGIT}+
ID         = [a-zA-Z_][a-zA-Z0-9_]*
WHITESPACE = [ \t\r\n]+

STRING = \"([^\"\\]|\\[ntr\"'\\])*\" 
CHAR   = \'([^\'\\]|\\[ntr\"'\\])\'

%%

<YYINITIAL>{

/* ===== COMENTARIOS ===== */
"//".*                                  { /* ignorar */ }
"/*"([^*]|\*+[^/])*\*+"/"               { /* ignorar */ }

/* ===== ESPACIOS ===== */
{WHITESPACE}                            { /* ignorar */ }

/* ===== LITERALES ===== */
{DOUBLE}                                { return symbol(sym.DOUBLE_LIT, Double.parseDouble(yytext())); }
{INT}                                   { return symbol(sym.INT_LIT, Integer.parseInt(yytext())); }
{STRING}                                { return symbol(sym.STRING_LIT, yytext()); }
{CHAR}                                  { return symbol(sym.CHAR_LIT, yytext()); }

/* ===== OPERADORES ===== */

/* potencia */
"**"                                    { return symbol(sym.POT); }

/* relacionales */
"=="                                    { return symbol(sym.IGUAL); }
"!="                                    { return symbol(sym.DIF); }
"<="                                    { return symbol(sym.MENIG); }
">="                                    { return symbol(sym.MAYIG); }
"<"                                     { return symbol(sym.MEN); }
">"                                     { return symbol(sym.MAY); }

/* lógicos */
"&&"                                    { return symbol(sym.AND); }
"||"                                    { return symbol(sym.OR); }
"^"                                     { return symbol(sym.XOR); }
"!"                                     { return symbol(sym.NOT); }

/* aritméticos */
"+"                                     { return symbol(sym.MAS); }
"-"                                     { return symbol(sym.MENOS); }
"*"                                     { return symbol(sym.POR); }
"/"                                     { return symbol(sym.DIV); }
"%"                                     { return symbol(sym.MOD); }

/* ===== SIGNOS ===== */
";"                                     { return symbol(sym.PTCOMA); }
":"                                     { return symbol(sym.DOSP); }
","                                     { return symbol(sym.COMA); }
"("                                     { return symbol(sym.PAR_ABRE); }
")"                                     { return symbol(sym.PAR_CIERRA); }
"{"                                     { return symbol(sym.LLAVE_ABRE); }
"}"                                     { return symbol(sym.LLAVE_CIERRA); }
"="                                     { return symbol(sym.ASIGN); }

/* ===== IDENTIFICADORES / PALABRAS RESERVADAS ===== */
{ID} {
    String lex = yytext().toLowerCase();

    switch (lex) {
        case "var":      return symbol(sym.VAR);
        case "int":      return symbol(sym.INT);
        case "double":   return symbol(sym.DOUBLE);
        case "bool":     return symbol(sym.BOOL);
        case "char":     return symbol(sym.CHAR);
        case "string":   return symbol(sym.STRING);

        case "true":     return symbol(sym.TRUE);
        case "false":    return symbol(sym.FALSE);

        case "if":       return symbol(sym.IF);
        case "else":     return symbol(sym.ELSE);

        case "while":    return symbol(sym.WHILE);
        case "do":       return symbol(sym.DO);
        case "for":      return symbol(sym.FOR);

        case "break":    return symbol(sym.BREAK);
        case "continue": return symbol(sym.CONTINUE);

        case "switch":   return symbol(sym.SWITCH);
        case "case":     return symbol(sym.CASE);
        case "default":  return symbol(sym.DEFAULT);

        case "print":    return symbol(sym.PRINT);

        default:
            return symbol(sym.ID, yytext());
    }
}

/* ===== ERROR ===== */
. {
    errorLexico(yytext());
}

}
