/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Modulo implements Expresion {
    private final Expresion a, b;
    public Modulo(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override
    public Object evaluar(Entorno env) {
        Object x = a.evaluar(env);
        Object y = b.evaluar(env);
        double den = AstUtil.toDouble(y);
        if (den == 0.0) return null;
        // para fase 1, modulo tipo double sirve
        return AstUtil.toDouble(x) % den;
    }
}
