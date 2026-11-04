package ArquitecturaTXT;

import java.util.List;

public interface IServicioPersonas {
    List<Persona> listar();
    Persona buscar(String nombre);
    boolean alta(String nombre, List<Double> notas);
    boolean modificarNotas(String nombre, List<Double> notas);
    boolean baja(String nombre);
    double media(Persona p);
}
