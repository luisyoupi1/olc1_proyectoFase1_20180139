/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import olc1.proyecto.interprete.Entorno;

public class NewListExp implements Expresion {

    private final int linea;
    private final int columna;

    public NewListExp(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object evaluar(Entorno env) {
        return new ArrayList<Object>();
    }
}
