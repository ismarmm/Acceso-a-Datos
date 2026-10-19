package Escribir;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import Leer.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gestiona {
    private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {
        Gestiona gestion = new Gestiona();
        gestion.guardarPersonas();
    }

    public void guardarPersonas() {
        List<Double> notas = new ArrayList<>();
        notas.add(3.0);
        notas.add(4.0);
        notas.add(6.0);
        notas.add(8.0);
        notas.add(9.0);
        notas.add(11.0);
        Persona persona = new Persona("Andrea", notas);
        List<Persona> personas = new ArrayList<>();
        personas.add(persona);
        String ruta = "src/main/resources/fichero1.txt";
        escribePersonas(personas, ruta);
    }

    public void escribePersonas(List<Persona> personas, String rutaFichero) {
        PrintWriter out = null;

        try {
            FileWriter ficheroSalida;
            ficheroSalida = new FileWriter(rutaFichero, StandardCharsets.UTF_8);
            out = new PrintWriter(ficheroSalida);

            for (int i = 0; i < personas.size(); i++) {
                Persona persona = personas.get(i);
                out.printf(Locale.ENGLISH, "%-10s", persona.nombre);

                for (int j = 0; j < persona.notas.size(); j++) {
                    double nota = persona.notas.get(j);
                    out.printf(Locale.ENGLISH, "%6.2f", nota);
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}