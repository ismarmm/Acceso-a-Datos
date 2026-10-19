package Leer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gestiona {

    private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {
        Gestiona gestion = new Gestiona();
        String ruta = "src/main/resources/fichero1.txt";
        try {
            List<Persona> personas = gestion.leerPersonas(ruta);
            gestion.mostrarPersonas(personas);
        }
        catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Persona> leerPersonas(String ruta) throws FileNotFoundException {
        List<Persona> personas = new ArrayList<>();
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

    public void mostrarPersonas(List<Persona> personas) {
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);
            logger.info("Alumno: " + persona.nombre);

            for (int j = 0; j < persona.notas.size(); j++) {
                logger.info("Nota: " + persona.notas.get(j));
            }
        }
    }
}