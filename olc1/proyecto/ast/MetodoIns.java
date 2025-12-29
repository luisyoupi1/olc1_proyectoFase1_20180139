/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.ReturnSignal;
import olc1.proyecto.reportes.ReporteErrores;

public class MetodoIns implements Instruccion {
    public final String id;
    public final List<Parametro> params;
    public final Instruccion cuerpo;
    public final int linea;
    public final int columna;

    public MetodoIns(String id, List params, Instruccion cuerpo, int linea, int columna) {
        this.id = id;
        this.params = (List<Parametro>) params;
        this.cuerpo = cuerpo;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        if (env.existeMetodo(id)) {
            ReporteErrores.add("SEMANTICO", "Método duplicado: " + id, linea, columna);
            return;
        }
        env.declararMetodo(id, this);
    }

    // 👉 necesario para LlamadaExp
    public Object ejecutarLlamada(Entorno envPadre, List<Object> args) {
        Entorno local = new Entorno(envPadre);

        int nParams = (params == null) ? 0 : params.size();
        int nArgs = (args == null) ? 0 : args.size();

        if (nParams != nArgs) {
            ReporteErrores.add(
                "SEMANTICO",
                "Cantidad de parámetros incorrecta en método '" + id +
                "'. Esperado " + nParams + " y llegó " + nArgs,
                linea, columna
            );
            return null;
        }

        // declarar parámetros como variables locales
        for (int i = 0; i < nParams; i++) {
            Parametro p = params.get(i);
            Object val = args.get(i);
            local.declarar(
                new olc1.proyecto.interprete.Simbolo(
                    p.id, p.tipo, val, p.linea, p.columna
                )
            );
        }

        try {
            cuerpo.ejecutar(local);
        } catch (ReturnSignal rs) {
            return rs.valor; // ← AQUÍ ESTABA EL ERROR
        }

        return null;
    }
}
