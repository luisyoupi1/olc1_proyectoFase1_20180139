/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.ArrayList;
import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;
import olc1.proyecto.interprete.Tipo;

public class ListDeclaracion implements Instruccion {
    private final String id;
    private final Tipo tipoBase;
    private final int linea;
    private final int columna;

    public ListDeclaracion(String id, Tipo tipoBase, int linea, int columna) {
        this.id = id;
        this.tipoBase = tipoBase;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public void ejecutar(Entorno env) {
        // Guardamos la lista como java.util.List (ArrayList)
        // TipoBase solo lo usamos si luego querés validar.
        env.declarar(new Simbolo(id, tipoBase, new ArrayList<>(), linea, columna));
    }
}
