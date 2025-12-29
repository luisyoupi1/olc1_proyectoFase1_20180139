/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;
import olc1.proyecto.interprete.Tipo;
import olc1.proyecto.interprete.ReturnSignal;
import olc1.proyecto.reportes.ReporteErrores;

public class FuncionIns implements Instruccion {
    public final String id;
    public final Tipo retorno;           // null si es void
    public final List<Parametro> params;
    public final Instruccion cuerpo;     // Bloque
    public final int linea;
    public final int columna;

    public FuncionIns(String id, Tipo retorno, List params, Instruccion cuerpo, int linea, int columna) {
        this.id = id;
        this.retorno = retorno;
        this.params = (List<Parametro>) params;
        this.cuerpo = cuerpo;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        // registra función en entorno
        if (env.existeFuncion(id)) {
            ReporteErrores.add("SEMANTICO", "Función duplicada: " + id, linea, columna);
            return;
        }
        env.declararFuncion(id, this);
    }

    // ===================== LLAMADA (para LlamadaExp) =====================
    public Object ejecutarLlamada(Entorno envPadre, List<Object> args) {
        Entorno local = new Entorno(envPadre);

        int nParams = (params == null) ? 0 : params.size();
        int nArgs = (args == null) ? 0 : args.size();

        if (nParams != nArgs) {
            ReporteErrores.add("SEMANTICO",
                    "Cantidad de parámetros incorrecta en función '" + id + "'. Esperado " + nParams + " y llegó " + nArgs,
                    linea, columna);
            return null;
        }

        // bind parámetros en el entorno local
        for (int i = 0; i < nParams; i++) {
            Parametro p = params.get(i);
            Object val = args.get(i);
            local.declarar(new Simbolo(p.id, p.tipo, val, p.linea, p.columna));
        }

        // ejecutar cuerpo y capturar return
        try {
            cuerpo.ejecutar(local);
        } catch (ReturnSignal rs) {
            // IMPORTANTE: tu ReturnSignal debe tener campo "valor"
            Object ret = rs.valor;

            // si es void y devolvió algo, lo puedes ignorar o reportar
            if (retorno == null) return ret;

            // si no devolvió nada
            if (ret == null) {
                ReporteErrores.add("SEMANTICO",
                        "Función '" + id + "' debe retornar un valor de tipo " + retorno + ".",
                        linea, columna);
                return null;
            }

            // aquí podrías validar tipo retorno si quieres (fase 2)
            return ret;
        }

        // si no hizo return
        if (retorno != null) {
            ReporteErrores.add("SEMANTICO",
                    "Función '" + id + "' debe retornar un valor de tipo " + retorno + ".",
                    linea, columna);
        }
        return null;
    }
}
