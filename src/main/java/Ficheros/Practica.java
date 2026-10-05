package Ficheros;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Practica {
    private static final Logger logger = LogManager.getLogger(Practica.class);

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce la ruta de un directorio: ");
        String ruta = teclado.nextLine();
    }

    public static void mostrarContenido(String ruta) throws RutaNoValidaException {

        File directorio = new File(ruta);

        if (!directorio.exists()) {
            throw new RutaNoValidaException("La ruta no existe.");
        }

        if (!directorio.isDirectory()) {
            throw new RutaNoValidaException("La ruta no corresponde a un directorio.");
        }

        File[] elementos = directorio.listFiles();

        if (elementos == null) {
            System.out.println("No se puede leer el contenido del directorio.");
        }
        else {
            int ficheros = 0;
            int directorios = 0;

            for (int i = 0; i < elementos.length; i++) {
                if (elementos[i].isFile()) {
                    System.out.println("[F] " + elementos[i].getName());
                    ficheros++;
                } else if (elementos[i].isDirectory()) {
                    System.out.println("[D] " + elementos[i].getName());
                    directorios++;
                }
            }

            System.out.println("Total de ficheros: " + ficheros);
            System.out.println("Total de directorios: " + directorios);
        }
    }

}