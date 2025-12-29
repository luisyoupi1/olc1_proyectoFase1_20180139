/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;

public class Casteo implements Expresion {

    private final Tipo tipo;
    private final Expresion exp;

    public Casteo(Tipo tipo, Expresion exp) {
        this.tipo = tipo;
        this.exp = exp;
    }

    @Override
    public Object evaluar(Entorno env) {
        Object v = exp.evaluar(env);

        if (v == null) return null;

        return switch (tipo) {
            case INT -> {
                if (v instanceof Double d) yield d.intValue();
                if (v instanceof Character c) yield (int) c;
                if (v instanceof Integer i) yield i;
                yield 0;
            }
            case DOUBLE -> {
                if (v instanceof Integer i) yield i.doubleValue();
                if (v instanceof Character c) yield (double) c;
                if (v instanceof Double d) yield d;
                yield 0.0;
            }
            case CHAR -> {
                if (v instanceof Integer i) yield (char) i.intValue();
                yield '\0';
            }
            default -> v;
        };
    }
}
