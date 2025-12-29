/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.reportes;

import olc1.proyecto.interprete.Entorno;
import olc1.proyecto.interprete.Simbolo;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ReporteTablaSimbolos {

    public static void generarHTML(String path, Entorno envGlobal) {
        try (FileWriter fw = new FileWriter(path, StandardCharsets.UTF_8)) {

            fw.write("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
              <meta charset="UTF-8">
              <title>Tabla de Símbolos</title>
              <style>
                body{font-family:Arial;margin:20px;}
                table{border-collapse:collapse;width:100%;}
                th,td{border:1px solid #ccc;padding:8px;text-align:left;}
                th{background:#f2f2f2;}
              </style>
            </head>
            <body>
              <h2>Tabla de Símbolos</h2>
              <table>
                <tr>
                  <th>#</th>
                  <th>ID</th>
                  <th>Tipo</th>
                  <th>Valor</th>
                  <th>Línea</th>
                  <th>Columna</th>
                </tr>
            """);

            int idx = 1;

            // Recorremos todos los entornos encadenados
            Entorno e = envGlobal;
            while (e != null) {
                for (Map.Entry<String, Simbolo> entry : e.getTablaActual().entrySet()) {
                    Simbolo s = entry.getValue();
                    fw.write("<tr>");
                    fw.write("<td>" + (idx++) + "</td>");
                    fw.write("<td>" + escape(s.id) + "</td>");
                    fw.write("<td>" + escape(String.valueOf(s.tipo)) + "</td>");
                    fw.write("<td>" + escape(String.valueOf(s.valor)) + "</td>");
                    fw.write("<td>" + s.linea + "</td>");
                    fw.write("<td>" + s.columna + "</td>");
                    fw.write("</tr>");
                }
                e = e.getAnterior();
            }

            fw.write("""
              </table>
            </body>
            </html>
            """);

        } catch (Exception ex) {
            System.err.println("No se pudo generar reporte TS: " + ex.getMessage());
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;")
                .replace("<","&lt;")
                .replace(">","&gt;")
                .replace("\"","&quot;");
    }
}
