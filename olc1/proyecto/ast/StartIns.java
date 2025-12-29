/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class StartIns implements Instruccion {

    private final String id;
    private final int linea;
    private final int columna;

    public StartIns(String id, int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        // START solo llama a un método (void) sin params: main()
        var m = env.obtenerMetodo(id);
        if (m == null) {
            ReporteErrores.add("SEMANTICO", "No existe método START '" + id + "'.", linea, columna);
            return;
        }
        m.ejecutarLlamada(env, new ArrayList<>());
    }
}
