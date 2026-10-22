package BoletinDiffFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiffFolder {

    private File folder1;
    private File folder2;

    private static final Logger logger = LogManager.getLogger(DiffFolder.class);

    public void setFolders(File folder1, File folder2) throws GestionFicherosException {
        if (folder1 == null || folder2 == null) {
            String mensaje = "Debes indicar los dos directorios.";
            logger.error(mensaje);
            throw new GestionFicherosException(mensaje);
        }

        if (!folder1.isDirectory() || !folder2.isDirectory()) {
            String mensaje = "Las dos rutas deben ser directorios existentes.";
            logger.error(mensaje);
            throw new GestionFicherosException(mensaje);
        }

        this.folder1 = folder1;
        this.folder2 = folder2;
    }

    public File getFolder1() {
        return folder1;
    }

    public File getFolder2() {
        return folder2;
    }

    private File[] listarFicheros(File directorio) throws DiffFolderException {
        if (directorio == null) {
            throw new DiffFolderException("Debes asignar los directorios antes de comparar.");
        }

        File[] elementos = directorio.listFiles();

        if (elementos == null) {
            throw new DiffFolderException("No se puede leer el directorio: " + directorio);
        }

        return elementos;
    }

    private int contarFicheros(File[] elementos) {
        int total = 0;

        for (int i = 0; i < elementos.length; i++) {
            if (elementos[i].isFile()) {
                total++;
            }
        }

        return total;
    }

    private ValorComparacion compararFechas(File fichero1, File fichero2)
            throws DiffFolderException {

        if (!fichero1.isFile()) {
            throw new DiffFolderException("Falta en el primer directorio: " + fichero1.getName());
        }

        if (!fichero2.isFile()) {
            throw new DiffFolderException("Falta en el segundo directorio: " + fichero2.getName());
        }

        ValorComparacion valor = ValorComparacion.IGUALES;
        long fecha1 = fichero1.lastModified();
        long fecha2 = fichero2.lastModified();

        if (fecha1 > fecha2) {
            valor = ValorComparacion.MENOS_NUEVO_EN_1;
        } else if (fecha2 > fecha1) {
            valor = ValorComparacion.MENOS_NUEVO_EN_2;
        }

        return valor;
    }

    private ResultadoComparacion compararFichero(String nombre) {
        File fichero1 = new File(folder1, nombre);
        File fichero2 = new File(folder2, nombre);
        ValorComparacion valor;

        try {
            valor = compararFechas(fichero1, fichero2);
        }
        catch (DiffFolderException e) {
            logger.error(e.getMessage());

            if (!fichero1.isFile()) {
                valor = ValorComparacion.FALTA_EN_1;
            } else {
                valor = ValorComparacion.FALTA_EN_2;
            }
        }

        ResultadoComparacion resultado = new ResultadoComparacion(nombre, valor);
        return resultado;
    }

    public Collection<ResultadoComparacion> comparame() throws DiffFolderException {
        Collection<ResultadoComparacion> resultados = new ArrayList<>();

        try {
            File[] elementos1 = listarFicheros(folder1);
            File[] elementos2 = listarFicheros(folder2);

            int total1 = contarFicheros(elementos1);
            int total2 = contarFicheros(elementos2);

            File[] primero;
            File[] segundo;
            File directorioPrimero;

            if (total1 >= total2) {
                primero = elementos1;
                segundo = elementos2;
                directorioPrimero = folder1;
            } else {
                primero = elementos2;
                segundo = elementos1;
                directorioPrimero = folder2;
            }

            for (int i = 0; i < primero.length; i++) {
                if (primero[i].isFile()) {
                    resultados.add(compararFichero(primero[i].getName()));
                }
            }

            for (int i = 0; i < segundo.length; i++) {
                if (segundo[i].isFile()) {
                    String nombre = segundo[i].getName();
                    File fichero = new File(directorioPrimero, nombre);

                    if (!fichero.isFile()) {
                        resultados.add(compararFichero(nombre));
                    }
                }
            }
        } catch (DiffFolderException e) {
            logger.error(e.getMessage());
            throw e;
        }

        return resultados;
    }
}
