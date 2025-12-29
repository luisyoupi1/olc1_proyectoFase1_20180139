/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class MayorIgual implements Expresion {
    private final Expresion a,b;
    public MayorIgual(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override public Object evaluar(Entorno env){
        return AstUtil.toDouble(a.evaluar(env)) >= AstUtil.toDouble(b.evaluar(env));
    }
}
