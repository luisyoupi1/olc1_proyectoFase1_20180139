/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Escape;

public class LiteralString implements Expresion {
    private final String v;

    public LiteralString(String raw) {
        // raw viene como: "hola\n"
        this.v = Escape.unescape(raw);
    }

    @Override public Object evaluar(Entorno env) { return v; }
}
