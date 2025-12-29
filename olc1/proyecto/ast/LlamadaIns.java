/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class LlamadaIns implements Instruccion {

    private final String id;
    private final List<Expresion> args;
    private final int linea;
    private final int columna;

    public LlamadaIns(String id, List args, int linea, int columna) {
        this.id = id;
        this.args = args;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        MetodoIns m = env.obtenerMetodo(id);
        List<Object> valores = new ArrayList<>();

        for (Expresion e : args) {
            valores.add(e.evaluar(env));
        }

        m.ejecutarLlamada(env, valores);
    }
}
