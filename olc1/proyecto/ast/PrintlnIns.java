package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.ui.Consola;

public class PrintlnIns implements Instruccion {
    private final Expresion exp;
    public PrintlnIns(Expresion exp){ this.exp = exp; }

    @Override
    public void ejecutar(Entorno env) {
        Object v = exp.evaluar(env);
        Consola.escribirLinea(String.valueOf(v));
    }
}
