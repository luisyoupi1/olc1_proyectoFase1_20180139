/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReporteGramatica {

    public static void generarHTML(String rutaSalidaHtml, String rutaCup, String rutaJflex) throws Exception {
        String cup = leer(rutaCup);
        String jflex = leer(rutaJflex);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalidaHtml))) {
            bw.write("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                  <meta charset="UTF-8"/>
                  <title>Reporte Gramática</title>
                  <style>
                    body{font-family:Arial, sans-serif; margin:20px;}
                    h1{margin-bottom:8px;}
                    pre{background:#f6f6f6; padding:12px; border:1px solid #ddd; overflow:auto;}
                    .box{margin-bottom:20px;}
                  </style>
                </head>
                <body>
                  <h1>Reporte de Gramática</h1>
            """);

            bw.write("<div class='box'><h2>Parser.cup</h2><pre>");
            bw.write(esc(cup));
            bw.write("</pre></div>");

            bw.write("<div class='box'><h2>Lexer.jflex</h2><pre>");
            bw.write(esc(jflex));
            bw.write("</pre></div>");

            bw.write("""
                </body>
                </html>
            """);
        }
    }

    private static String leer(String ruta) throws Exception {
        if (ruta == null || ruta.isBlank()) return "(ruta vacía)";
        if (!Files.exists(Paths.get(ruta))) return "(no existe: " + ruta + ")";
        return Files.readString(Paths.get(ruta));
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;")
                .replace("<","&lt;")
                .replace(">","&gt;")
                .replace("\"","&quot;");
    }
}
