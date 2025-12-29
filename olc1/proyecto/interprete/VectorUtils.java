/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

import java.util.List;

public class VectorUtils {

    // profundidad real: [1,2] => 1, [[1],[2]] => 2, etc.
    public static int profundidad(Object v) {
        if (!(v instanceof List<?>)) return 0;
        List<?> l = (List<?>) v;
        if (l.isEmpty()) return 1; // [] cuenta como 1 dimensión
        // si el primer elemento es lista, asumimos anidación
        int max = 1;
        for (Object it : l) {
            int p = profundidad(it);
            if (p > 0) max = Math.max(max, 1 + (p - 1)); // mantiene base
        }
        // Si hay listas adentro, sube dimensión
        boolean hayLista = false;
        for (Object it : l) if (it instanceof List) { hayLista = true; break; }
        return hayLista ? 1 + profundidad(l.get(0)) : 1;
    }

    // Valida que sea lista y que su anidación cumpla dims (permite [] en cualquier nivel)
    public static boolean validarDims(Object v, int dims) {
        if (dims <= 0) return false;
        if (!(v instanceof List<?>)) return false;
        return validarDimsRec(v, dims);
    }

    private static boolean validarDimsRec(Object v, int dimsRestantes) {
        if (!(v instanceof List<?>)) return false;
        List<?> l = (List<?>) v;

        if (dimsRestantes == 1) {
            // último nivel: NO debe contener listas (idealmente)
            // pero permitimos [] vacío (no contiene nada)
            for (Object it : l) {
                if (it instanceof List) return false;
            }
            return true;
        }

        // dimsRestantes > 1 : cada elemento debe ser lista (o permitir vacíos)
        for (Object it : l) {
            if (it == null) continue;
            if (!(it instanceof List)) return false;
            if (!validarDimsRec(it, dimsRestantes - 1)) return false;
        }
        return true;
    }

    // valida tipo base en las hojas (int/double/bool/char/string)
    public static boolean validarTipoBase(Object v, Tipo base) {
        if (!(v instanceof List<?>)) return false;
        return validarTipoRec(v, base);
    }

    private static boolean validarTipoRec(Object v, Tipo base) {
        if (!(v instanceof List<?>)) {
            return esCompatible(v, base);
        }
        for (Object it : (List<?>) v) {
            if (!validarTipoRec(it, base)) return false;
        }
        return true;
    }

    private static boolean esCompatible(Object val, Tipo base) {
        if (val == null) return true; // si querés ser estricto: false
        return switch (base) {
            case INT -> (val instanceof Integer);
            case DOUBLE -> (val instanceof Double) || (val instanceof Integer);
            case BOOL -> (val instanceof Boolean);
            case CHAR -> (val instanceof Character) || (val instanceof String && ((String)val).length()==1);
            case STRING -> (val instanceof String);
            default -> true;
        };
    }
}
