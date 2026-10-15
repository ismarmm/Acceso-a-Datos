package Boletin1.EJ3.controlador;

import Boletin1.EJ3.exceptions.DirectorioException;
import Boletin1.EJ3.modelo.OperacionesFicheros;

import java.io.File;
import java.util.logging.Logger;

public class GestionaDirectorio {

    private static final Logger logger = Logger.getLogger(GestionaDirectorio.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");

        File directorio = new File(System.getProperty("user.home"), "miDirectorio");
        OperacionesFicheros operaciones = new OperacionesFicheros(directorio);

        try {
            operaciones.realizarOperaciones();
        } catch (DirectorioException error) {
            logger.warning(error.getMessage());
        }
    }
}
