/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Entorno;

public class SwitchIns implements Instruccion {
    private final Expresion exp;
    private final List<CasoSwitch> casos;

    public SwitchIns(Expresion exp, List<CasoSwitch> casos) {
        this.exp = exp;
        this.casos = casos;
    }

    @Override
    public void ejecutar(Entorno env) {
        Object val = exp.evaluar(env);

        boolean ejecutando = false;
        CasoSwitch def = null;

        for (CasoSwitch c : casos) {
            if (c.esDefault()) def = c;
        }

        for (CasoSwitch c : casos) {
            if (!ejecutando && !c.esDefault()) {
                Object casoVal = c.getValor().evaluar(env);
                if (Igualdad.iguales(val, casoVal)) {
                    ejecutando = true;
                }
            }

            if (ejecutando) {
                c.ejecutar(env); // ejecuta lista de instrucciones del case
                // si manejas BreakIns con excepción, aquí capturás break para salir del switch
            }
        }

        if (!ejecutando && def != null) {
            def.ejecutar(env);
        }
    }
}
