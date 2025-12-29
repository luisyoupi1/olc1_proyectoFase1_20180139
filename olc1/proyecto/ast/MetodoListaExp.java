/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class MetodoListaExp implements Expresion {

    private final String objId;
    private final String metodo;
    private final List<Expresion> args;
    private final int linea;
    private final int columna;

    public MetodoListaExp(String objId, String metodo, List args, int linea, int columna) {
        this.objId = objId;
        this.metodo = metodo;
        this.args = (List<Expresion>) args;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object evaluar(Entorno env) {
        var sim = env.obtenerSimbolo(objId);
        if (sim == null) {
            ReporteErrores.add("SEMANTICO", "No existe '" + objId + "'.", linea, columna);
            return null;
        }

        Object base = sim.valor;

        // Permitir find en arreglos y listas (si tu vector usa java.util.List también funciona)
        if (!(base instanceof List)) {
            ReporteErrores.add("SEMANTICO", "'" + objId + "' no es lista/vector.", linea, columna);
            return null;
        }

        List lista = (List) base;

        // evaluar args
        List<Object> vals = new ArrayList<>();
        if (args != null) {
            for (Expresion e : args) vals.add(e.evaluar(env));
        }

        switch (metodo.toLowerCase()) {
            case "append": {
                if (vals.size() != 1) {
                    ReporteErrores.add("SEMANTICO", "append(x) requiere 1 argumento.", linea, columna);
                    return null;
                }
                lista.add(vals.get(0));
                return null;
            }

            case "remove": {
                if (vals.size() != 1 || !(vals.get(0) instanceof Number)) {
                    ReporteErrores.add("SEMANTICO", "remove(i) requiere índice entero.", linea, columna);
                    return null;
                }
                int i = ((Number) vals.get(0)).intValue();
                if (i < 0 || i >= lista.size()) {
                    ReporteErrores.add("SEMANTICO", "Índice fuera de rango en remove: " + i, linea, columna);
                    return null;
                }
                return lista.remove(i);
            }

            case "find": {
                if (vals.size() != 1) {
                    ReporteErrores.add("SEMANTICO", "find(x) requiere 1 argumento.", linea, columna);
                    return false;
                }
                Object x = vals.get(0);
                return lista.contains(x);
            }

            default:
                ReporteErrores.add("SEMANTICO", "Método no soportado: " + metodo, linea, columna);
                return null;
        }
    }
}
