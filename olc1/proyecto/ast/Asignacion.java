package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class Asignacion implements Instruccion {

    private final String id;
    private final Expresion exp;

    public Asignacion(String id, Expresion exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public void ejecutar(Entorno env) {
        Object valor = exp.evaluar(env);
        env.asignar(id, valor);
    }
}
