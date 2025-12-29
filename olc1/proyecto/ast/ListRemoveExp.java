/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class ListRemoveExp implements Expresion {
    private final String id;
    private final Expresion indexExp;
    private final int linea;
    private final int columna;

    public ListRemoveExp(String id, Expresion indexExp, int linea, int columna) {
        this.id = id;
        this.indexExp = indexExp;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object evaluar(Entorno env) {
        var sim = env.obtenerSimbolo(id);
        if (sim == null || !(sim.valor instanceof List)) {
            ReporteErrores.add("SEMANTICO", "No existe la lista '" + id + "'.", linea, columna);
            return null;
        }

        Object idxObj = indexExp.evaluar(env);
        if (!(idxObj instanceof Number)) {
            ReporteErrores.add("SEMANTICO", "remove(i): índice debe ser entero.", linea, columna);
            return null;
        }

        int idx = ((Number) idxObj).intValue();
        List l = (List) sim.valor;

        if (idx < 0 || idx >= l.size()) {
            ReporteErrores.add("SEMANTICO", "remove(i): índice fuera de rango: " + idx, linea, columna);
            return null;
        }

        return l.remove(idx);
    }
}

