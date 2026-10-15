package Boletin1.EJ3.modelo;

import Boletin1.EJ3.exceptions.DirectorioException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class OperacionesFicheros {

    private static final Logger logger = Logger.getLogger(OperacionesFicheros.class.getName());
    private File directorio;

    public OperacionesFicheros(File directorio) {
        this.directorio = directorio;
    }

    public void realizarOperaciones() throws DirectorioException {
        File lectura = new File(directorio, "lectura.txt");
        File normal = new File(directorio, "normal.txt");
        File renombrado = new File(directorio, "renombrado.txt");

        crearDirectorio();
        crearFichero(lectura);
        crearFichero(normal);
        ponerSoloLectura(lectura);
        mostrarPermisos(lectura);
        mostrarPermisos(normal);
        renombrarFichero(normal, renombrado);
        borrarFichero(lectura);
        mostrarContenido();
    }

    public void crearDirectorio() throws DirectorioException {
        boolean creado = directorio.mkdir();

        if (creado) {
            logger.info("Directorio creado");
        } else if (directorio.isDirectory()) {
            logger.info("Directorio ya existe");
        } else {
            throw new DirectorioException("No se pudo crear el directorio");
        }
    }

    public void crearFichero(File fichero) {
        try {
            boolean creado = fichero.createNewFile();
            if (creado) {
                logger.info("Fichero creado: " + fichero.getName());
            } else {
                logger.info("Fichero ya existe: " + fichero.getName());
            }
        } catch (IOException error) {
            logger.warning("No se pudo crear: " + fichero.getName());
        }
    }

    public void ponerSoloLectura(File fichero) {
        boolean cambiado = fichero.setReadOnly();
        if (cambiado) {
            logger.info("Solo lectura activada");
        } else {
            logger.warning("No se pudo poner solo lectura");
        }
    }

    public void mostrarPermisos(File fichero) {
        logger.info(fichero.getName() + " -> lectura: " + fichero.canRead()
                + " | escritura: " + fichero.canWrite()
                + " | ejecución: " + fichero.canExecute());
    }

    public void renombrarFichero(File original, File nuevo) {
        boolean renombrado = original.renameTo(nuevo);
        if (renombrado) {
            logger.info("Fichero renombrado");
        } else {
            logger.warning("No se pudo renombrar");
        }
    }

    public void borrarFichero(File fichero) {
        boolean borrado = fichero.delete();

        if (!borrado) {
            logger.warning("No se pudo borrar el fichero");
            boolean permiso = fichero.setWritable(true);
            if (permiso) {
                logger.info("Escritura restaurada");
                borrado = fichero.delete();
            } else {
                logger.warning("No se pudo restaurar la escritura");
            }
        }

        if (borrado) {
            logger.info("Fichero borrado");
        } else {
            logger.warning("El fichero no se pudo borrar");
        }
    }

    public void mostrarContenido() {
        logger.info("Contenido final:");
        File[] elementos = directorio.listFiles();

        if (elementos != null) {
            for (int posicion = 0; posicion < elementos.length; posicion++) {
                logger.info(elementos[posicion].getName());
            }
        } else {
            logger.warning("No se puede mostrar el contenido");
        }
    }
}
