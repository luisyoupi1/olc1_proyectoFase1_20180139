package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;

public class Acceso implements Expresion {
    private final String id;

    public Acceso(String id){ this.id = id; }

    @Override
    public Object evaluar(Entorno env) {
        Simbolo s = env.obtenerSimbolo(id);
        return (s != null) ? s.valor : null;
    }
}
