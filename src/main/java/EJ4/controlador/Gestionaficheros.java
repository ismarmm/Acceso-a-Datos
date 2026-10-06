package EJ4.controlador;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

public class Gestionaficheros {
    public static void main(String[] args) {
        Gestionaficheros programa = new Gestionaficheros();
        programa.LeerRuta();
    }
    private final Logger logger = Logger.getLogger(Gestionaficheros.class.getName());

    public void LeerRuta() {
        Scanner teclado = new Scanner(System.in);
        logger.info("Introduce la ruta de un directorio:");
        File directorio = new File(teclado.nextLine());

        if (directorio.isDirectory()) { //si quiero que en vez de un directorio sea um archivo o whatever cambia esta linea
            listarContenido(directorio);
        } else {
            logger.warning("La ruta no existe o no es un directorio.");
        }

        teclado.close();
    }

    public void listarContenido(File directorio) {
        File[] elementos = directorio.listFiles();
        if (elementos != null) {
            for (int i = 0; i < elementos.length; i++) {
                File elemento = elementos[i];
                if (elemento.isDirectory()) {
                    listarContenido(elemento);
                }
                else if (elemento.isFile()) {
                    logger.info("Ruta: " + elemento.getAbsolutePath());
                    logger.info("Nombre: " + elemento.getName());
                }
            }
        }
        else {
            logger.warning("No se puede acceder a: " + directorio.getAbsolutePath());
        }
    }


}