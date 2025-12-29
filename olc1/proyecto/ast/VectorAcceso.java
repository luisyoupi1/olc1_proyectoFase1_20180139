/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class VectorAcceso implements Expresion {

    private final String id;
    private final List<Expresion> indices;
    private final int linea;
    private final int columna;

    public VectorAcceso(String id, List indices, int linea, int columna) {
        this.id = id;
        this.indices = indices;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object evaluar(Entorno env) {

        var sim = env.obtenerSimbolo(id);
        if (sim == null) {
            ReporteErrores.add("SEMANTICO",
                    "No existe el vector/matriz '" + id + "'.",
                    linea, columna);
            return null;
        }

        Object actual = sim.valor;

        for (int i = 0; i < indices.size(); i++) {

            if (!(actual instanceof List<?> lista)) {
                ReporteErrores.add("SEMANTICO",
                        "Acceso inválido a '" + id + "', no es vector/matriz.",
                        linea, columna);
                return null;
            }

            Object idxObj = indices.get(i).evaluar(env);
            if (!(idxObj instanceof Number)) {
                ReporteErrores.add("SEMANTICO",
                        "Índice debe ser entero en '" + id + "'.",
                        linea, columna);
                return null;
            }

            int idx = ((Number) idxObj).intValue();

            if (idx < 0 || idx >= lista.size()) {
                ReporteErrores.add("SEMANTICO",
                        "Índice fuera de rango en '" + id + "': " + idx,
                        linea, columna);
                return null;
            }

            // bajar un nivel
            actual = lista.get(idx);
        }

        // 🔥 CLAVE: puede devolver LIST (subvector) o VALOR
        return actual;
    }
}
