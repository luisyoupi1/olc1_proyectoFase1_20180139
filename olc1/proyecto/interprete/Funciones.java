/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

import java.util.HashMap;
import java.util.Map;
import olc1.proyecto.ast.FuncDef;

public class Funciones {
    private static final Map<String, FuncDef> tabla = new HashMap<>();

    public static void limpiar() { tabla.clear(); }

    public static boolean registrar(FuncDef f) {
        if (tabla.containsKey(f.id.toLowerCase())) return false;
        tabla.put(f.id.toLowerCase(), f);
        return true;
    }

    public static FuncDef obtener(String id) {
        if (id == null) return null;
        return tabla.get(id.toLowerCase());
    }
}
