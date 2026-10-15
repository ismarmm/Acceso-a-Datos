package Boletin1.EJ8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;


public class Gestiona {
    private static final String RUTA = "C:\\Users\\alumno\\Desktop\\2ºDAM\\ACCESO A DATOS\\ACCESO DATOS NUEVO";
    private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {

        String carpetaUsuario = System.getProperty(RUTA);
        File directorio = new File(carpetaUsuario, RUTA);

        File[] contenido = directorio.listFiles();

        if (contenido != null) {

            // Ordenar por tamaño, de mayor a menor
            Arrays.sort(contenido, new ComparadorTamano());

            logger.info("Los 5 ficheros más grandes:");

            for (int i = 0; i < contenido.length && i < 5; i++) {
                logger.info(contenido[i].getName()
                        + " - Tamaño: " + contenido[i].length() + " bytes");
            }

            // Ordenar por fecha, de más reciente a más antiguo
            Arrays.sort(contenido, Comparator.comparingLong(File::lastModified).reversed());

            logger.info("Boletin1.Ficheros ordenados por fecha de modificación:");

            for (File fichero : contenido) {
                logger.info(fichero.getName() + " - Fecha: " + fichero.lastModified());
            }

        } else {
            logger.error("No se ha podido mostrar el contenido del directorio.");
        }
    }


}
