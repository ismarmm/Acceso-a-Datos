package ArquitecturaTXT;

import java.util.List;

public interface IRepositorioPersonas {
    List<Persona> findAll() throws RepositorioException;
    Persona findById(String Nombre) throws RepositorioException;
    boolean existById(String Nombre) throws RepositorioException;
    int count() throws RepositorioException;
    void save(Persona persona) throws RepositorioException;
    boolean deleteById(String Nombre) throws RepositorioException;
}
