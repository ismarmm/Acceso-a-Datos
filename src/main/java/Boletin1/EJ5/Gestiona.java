package Boletin1.EJ5;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gestiona {
    private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {
        String ruta = "C:\\Users\\alumno\\Desktop\\Fullstack";
        Gestiona g = new Gestiona();
        g.mostrarDirectorio(ruta);
    }

    public void mostrarDirectorio(String ruta) {
        File directorio = new File(ruta);
        int tamano = 0;
        for (File f : directorio.listFiles() ) {
            if (f.isDirectory()) {
                logger.debug("Entro en siguiente directorio...");
                mostrarDirectorio(f.getPath());
            }
            else if(f.isFile()) {
                logger.debug("Nombre del fichero: " + f.getName());
                tamano+= f.length();
            }
        }
        double megabytes = (double) tamano / (1024 * 1024);
        double redondeado = Math.round(megabytes);
        logger.info("Tamaño total de los archivos: " + redondeado + "MB");
    }
}
