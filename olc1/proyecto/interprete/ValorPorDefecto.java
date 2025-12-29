/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

public class ValorPorDefecto {
    public static Object de(Tipo t) {
        return switch (t) {
            case INT -> 0;
            case DOUBLE -> 0.0;
            case BOOL -> false;
            case CHAR -> '\0';
            case STRING -> "";
            default -> null;
        };
    }
}
