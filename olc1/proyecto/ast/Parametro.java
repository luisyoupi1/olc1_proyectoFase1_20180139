/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Tipo;

public class Parametro {
    public final Tipo tipo;
    public final String id;
    public final int linea;
    public final int columna;

    public Parametro(Tipo tipo, String id, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.linea = linea;
        this.columna = columna;
    }
}
