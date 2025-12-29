/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class VectorLiteral implements Expresion {

    private final List<Expresion> elems;

    public VectorLiteral(List elems) {
        this.elems = (List<Expresion>) elems;
    }

    @Override
    public Object evaluar(Entorno env) {
        List<Object> out = new ArrayList<>();

        for (Expresion e : elems) {
            Object v = e.evaluar(env);

            // Si un elemento ES otro VectorLiteral, su evaluar ya devuelve List<Object>
            out.add(v);
        }

        return out;
    }
}
