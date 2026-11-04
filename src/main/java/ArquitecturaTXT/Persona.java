package ArquitecturaTXT;

import java.util.List;

public class Persona {
    public static final double NOTA_MIN = 0;
    public static final double NOTA_MAX = 10;

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

    // Constructor del diagrama: calcula solo la nota maxima y la minima
    public Persona(String nombre, List<Double> notas) {
        Nombre = nombre;
        Notas = notas;
        Nota_Maxima = 0;
        Nota_Minima = 0;
        if (!notas.isEmpty()) {
            Nota_Maxima = notas.get(0);
            Nota_Minima = notas.get(0);
            for (int i = 1; i < notas.size(); i++) {
                if (notas.get(i) > Nota_Maxima) {
                    Nota_Maxima = notas.get(i);
                }
                if (notas.get(i) < Nota_Minima) {
                    Nota_Minima = notas.get(i);
                }
            }
        }
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
