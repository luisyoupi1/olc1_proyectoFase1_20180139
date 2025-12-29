/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto.interprete;

import java.util.HashMap;
import java.util.Map;

import olc1.proyecto.ast.FuncionIns;
import olc1.proyecto.ast.MetodoIns;

public class Entorno {
    private final Entorno anterior;

    // VARIABLES
    private final Map<String, Simbolo> tabla = new HashMap<>();

    // FUNCIONES (con retorno)
    private final Map<String, FuncionIns> funciones = new HashMap<>();

    // METODOS (void)
    private final Map<String, MetodoIns> metodos = new HashMap<>();

    public Entorno(Entorno anterior) {
        this.anterior = anterior;
    }

    public Entorno getAnterior() {
        return anterior;
    }

    // ===================== VARIABLES =====================
    public void declarar(Simbolo s) {
        tabla.put(s.id, s);
    }

    public Simbolo obtenerSimbolo(String id) {
        for (Entorno e = this; e != null; e = e.anterior) {
            Simbolo s = e.tabla.get(id);
            if (s != null) return s;
        }
        return null;
    }

    public void asignar(String id, Object valor) {
        for (Entorno e = this; e != null; e = e.anterior) {
            Simbolo s = e.tabla.get(id);
            if (s != null) {
                s.valor = valor;
                return;
            }
        }
        // opcional: ReporteErrores.add(...) si quieres error por variable no declarada
    }

    public Map<String, Simbolo> getTablaActual() {
        return tabla;
    }

    // ===================== FUNCIONES =====================
    public boolean existeFuncion(String id) {
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.funciones.containsKey(id)) return true;
        }
        return false;
    }

    public void declararFuncion(String id, FuncionIns fn) {
        funciones.put(id, fn);
    }

    public FuncionIns obtenerFuncion(String id) {
        for (Entorno e = this; e != null; e = e.anterior) {
            FuncionIns f = e.funciones.get(id);
            if (f != null) return f;
        }
        return null;
    }

    // ===================== METODOS =====================
    public boolean existeMetodo(String id) {
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.metodos.containsKey(id)) return true;
        }
        return false;
    }

    public void declararMetodo(String id, MetodoIns m) {
        metodos.put(id, m);
    }

    public MetodoIns obtenerMetodo(String id) {
        for (Entorno e = this; e != null; e = e.anterior) {
            MetodoIns m = e.metodos.get(id);
            if (m != null) return m;
        }
        return null;
    }
    
    public Map<String, olc1.proyecto.ast.FuncionIns> getFuncionesActuales() {
    return funciones;
}

public Map<String, olc1.proyecto.ast.MetodoIns> getMetodosActuales() {
    return metodos;
}

}
