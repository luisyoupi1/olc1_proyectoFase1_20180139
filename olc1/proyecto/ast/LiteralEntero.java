/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;

public class LiteralEntero implements Expresion {
    private final int v;
    public LiteralEntero(int v){ this.v = v; }

    @Override public Object evaluar(Entorno env) { return v; }
}
