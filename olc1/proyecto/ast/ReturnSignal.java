/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.ast;

public class ReturnSignal extends RuntimeException {
    public final Object valor;
    public ReturnSignal(Object valor) { this.valor = valor; }
}
