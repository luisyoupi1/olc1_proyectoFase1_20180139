/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

import java.util.List;
import olc1.proyecto.interprete.Tipo;

public class VectorUtils {

    private VectorUtils() {}

    // ==============================
    //  VALIDAR DIMENSIONES
    // ==============================
    public static boolean validarDims(Object valor, int dimsEsperadas) {
        if (!(valor instanceof List<?>)) return false;
        return profundidad((List<?>) valor) == dimsEsperadas;
    }

    // Calcula la profundidad real del vector/matriz:
    // [1,2,3] -> 1
    // [[1,2],[3,4]] -> 2
    // [[[...]]] -> 3
    private static int profundidad(List<?> lista) {
        if (lista == null) return 1;
        if (lista.isEmpty()) return 1; // vacío => mínimo 1D

        Object first = lista.get(0);
        if (first instanceof List<?>) {
            return 1 + profundidad((List<?>) first);
        }
        return 1;
    }

    // ==============================
    //  VALIDAR TIPO BASE (recursivo)
    // ==============================
    public static boolean validarTipoBase(Object valor, Tipo tipoBase) {
        if (!(valor instanceof List<?>)) return false;
        return validarTipoRec((List<?>) valor, tipoBase);
    }

    private static boolean validarTipoRec(List<?> lista, Tipo tipoBase) {
        if (lista == null) return true;
        for (Object x : lista) {
            if (x instanceof List<?>) {
                if (!validarTipoRec((List<?>) x, tipoBase)) return false;
            } else {
                if (!valorCompatible(tipoBase, x)) return false;
            }
        }
        return true;
    }

    // ==============================
    //  COMPATIBILIDAD DE TIPOS
    // ==============================
    public static boolean valorCompatible(Tipo tipo, Object valor) {
        if (tipo == null) return false;

        // null permitido SOLO si tu lenguaje lo permite.
        // Si NO querés permitir null, devolvé false aquí.
        if (valor == null) return true;

        switch (tipo) {
            case INT:
                return valor instanceof Integer;

            case DOUBLE:
                // Aceptar int como double (opcional, útil para que no truene)
                return (valor instanceof Double) || (valor instanceof Integer);

            case BOOL:
                return valor instanceof Boolean;

            case CHAR:
                return valor instanceof Character;

            case STRING:
                return valor instanceof String;

            default:
                return false; // cubre VOID, NULL, etc.
        }
    }

    // ==============================
    //  LENGTH()
    // ==============================
    public static int length(Object obj) {
        if (obj == null) return 0;

        if (obj instanceof List<?> l) return l.size();

        if (obj instanceof String s) return s.length();

        // si después manejás arreglos nativos:
        if (obj.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(obj);
        }

        return 0;
    }

    // ==============================
    //  GET ELEMENTO multi-dim
    // ==============================
    public static Object getElemento(Object base, List<Integer> indices) {
        Object actual = base;

        for (int i = 0; i < indices.size(); i++) {
            if (!(actual instanceof List<?>)) return null;

            int idx = indices.get(i);
            List<?> lista = (List<?>) actual;

            if (idx < 0 || idx >= lista.size()) return null;

            actual = lista.get(idx);
        }
        return actual;
    }

    // ==============================
    //  SET ELEMENTO multi-dim
    // ==============================
    public static boolean setElemento(Object base, List<Integer> indices, Object nuevoValor) {
        if (!(base instanceof List<?>)) return false;
        if (indices == null || indices.isEmpty()) return false;

        Object actual = base;

        for (int i = 0; i < indices.size() - 1; i++) {
            if (!(actual instanceof List<?>)) return false;

            int idx = indices.get(i);
            List<?> lista = (List<?>) actual;

            if (idx < 0 || idx >= lista.size()) return false;

            actual = lista.get(idx);
        }

        if (!(actual instanceof List<?>)) return false;

        int last = indices.get(indices.size() - 1);
        List listaFinal = (List) actual;

        if (last < 0 || last >= listaFinal.size()) return false;

        listaFinal.set(last, nuevoValor);
        return true;
    }
}
