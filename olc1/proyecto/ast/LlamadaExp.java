/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import java.util.List;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.reportes.ReporteErrores;

public class LlamadaExp implements Expresion {

    private final String id;
    private final List<Expresion> args;
    private final int linea;
    private final int columna;

    public LlamadaExp(String id, List args, int linea, int columna) {
        this.id = id;
        this.args = (List<Expresion>) args;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object evaluar(Entorno env) {

        // ================== NATIVAS ==================
        String fid = id.toLowerCase();

        // length(x): x puede ser String o List (vector/matriz/lista dinámica)
        if (fid.equals("length")) {
            if (args == null || args.size() != 1) {
                ReporteErrores.add("SEMANTICO", "length(x) requiere 1 argumento.", linea, columna);
                return 0;
            }

            Object obj = args.get(0).evaluar(env);
            if (obj == null) return 0;

            if (obj instanceof String s) return s.length();
            if (obj instanceof List l) return l.size();

            ReporteErrores.add("SEMANTICO", "length(x) solo aplica a String o List.", linea, columna);
            return 0;
        }

        // round(x): retorna int
        if (fid.equals("round")) {
            if (args == null || args.size() != 1) {
                ReporteErrores.add("SEMANTICO", "round(x) requiere 1 argumento.", linea, columna);
                return 0;
            }
            Object x = args.get(0).evaluar(env);
            if (!(x instanceof Number)) {
                ReporteErrores.add("SEMANTICO", "round(x) requiere número.", linea, columna);
                return 0;
            }
            return (int) Math.round(((Number) x).doubleValue());
        }

        // toString(x): retorna String
        if (fid.equals("tostring")) {
            if (args == null || args.size() != 1) {
                ReporteErrores.add("SEMANTICO", "toString(x) requiere 1 argumento.", linea, columna);
                return "";
            }
            Object x = args.get(0).evaluar(env);
            return String.valueOf(x);
        }

        // ================== LLAMADA NORMAL ==================
        FuncionIns f = env.obtenerFuncion(id);
        MetodoIns m = env.obtenerMetodo(id);

        if (f == null && m == null) {
            ReporteErrores.add("SEMANTICO", "No existe método/función '" + id + "'.", linea, columna);
            return null;
        }

        List<Object> valores = new ArrayList<>();
        if (args != null) {
            for (Expresion e : args) {
                valores.add(e.evaluar(env));
            }
        }

        if (f != null) return f.ejecutarLlamada(env, valores);
        return m.ejecutarLlamada(env, valores);
    }
}
