/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;


public class Simbolo {
    public String id;
    public Tipo tipo;
    public Object valor;
    public int linea, columna;

    public int dims; // <- NUEVO (0 para variables normales)

    public Simbolo(String id, Tipo tipo, Object valor, int linea, int columna) {
        this(id, tipo, valor, linea, columna, 0);
    }

    public Simbolo(String id, Tipo tipo, Object valor, int linea, int columna, int dims) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.dims = dims;
    }
}
