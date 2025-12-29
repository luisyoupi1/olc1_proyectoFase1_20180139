/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class ListAppendIns implements Instruccion {
    private final String id;
    private final Expresion exp;
    private final int linea;
    private final int columna;

    public ListAppendIns(String id, Expresion exp, int linea, int columna) {
        this.id = id;
        this.exp = exp;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        var sim = env.obtenerSimbolo(id);
        if (sim == null || !(sim.valor instanceof List)) {
            ReporteErrores.add("SEMANTICO", "No existe la lista '" + id + "'.", linea, columna);
            return;
        }
        Object val = exp.evaluar(env);
        ((List) sim.valor).add(val);
    }
}
