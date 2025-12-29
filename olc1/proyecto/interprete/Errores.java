/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

import java.util.ArrayList;
import java.util.List;

public class Errores {
    public static final List<ErrorInfo> lista = new ArrayList<>();

    public static void limpiar() {
        lista.clear();
    }

    public static void add(String tipo, String descripcion, int linea, int columna) {
        lista.add(new ErrorInfo(tipo, descripcion, linea, columna));
    }
}
