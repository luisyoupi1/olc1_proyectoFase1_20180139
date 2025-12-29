/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

public final class AstUtil {
    private AstUtil() {}

    public static boolean isNumber(Object v) {
        return v instanceof Integer || v instanceof Double || v instanceof Character;
    }

    public static double toDouble(Object v) {
        if (v instanceof Double d) return d;
        if (v instanceof Integer i) return i.doubleValue();
        if (v instanceof Character c) return (double) c.charValue();
        if (v instanceof Boolean b) return b ? 1.0 : 0.0;
        if (v instanceof String s) {
            try { return Double.parseDouble(s); } catch (Exception ignore) {}
        }
        return 0.0;
    }

    public static int toInt(Object v) {
        if (v instanceof Integer i) return i;
        if (v instanceof Double d) return (int) d.doubleValue();
        if (v instanceof Character c) return (int) c.charValue();
        if (v instanceof Boolean b) return b ? 1 : 0;
        if (v instanceof String s) {
            try { return Integer.parseInt(s); } catch (Exception ignore) {}
        }
        return 0;
    }

    public static char toChar(Object v) {
        if (v instanceof Character c) return c;
        if (v instanceof Integer i) return (char) i.intValue();
        if (v instanceof Double d) return (char) ((int) d.doubleValue());
        if (v instanceof String s && !s.isEmpty()) return s.charAt(0);
        return '\0';
    }

    public static boolean toBool(Object v) {
        if (v instanceof Boolean b) return b;
        if (v instanceof Integer i) return i != 0;
        if (v instanceof Double d) return d != 0.0;
        if (v instanceof Character c) return c != '\0';
        if (v instanceof String s) return !s.isEmpty();
        return false;
    }

    public static boolean numEq(Object a, Object b) {
        return Double.compare(toDouble(a), toDouble(b)) == 0;
    }
}
