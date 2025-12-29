/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class MetodoListaIns implements Instruccion {

    private final MetodoListaExp exp;

    public MetodoListaIns(String objId, String metodo, List args, int linea, int columna) {
        this.exp = new MetodoListaExp(objId, metodo, args, linea, columna);
    }

    @Override
    public void ejecutar(Entorno env) {
        exp.evaluar(env); // ejecuta y descarta retorno
    }
}
