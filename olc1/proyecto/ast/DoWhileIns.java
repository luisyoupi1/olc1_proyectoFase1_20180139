/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;

public class DoWhileIns implements Instruccion {

    private final Expresion condicion;
    private final Instruccion cuerpo;

    public DoWhileIns(Expresion condicion, Instruccion cuerpo) {
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }

    @Override
    public void ejecutar(Entorno env) {
        do {
            try {
                cuerpo.ejecutar(env);
            } catch (ContinueSignal c) {
                // sigue
            } catch (BreakSignal b) {
                break;
            }
        } while ((boolean) condicion.evaluar(env));
    }
}
