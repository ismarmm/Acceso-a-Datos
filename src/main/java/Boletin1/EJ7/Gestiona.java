package Boletin1.EJ7;

import Boletin1.EJ3.modelo.OperacionesFicheros;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Gestiona {
    private static final Logger logger = LogManager.getLogger(OperacionesFicheros.class);

    public static void main(String[] args) {
        File dir = new File("C:\\Users\\alumno\\Desktop\\Fullstack");
        Gestiona g =  new Gestiona();
        List lista = g.buscar(dir, "ej");
        for(int indice = 0; indice < lista.size(); indice++){
            logger.info("Alumno: " + lista.get(indice));
        }

    }
    public List<File> buscar(File dir, String nombre){
        List lista =  new ArrayList<File>();
        contienecadena(nombre, lista, dir);
        return lista;
    }

    public void contienecadena (String nombre, List lista, File dir){
        for (File fichero : dir.listFiles()){
            if (fichero.isFile()){
                if (fichero.getName().contains(nombre)){
                    lista.add(fichero);
                }
            }
            else if (fichero.isDirectory()){
                if (fichero.getName().contains(nombre)){
                    lista.add(fichero);
                }
                contienecadena(nombre, lista, fichero);
            }
        }
    }

}
