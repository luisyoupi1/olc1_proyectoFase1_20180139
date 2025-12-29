/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Resta implements Expresion {
    private final Expresion a, b;
    public Resta(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override
    public Object evaluar(Entorno env) {
        Object x = a.evaluar(env);
        Object y = b.evaluar(env);
        if (x instanceof Double || y instanceof Double) return AstUtil.toDouble(x) - AstUtil.toDouble(y);
        return AstUtil.toInt(x) - AstUtil.toInt(y);
    }
}
