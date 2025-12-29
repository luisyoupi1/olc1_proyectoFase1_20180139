/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Xor implements Expresion {
    private final Expresion a,b;
    public Xor(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override public Object evaluar(Entorno env){
        boolean x = AstUtil.toBool(a.evaluar(env));
        boolean y = AstUtil.toBool(b.evaluar(env));
        return x ^ y;
    }
}
