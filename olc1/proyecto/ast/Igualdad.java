/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

public final class Igualdad {
    private Igualdad() {}

    public static boolean iguales(Object a, Object b) {
        if (a == null || b == null) return a == b;
        if (a instanceof Number && b instanceof Number) {
            return CastNumerico.aDouble(a) == CastNumerico.aDouble(b);
        }
        return a.equals(b);
    }
}
