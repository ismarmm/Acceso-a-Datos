package Boletin1.EJ6;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gestiona {

    private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {
        String ruta = "C:\\Users\\alumno\\Desktop\\Fullstack";
        Gestiona g = new Gestiona();
        g.filtrarExtension(ruta);
    }

    public void filtrarExtension(String ruta) {
        File directorio = new File(ruta);
        logger.info("Boletin1.Ficheros .txt usando una clase:");
        File[] txtClase = directorio.listFiles(new FiltroTxt());
        if (txtClase != null) {
            for (File f : txtClase) {
                logger.info("Nombre del fichero: " + f.getName());
            }
        } else {
            logger.error("No se ha podido mostrar el contenido del directorio.");
        }

        logger.info("Boletin1.Ficheros .txt usando una lambda:");
        File[] txtLambda = directorio.listFiles(
                (d, nombre) -> new File(d, nombre).isFile() && nombre.endsWith(".txt")
        );

        if (txtLambda != null) {
            for (File f : txtLambda) {
                logger.info("Nombre del fichero: " + f.getName());
            }
        } else {
            logger.error("No se ha podido mostrar el contenido del directorio.");
        }
    }
}

class FiltroTxt implements FilenameFilter {

    @Override
    public boolean accept(File directorio, String nombre) {
        File fichero = new File(directorio, nombre);
        return fichero.isFile() && nombre.endsWith(".txt");
    }
}