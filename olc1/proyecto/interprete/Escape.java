/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

public class Escape {

    /**
     * Recibe un literal con comillas:
     *  - "hola\n"  o  'a'  o  '\n'
     * y devuelve el contenido ya interpretado (sin comillas).
     */
    public static String unescape(String raw) {
        if (raw == null) return "";

        // quitar comillas externas si vienen
        String s = raw;
        if (s.length() >= 2) {
            char a = s.charAt(0);
            char b = s.charAt(s.length() - 1);
            if ((a == '"' && b == '"') || (a == '\'' && b == '\'')) {
                s = s.substring(1, s.length() - 1);
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '\\' && i + 1 < s.length()) {
                char n = s.charAt(i + 1);
                i++; // consumir el siguiente

                switch (n) {
                    case 'n': out.append('\n'); break;
                    case 't': out.append('\t'); break;
                    case 'r': out.append('\r'); break;
                    case '"': out.append('\"'); break;
                    case '\'': out.append('\''); break;
                    case '\\': out.append('\\'); break;
                    default:
                        // si viene algo raro, lo dejamos literal
                        out.append(n);
                        break;
                }
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }
}
