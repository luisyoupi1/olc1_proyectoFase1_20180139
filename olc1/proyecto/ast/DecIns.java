/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;

public class DecIns implements Instruccion {
    private final String id;
    public DecIns(String id){ this.id = id; }

    @Override
    public void ejecutar(Entorno env) {
        Simbolo s = env.obtenerSimbolo(id);
        if (s == null) return;

        if (s.valor instanceof Integer i) s.valor = i - 1;
        else if (s.valor instanceof Double d) s.valor = d - 1.0;
        else if (s.valor instanceof Character c) s.valor = (char)(c - 1);
    }
}
