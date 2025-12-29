/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.analizadores;

import java.io.File;

public class GeneradorLexico {
    public static void main(String[] args) {
        try {
            String rutaFlex = "src/olc1/proyecto/analizadores/Lexer.flex";
            File flexFile = new File(rutaFlex);

            System.out.println("Intentando generar Lexer desde: " + flexFile.getAbsolutePath());

            if (!flexFile.exists()) {
                System.out.println("ERROR: No existe Lexer.flex en esa ruta.");
                return;
            }

            // -d src => respeta el package y crea en src/olc1/proyecto/analizadores/Lexer.java
            jflex.Main.generate(new String[]{"-d", "src", flexFile.getPath()});

            File lexerOut = new File("src/olc1/proyecto/analizadores/Lexer.java");
            System.out.println("Ruta Lexer.java: " + lexerOut.getAbsolutePath());
            System.out.println("¿Existe Lexer.java? " + (lexerOut.exists() ? "SI ✅" : "NO ❌"));

        } catch (Exception e) {
            System.out.println("Error al generar el lexer:");
            e.printStackTrace();
        }
    }
}
