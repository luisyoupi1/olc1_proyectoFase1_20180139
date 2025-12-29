/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;

public class WhileIns implements Instruccion {

    private final Expresion condicion;
    private final Instruccion cuerpo;

    public WhileIns(Expresion condicion, Instruccion cuerpo) {
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }

    @Override
    public void ejecutar(Entorno env) {
        while ((boolean) condicion.evaluar(env)) {
            try {
                cuerpo.ejecutar(env);
            } catch (ContinueSignal c) {
                continue;
            } catch (BreakSignal b) {
                break;
            }
        }
    }
}
