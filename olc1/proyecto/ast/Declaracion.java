package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;
import static olc1.proyecto.interprete.Tipo.BOOL;
import static olc1.proyecto.interprete.Tipo.CHAR;
import static olc1.proyecto.interprete.Tipo.DOUBLE;
import static olc1.proyecto.interprete.Tipo.INT;
import static olc1.proyecto.interprete.Tipo.STRING;

public class Declaracion implements Instruccion {
    private final String id;
    private final Tipo tipo;
    private final Expresion exp;

    public Declaracion(String id, Tipo tipo, Expresion exp){
        this.id = id;
        this.tipo = tipo;
        this.exp = exp;
    }

    @Override
    public void ejecutar(Entorno env) {
        Object valor = (exp != null) ? exp.evaluar(env) : valorPorDefecto(tipo);
        env.declarar(new Simbolo(id, tipo, valor, 0, 0));
    }

    private Object valorPorDefecto(Tipo t) {
        return switch (t) {
            case INT -> 0;
            case DOUBLE -> 0.0;
            case BOOL -> false;
            case CHAR -> '\0';
            case STRING -> "";
            default -> null;
        };
    }
}