/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.reportes;

import java.util.List;

public class ASTTexto {

    public static String generar(Object raiz) {
        StringBuilder sb = new StringBuilder();
        recorrer(raiz, sb, 0);
        return sb.toString();
    }

    private static void recorrer(Object nodo, StringBuilder sb, int nivel) {
        if (nodo == null) return;

        indent(sb, nivel);
        sb.append(nodo.getClass().getSimpleName()).append("\n");

        if (nodo instanceof List<?> lista) {
            for (Object o : lista) {
                recorrer(o, sb, nivel + 1);
            }
        }
    }

    private static void indent(StringBuilder sb, int n) {
        for (int i = 0; i < n; i++) sb.append("  ");
    }
}
