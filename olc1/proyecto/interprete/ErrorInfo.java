/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

public class ErrorInfo {
    public final String tipo; // LEXICO/SINTACTICO/SEMANTICO
    public final String descripcion;
    public final int linea;
    public final int columna;

    public ErrorInfo(String tipo, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }
}
