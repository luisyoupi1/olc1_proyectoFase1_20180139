/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class IfIns implements Instruccion {
    private final Expresion cond;
    private final Instruccion thenIns;
    private final Instruccion elseIns;

    public IfIns(Expresion cond, Instruccion thenIns, Instruccion elseIns){
        this.cond = cond;
        this.thenIns = thenIns;
        this.elseIns = elseIns;
    }

    @Override
    public void ejecutar(Entorno env) {
        boolean c = AstUtil.toBool(cond.evaluar(env));
        if (c) thenIns.ejecutar(env);
        else if (elseIns != null) elseIns.ejecutar(env);
    }
}
