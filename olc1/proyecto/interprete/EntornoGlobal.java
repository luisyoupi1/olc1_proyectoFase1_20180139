/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

public class EntornoGlobal {

    private static Entorno entorno = new Entorno(null); // si tu Entorno usa "anterior"

    private EntornoGlobal() {}

    public static Entorno get() {
        return entorno;
    }

    public static void limpiar() {
        // reinicia el entorno global (más seguro que limpiar mapas internos)
        entorno = new Entorno(null);
    }
}

