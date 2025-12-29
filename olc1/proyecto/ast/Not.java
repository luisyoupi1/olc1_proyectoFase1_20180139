/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Not implements Expresion {
    private final Expresion e;
    public Not(Expresion e){ this.e=e; }

    @Override public Object evaluar(Entorno env){
        return !AstUtil.toBool(e.evaluar(env));
    }
}
