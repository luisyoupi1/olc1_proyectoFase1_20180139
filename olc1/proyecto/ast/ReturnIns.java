/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.ReturnSignal;

public class ReturnIns implements Instruccion {

    private final Expresion exp;
    private final int linea;
    private final int columna;

    public ReturnIns(Expresion exp, int linea, int columna) {
        this.exp = exp;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        Object valor = null;
        if (exp != null) {
            valor = exp.evaluar(env);
        }
        throw new ReturnSignal(valor);
    }
}
