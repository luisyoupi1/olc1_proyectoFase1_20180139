/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class CasoSwitch {
    private final Expresion valor; // null si default
    private final List<Instruccion> instrucciones;
    private final boolean isDefault;

    public CasoSwitch(Expresion valor, List<Instruccion> instrucciones, boolean isDefault) {
        this.valor = valor;
        this.instrucciones = instrucciones;
        this.isDefault = isDefault;
    }

    public boolean esDefault() { return isDefault; }
    public Expresion getValor() { return valor; }

    public void ejecutar(Entorno env) {
        for (Instruccion ins : instrucciones) {
            ins.ejecutar(env);
        }
    }
}
