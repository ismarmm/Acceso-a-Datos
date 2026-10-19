package Leer;

import java.util.List;

public class Persona {

    public String nombre;
    public List<Double> notas;

    public Persona(String nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }
}