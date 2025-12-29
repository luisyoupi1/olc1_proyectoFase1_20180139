/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;

public class StartCall {
    public final String id;
    public final List<Expresion> args;
    public final int linea, columna;

    public StartCall(String id, List<Expresion> args, int linea, int columna) {
        this.id = id;
        this.args = args;
        this.linea = linea;
        this.columna = columna;
    }
}

