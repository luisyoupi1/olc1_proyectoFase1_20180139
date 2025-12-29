/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Escape;

public class LiteralChar implements Expresion {
    private final char v;

    // raw viene como: a  o  \n  o  \t  o  \'  etc (SIN comillas)
    public LiteralChar(String raw) {
        String s = Escape.unescape(raw); // debe convertir "\n" -> salto real, "\t" -> tab, etc.
        this.v = (s != null && s.length() >= 1) ? s.charAt(0) : '\0';
    }

    @Override
    public Object evaluar(Entorno env) {
        return v;
    }
}
