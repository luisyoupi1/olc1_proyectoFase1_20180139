/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.reportes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class ReporteAST {

    public static void generarHTML(String rutaSalida, String astTexto, String gramaticaTexto) throws Exception {

        if (astTexto == null) astTexto = "";
        if (gramaticaTexto == null) gramaticaTexto = "";

        File f = new File(rutaSalida);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(f, StandardCharsets.UTF_8))) {

            bw.write("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
              <meta charset="UTF-8">
              <title>Reporte AST</title>
              <style>
                body { font-family: Arial; background:#111; color:#eee; }
                h1,h2 { color:#58a6ff; }
                pre {
                    background:#1e1e1e;
                    border:1px solid #333;
                    padding:10px;
                    overflow:auto;
                }
                .box { margin-bottom:20px; }
              </style>
            </head>
            <body>
              <h1>Reporte AST</h1>

              <div class="box">
                <h2>Árbol de Sintaxis Abstracta</h2>
                <pre>
            """);

            bw.write(escape(astTexto));

            bw.write("""
                </pre>
              </div>

              <div class="box">
                <h2>Gramática</h2>
                <pre>
            """);

            bw.write(escape(gramaticaTexto));

            bw.write("""
                </pre>
              </div>

            </body>
            </html>
            """);
        }
    }

    private static String escape(String s) {
        return s
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;");
    }
}
