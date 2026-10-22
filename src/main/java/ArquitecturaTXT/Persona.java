package ArquitecturaTXT;

import java.util.List;

public class Persona {
    private double Nota_Maxima;
    private double Nota_Minima;
    private String Nombre;
    private List<Double>Notas;

    public Persona(double nota_Maxima, double nota_Minima, String nombre, List<Double> notas) {
        Nota_Maxima = nota_Maxima;
        Nota_Minima = nota_Minima;
        Nombre = nombre;
        Notas = notas;
    }

    public String getNombre() {
        return Nombre;
    }

    public List<Double> getNotas() {
        return Notas;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Nota_Maxima=" + Nota_Maxima +
                ", Nota_Minima=" + Nota_Minima +
                ", Nombre='" + Nombre + '\'' +
                ", Notas=" + Notas +
                '}';
    }

}


