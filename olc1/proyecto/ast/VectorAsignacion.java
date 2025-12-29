/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;
import olc1.proyecto.reportes.ReporteErrores;

public class VectorAsignacion implements Instruccion {
    private final String id;
    private final List<Expresion> indices;
    private final Expresion valor;
    private final int linea;
    private final int columna;

    public VectorAsignacion(String id, List indices, Expresion valor, int linea, int columna) {
        this.id = id;
        this.indices = (List<Expresion>) indices;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        Simbolo sim = env.obtenerSimbolo(id);
        if (sim == null) {
            ReporteErrores.add("SEMANTICO", "No existe el vector '" + id + "'.", linea, columna);
            return;
        }

        if (sim.valor == null) {
            ReporteErrores.add("SEMANTICO", "El vector '" + id + "' no está inicializado.", linea, columna);
            return;
        }

        Object actual = sim.valor;

        for (int i = 0; i < indices.size(); i++) {

            if (!(actual instanceof List)) {
                ReporteErrores.add("SEMANTICO",
                        "Asignación inválida: '" + id + "' no es vector en el nivel " + (i + 1) + ".",
                        linea, columna);
                return;
            }

            Object idxObj = indices.get(i).evaluar(env);
            if (!(idxObj instanceof Number)) {
                ReporteErrores.add("SEMANTICO", "Índice debe ser numérico (entero) en '" + id + "'.", linea, columna);
                return;
            }

            int idx = ((Number) idxObj).intValue();
            List lista = (List) actual;

            if (idx < 0 || idx >= lista.size()) {
                ReporteErrores.add("SEMANTICO",
                        "Índice fuera de rango en '" + id + "': " + idx,
                        linea, columna);
                return;
            }

            if (i == indices.size() - 1) {
                Object val = valor.evaluar(env);

                // Si querés validación de tipo base, hacelo aquí (depende cómo guardes el tipo del vector)
                // Ejemplo mínimo:
                // if (!VectorUtils.valorCompatible(sim.tipo, val)) { ... }

                lista.set(idx, val);
                return;
            }

            Object siguiente = lista.get(idx);
            if (siguiente == null) {
                ReporteErrores.add("SEMANTICO",
                        "Acceso inválido: '" + id + "' tiene null en dimensión " + (i + 1) + ".",
                        linea, columna);
                return;
            }

            actual = siguiente;
        }
    }
}
