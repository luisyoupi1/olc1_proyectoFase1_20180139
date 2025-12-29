/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Tipo;

public class FuncDef {
    public final String id;
    public final Tipo retorno;
    public final List<Param> params;
    public final Bloque cuerpo;
    public final int linea, columna;

    public FuncDef(String id, Tipo retorno, List<Param> params, Bloque cuerpo, int linea, int columna) {
        this.id = id;
        this.retorno = retorno;
        this.params = params;
        this.cuerpo = cuerpo;
        this.linea = linea;
        this.columna = columna;
    }
}
