/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.BreakSignal;
import olc1.proyecto.interprete.Entorno;

public class BreakIns implements Instruccion {
    @Override
    public void ejecutar(Entorno env) {
        throw new BreakSignal();
    }
}
