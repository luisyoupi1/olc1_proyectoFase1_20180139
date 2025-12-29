/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class Bloque implements Instruccion {
    private final List<Instruccion> ins;

    public Bloque(List<Instruccion> ins){ this.ins = ins; }

    @Override
    public void ejecutar(Entorno env) {
        for (Instruccion i : ins) i.ejecutar(env);
    }
}
