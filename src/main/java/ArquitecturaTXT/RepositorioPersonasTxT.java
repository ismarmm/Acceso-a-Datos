package ArquitecturaTXT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioPersonasTxT implements IRepositorioPersonas {
    private static final Logger logger = LogManager.getLogger(RepositorioPersonasTxT.class);
    private List<Persona> personas = new ArrayList<>();

    public static void main(String[] args) {
        RepositorioPersonasTxT gestion = new RepositorioPersonasTxT();
        List<Persona> personas = gestion.findAll();
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

                Persona persona = new Persona(2.3, 8.6, nombre, notas);
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

    @Override
    public List<Persona> findAll() {
        if (personas == null && personas.isEmpty()) {
            try {
                leerPersonas("src/main/resources/fichero3.txt");
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            }
        }
        return personas;
    }

    @Override
    public Persona findById(String Nombre) {
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
    public boolean existById(String Nombre) {
        boolean existe = false;
        if (findById(Nombre) != null) {
            existe = true;
        }
        return existe;
    }

    @Override
    public int count() {
        return personas.size();
    }

    @Override
    public void save(Persona persona) {
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
    }


}