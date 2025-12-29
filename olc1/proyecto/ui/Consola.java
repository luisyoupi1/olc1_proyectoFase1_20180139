/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ui;


import javax.swing.JTextArea;

public class Consola {

    private static JTextArea area;

    public static void setArea(JTextArea txt) {
        area = txt;
    }

    public static void limpiar() {
        if (area != null) {
            area.setText("");
        }
    }

    public static void escribir(String texto) {
        if (area != null) {
            area.append(texto);
        }
    }

    public static void escribirLinea(String texto) {
        if (area != null) {
            area.append(texto + "\n");
        }
    }
}
