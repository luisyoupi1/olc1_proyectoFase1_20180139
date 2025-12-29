/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.ui.Consola;

public class PrintIns implements Instruccion {
    private final Expresion exp;
    public PrintIns(Expresion exp){ this.exp = exp; }

    @Override
    public void ejecutar(Entorno env) {
        Object v = exp.evaluar(env);
        Consola.escribirLinea(String.valueOf(v));
    }
    
    
public String toAST(String parent) {
    String id = "n" + System.identityHashCode(this);
    StringBuilder sb = new StringBuilder();

    sb.append(id).append("[label=\"Print\"];\n");
    sb.append(parent).append(" -> ").append(id).append(";\n");

    if (exp instanceof ASTNode ast) {
        sb.append(ast.toAST(id));
    }

    return sb.toString();
}

}
