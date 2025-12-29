package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Suma implements Expresion {
    private final Expresion a, b;
    public Suma(Expresion a, Expresion b){ this.a=a; this.b=b; }

    @Override
    public Object evaluar(Entorno env) {
        Object x = a.evaluar(env);
        Object y = b.evaluar(env);

        // concatenación si alguno es string
        if (x instanceof String || y instanceof String) {
            return String.valueOf(x) + String.valueOf(y);
        }

        // numérico
        if (x instanceof Double || y instanceof Double) {
            return AstUtil.toDouble(x) + AstUtil.toDouble(y);
        }
        return AstUtil.toInt(x) + AstUtil.toInt(y);
    }
}
