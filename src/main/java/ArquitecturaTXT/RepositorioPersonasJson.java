package ArquitecturaTXT;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class RepositorioPersonasJson implements IRepositorioPersonas {
    private static final Logger logger = LogManager.getLogger(RepositorioPersonasJson.class);
    private Gson gson = new Gson();
    private String ruta;
    private List<Persona> personas = new ArrayList<>();

    public RepositorioPersonasJson(String ruta) {
        this.ruta = ruta;
    }

    // Si el fichero no existe todavia, empezamos con la lista vacia
    private void leerFichero() throws RepositorioException {
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            return;
        }

        try (FileReader lector = new FileReader(fichero)) {
            Persona[] leidas = gson.fromJson(lector, Persona[].class);
            // Si el fichero esta vacio, Gson devuelve null
            if (leidas != null) {
                for (int i = 0; i < leidas.length; i++) {
                    personas.add(validarLeida(leidas[i], i));
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RepositorioException("No se puede leer " + ruta, e);
        } catch (JsonParseException e) {
            logger.error(e.getMessage());
            throw new RepositorioException("El JSON de " + ruta + " no es valido", e);
        }
    }

    private void escribeFichero() throws RepositorioException {
        File padre = new File(ruta).getParentFile();
        if (padre != null) {
            padre.mkdirs();
        }

        try (FileWriter escritor = new FileWriter(ruta)) {
            gson.toJson(personas, escritor);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RepositorioException("No se puede escribir en " + ruta, e);
        }
    }

    // Comprueba que lo que ha leido Gson tiene sentido
    private Persona validarLeida(Persona leida, int posicion) throws RepositorioException {
        if (leida == null || leida.getNombre() == null || leida.getNotas() == null) {
            throw new RepositorioException("Persona incorrecta en la posicion " + posicion + " del JSON", null);
        }
        return leida;
    }

    private int buscarPosicion(String Nombre) {
        int posicion = -1;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getNombre().equals(Nombre)) {
                posicion = i;
            }
        }
        return posicion;
    }

    @Override
    public List<Persona> findAll() throws RepositorioException {
        if (personas.isEmpty()) {
            leerFichero();
        }
        return personas;
    }

    @Override
    public Persona findById(String Nombre) throws RepositorioException {
        List<Persona> personas = findAll();
        Persona encontrada = null;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getNombre().equals(Nombre)) {
                encontrada = personas.get(i);
            }
        }
        return encontrada;
    }

    @Override
    public boolean existById(String Nombre) throws RepositorioException {
        boolean existe = false;
        if (findById(Nombre) != null) {
            existe = true;
        }
        return existe;
    }

    @Override
    public int count() throws RepositorioException {
        findAll();
        return personas.size();
    }

    @Override
    public void save(Persona persona) throws RepositorioException {
        findAll();
        int posicion = buscarPosicion(persona.getNombre());
        if (posicion == -1) {
            personas.add(persona);
        } else {
            personas.set(posicion, persona);
        }
        escribeFichero();
    }

    @Override
    public boolean deleteById(String Nombre) throws RepositorioException {
        findAll();
        boolean borrada = false;
        int posicion = buscarPosicion(Nombre);
        if (posicion != -1) {
            personas.remove(posicion);
            escribeFichero();
            borrada = true;
        }
        return borrada;
    }
}
