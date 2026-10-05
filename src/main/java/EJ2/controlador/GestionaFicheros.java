package EJ2.controlador;

import EJ2.exceptions.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class GestionaFicheros {

    public static void mostrarInformacion(String ruta) throws RutaNoValidaException, IOException {
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            throw new RutaNoValidaException("La ruta introducida no existe: " + ruta);
        }

        System.out.println("Nombre: " + fichero.getName());
        System.out.println("Ruta introducida: " + ruta);
        System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
        System.out.println("Ruta canónica: " + fichero.getCanonicalPath());
        System.out.println("Directorio padre: " + fichero.getAbsoluteFile().getParent());

        if (fichero.isFile()) {
            System.out.println("Tipo: Fichero");
        } else if (fichero.isDirectory()) {
            System.out.println("Tipo: Directorio");
        }

        System.out.println("Permiso de lectura: " + fichero.canRead());
        System.out.println("Permiso de escritura: " + fichero.canWrite());
        System.out.println("Permiso de ejecución: " + fichero.canExecute());
        System.out.println("Oculto: " + fichero.isHidden());
        System.out.println("Tamaño en bytes: " + fichero.length());

        if (fichero.isDirectory()) {
            File[] elementos = fichero.listFiles();
            if (elementos != null) {
                System.out.println("Número de elementos: " + elementos.length);
            } else {
                System.out.println("No se puede acceder al contenido del directorio");
            }
        }

        Date fecha = new Date(fichero.lastModified());
        System.out.println("Última modificación: " + fecha);
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce una ruta: ");
        String ruta = teclado.nextLine();

        try {
            mostrarInformacion(ruta);
        } catch (RutaNoValidaException error) {
            System.out.println(error.getMessage());
        } catch (IOException error) {
            System.out.println("Error al obtener la ruta canónica: " + error.getMessage());
        }

        teclado.close();
    }
}
