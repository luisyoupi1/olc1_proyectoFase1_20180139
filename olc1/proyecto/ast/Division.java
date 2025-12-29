/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.ui.Consola;

public class Division implements Expresion {
    private final Expresion izq;
    private final Expresion der;

    public Division(Expresion izq, Expresion der) {
        this.izq = izq;
        this.der = der;
    }

    @Override
    public Object evaluar(Entorno env) {
        Object a = izq.evaluar(env);
        Object b = der.evaluar(env);

        if (a == null || b == null) return null;

        double da = CastNumerico.aDouble(a);
        double db = CastNumerico.aDouble(b);

        if (db == 0.0) {
            // registrá error como vos estés manejando errores semánticos
            Consola.escribirLinea("Error: división entre 0");
            return null;
        }
        return da / db; // SIEMPRE double
    }
}
