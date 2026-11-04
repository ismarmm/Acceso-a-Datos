package ArquitecturaTXT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioPersonasTxT implements IRepositorioPersonas {
    private static final Logger logger = LogManager.getLogger(RepositorioPersonasTxT.class);
    private String ruta;
    private List<Persona> personas = new ArrayList<>();

    public RepositorioPersonasTxT(String ruta) {
        this.ruta = ruta;
    }

    public List<Persona> leerPersonas(String ruta) throws FileNotFoundException {
        Scanner entrada = null;

        try {
            FileReader fichero = new FileReader(ruta);
            entrada = new Scanner(fichero);
            entrada.useLocale(Locale.ENGLISH);

            while (entrada.hasNext()) {
                String nombre = entrada.next();
                List<Double> notas = new ArrayList<>();

                while (entrada.hasNextDouble()) {
                    double nota = entrada.nextDouble();
                    notas.add(nota);
                }

                // La nota maxima y minima se calculan a partir de las notas
                Persona persona = new Persona(nombre, notas);
                personas.add(persona);
            }
        }
        finally {
            if (entrada != null) {
                entrada.close();
            }
        }

        return personas;
    }

    // Escribe todas las personas en el fichero: nombre nota1 nota2 ...
    private void escribeFichero() throws RepositorioException {
        PrintWriter salida = null;

        try {
            salida = new PrintWriter(ruta);
            for (int i = 0; i < personas.size(); i++) {
                String linea = personas.get(i).getNombre();
                List<Double> notas = personas.get(i).getNotas();
                for (int j = 0; j < notas.size(); j++) {
                    linea = linea + " " + notas.get(j);
                }
                salida.println(linea);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new RepositorioException("No se puede escribir en " + ruta, e);
        } finally {
            if (salida != null) {
                salida.close();
            }
        }
    }

    // Devuelve la posicion de la persona en la lista, o -1 si no esta
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
            try {
                leerPersonas(ruta);
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
                throw new RepositorioException("No se encuentra el fichero " + ruta, e);
            }
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
        if (!existById(persona.getNombre())) {
            personas.add(persona);
        } else {
            for (int i = 0; i < personas.size(); i++) {
                if (personas.get(i).getNombre().equals(persona.getNombre())) {
                    personas.set(i, persona);
                }
            }
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
