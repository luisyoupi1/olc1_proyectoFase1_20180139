/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

public final class CastNumerico {
    private CastNumerico() {}

    public static double aDouble(Object v) {
        if (v instanceof Integer) return ((Integer) v).doubleValue();
        if (v instanceof Double) return (Double) v;
        if (v instanceof Character) return (double) ((Character) v);
        throw new RuntimeException("No es numérico: " + v);
    }
}
 