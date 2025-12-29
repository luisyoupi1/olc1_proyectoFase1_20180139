/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;
import olc1.proyecto.reportes.ReporteErrores;

public class VectorDeclaracion implements Instruccion {
    private final String id;
    private final Tipo tipoBase;
    private final int dims;
    private final Expresion init;
    private final int linea;
    private final int columna;

    public VectorDeclaracion(String id, Tipo tipoBase, int dims, Expresion init, int linea, int columna) {
        this.id = id;
        this.tipoBase = tipoBase;
        this.dims = dims;
        this.init = init;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
public void ejecutar(Entorno env) {
    Object valor = null;

    // DEBUG (antes de evaluar init)
    olc1.proyecto.ui.Consola.escribirLinea("DEBUG dims=" + dims + " tipo=" + tipoBase + " id=" + id);

    if (init != null) {
        valor = init.evaluar(env);

        // DEBUG (después de evaluar init)
        olc1.proyecto.ui.Consola.escribirLinea("DEBUG init de " + id + ": " + valor);

        if (!(valor instanceof java.util.List)) {
            ReporteErrores.add("SEMANTICO",
                    "Inicialización inválida de vector '" + id + "'. Se esperaba [ ... ].",
                    linea, columna);
            valor = null;

        } else {
            if (!VectorUtils.validarDims(valor, dims)) {
                ReporteErrores.add("SEMANTICO",
                        "Dimensiones no coinciden en '" + id + "'. Declarado con " + dims + "D.",
                        linea, columna);
                valor = null;

            } else if (!VectorUtils.validarTipoBase(valor, tipoBase)) {
                ReporteErrores.add("SEMANTICO",
                        "Tipo inválido dentro del vector '" + id + "'. Se esperaba " + tipoBase + ".",
                        linea, columna);
                valor = null;
            }
        }
    }

    env.declarar(new Simbolo(id, tipoBase, valor, linea, columna));
}
}