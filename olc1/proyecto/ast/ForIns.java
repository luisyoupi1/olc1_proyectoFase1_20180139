/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.*;

public class ForIns implements Instruccion {

    private final Instruccion init;
    private final Expresion condicion;
    private final Instruccion update;
    private final Instruccion cuerpo;

    public ForIns(Instruccion init, Expresion condicion,
                  Instruccion update, Instruccion cuerpo) {
        this.init = init;
        this.condicion = condicion;
        this.update = update;
        this.cuerpo = cuerpo;
    }

    @Override
    public void ejecutar(Entorno env) {

        // ejecutar inicialización UNA vez
        init.ejecutar(env);

        while (true) {

            Object cond = condicion.evaluar(env);

            if (!(cond instanceof Boolean) || !(Boolean) cond) {
                break;
            }

            try {
                cuerpo.ejecutar(env);
            } catch (ContinueSignal c) {
                // solo saltar al update
            } catch (BreakSignal b) {
                break;
            }

            // 
            update.ejecutar(env);
        }
    }
}
