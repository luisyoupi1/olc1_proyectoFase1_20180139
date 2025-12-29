/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Potencia implements Expresion {
    private final Expresion a, b;
    public Potencia(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override
    public Object evaluar(Entorno env) {
        double x = AstUtil.toDouble(a.evaluar(env));
        double y = AstUtil.toDouble(b.evaluar(env));
        double r = Math.pow(x, y);

        // si ambos eran int y el resultado es entero, devolvemos int (para comparaciones tipo == 61)
        Object oa = a.evaluar(env);
        Object ob = b.evaluar(env);
        if (!(oa instanceof Double) && !(ob instanceof Double)) {
            if (Math.floor(r) == r) return (int) r;
        }
        return r;
    }
}
