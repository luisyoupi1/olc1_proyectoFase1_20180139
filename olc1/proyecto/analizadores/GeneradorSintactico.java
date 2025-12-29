/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.analizadores;

import java.io.File;

public class GeneradorSintactico {

    public static void main(String[] args) {
        try {
            String rutaCup = "src/olc1/proyecto/analizadores/Parser.cup";
            File cupFile = new File(rutaCup);

            System.out.println("Intentando generar Parser y sym desde Parser.cup...");
            System.out.println("RUTA REAL USADA: " + cupFile.getAbsolutePath());

            if (!cupFile.exists()) {
                System.out.println("ERROR: No existe Parser.cup en esa ruta.");
                return;
            }

            // 👇 DESTINO EXACTO al folder del paquete
            String destDir = "src/olc1/proyecto/analizadores";
            File dest = new File(destDir);
            dest.mkdirs();

            String[] cupArgs = {
                "-parser", "Parser",
                "-symbols", "sym",
                "-package", "olc1.proyecto.analizadores",
                "-destdir", destDir,
                cupFile.getPath()
            };

            java_cup.Main.main(cupArgs);

            // Espera breve para filesystem
            Thread.sleep(150);

            File parserOut = new File(destDir + "/Parser.java");
            File symOut = new File(destDir + "/sym.java");

            System.out.println("--------------------------------------------------");
            System.out.println("Ruta Parser.java: " + parserOut.getAbsolutePath());
            System.out.println("¿Existe Parser.java? " + (parserOut.exists() ? "SI ✅" : "NO ❌"));
            System.out.println("Ruta sym.java: " + symOut.getAbsolutePath());
            System.out.println("¿Existe sym.java? " + (symOut.exists() ? "SI ✅" : "NO ❌"));
            System.out.println("--------------------------------------------------");

            if (!parserOut.exists() || !symOut.exists()) {
                System.out.println("Contenido real de " + dest.getAbsolutePath() + ":");
                File[] files = dest.listFiles();
                if (files != null) for (File f : files) System.out.println(" - " + f.getName());
            }

        } catch (Exception ex) {
            System.out.println("Error al generar el parser:");
            ex.printStackTrace();
        }
    }
}
