/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Negativo implements Expresion {
    private final Expresion e;
    public Negativo(Expresion e){ this.e=e; }

    @Override
    public Object evaluar(Entorno env) {
        Object v = e.evaluar(env);
        if (v instanceof Double) return -AstUtil.toDouble(v);
        if (v instanceof Integer || v instanceof Character) return -AstUtil.toInt(v);
        return -AstUtil.toDouble(v);
    }
}
