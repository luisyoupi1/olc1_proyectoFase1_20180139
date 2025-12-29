package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;

public class IncIns implements Instruccion {
    private final String id;
    public IncIns(String id){ this.id = id; }

    @Override
    public void ejecutar(Entorno env) {
        Simbolo s = env.obtenerSimbolo(id);
        if (s == null) return;

        if (s.valor instanceof Integer i) s.valor = i + 1;
        else if (s.valor instanceof Double d) s.valor = d + 1.0;
        else if (s.valor instanceof Character c) s.valor = (char)(c + 1);
    }
}
