/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ReporteErrores {

    public static class ErrorItem {
        public final String tipo;
        public final String descripcion;
        public final int linea;
        public final int columna;

        public ErrorItem(String tipo, String descripcion, int linea, int columna) {
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.linea = linea;
            this.columna = columna;
        }
    }

    private static final List<ErrorItem> errores = new ArrayList<>();

    public static void add(String tipo, String descripcion, int linea, int columna) {
        errores.add(new ErrorItem(tipo, descripcion, linea, columna));
    }

    public static void clear() {
        errores.clear();
    }

    public static List<ErrorItem> getErrores() {
        return errores;
    }

    public static void generarHTML(String ruta) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                  <meta charset="UTF-8"/>
                  <title>Reporte de Errores</title>
                  <style>
                    body{font-family:Arial, sans-serif; margin:20px;}
                    h1{margin-bottom:10px;}
                    table{border-collapse:collapse; width:100%;}
                    th,td{border:1px solid #ccc; padding:8px; text-align:left;}
                    th{background:#f2f2f2;}
                    .tipo{font-weight:bold;}
                  </style>
                </head>
                <body>
                  <h1>Reporte de Errores</h1>
                """);

            bw.write("<p>Total: " + errores.size() + "</p>");

            bw.write("""
                <table>
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Tipo</th>
                      <th>Descripción</th>
                      <th>Línea</th>
                      <th>Columna</th>
                    </tr>
                  </thead>
                  <tbody>
                """);

            int i = 1;
            for (ErrorItem e : errores) {
                bw.write("<tr>");
                bw.write("<td>" + (i++) + "</td>");
                bw.write("<td class='tipo'>" + esc(e.tipo) + "</td>");
                bw.write("<td>" + esc(e.descripcion) + "</td>");
                bw.write("<td>" + e.linea + "</td>");
                bw.write("<td>" + e.columna + "</td>");
                bw.write("</tr>");
            }

            bw.write("""
                  </tbody>
                </table>
                </body>
                </html>
            """);
        }
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;")
                .replace("<","&lt;")
                .replace(">","&gt;")
                .replace("\"","&quot;");
    }
}
