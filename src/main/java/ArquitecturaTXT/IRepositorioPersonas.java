package ArquitecturaTXT;

import java.util.List;

public interface IRepositorioPersonas {
    List<Persona> findAll();
    Persona findById(String Nombre);
    boolean existById(String Nombre);
}
