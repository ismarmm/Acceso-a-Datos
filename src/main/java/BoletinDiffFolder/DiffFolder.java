package BoletinDiffFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DiffFolder {
    File directorio1 ;
    File directorio2;

    public DiffFolder(String ruta1, String ruta2) throws DirectoryNotEmptyException {
        super();
    directorio1 = new File(ruta1);
    directorio2 = new File(ruta2);
    if(directorio1 != null && directorio2 != null && directorio1.isDirectory() && directorio2.isDirectory()) {
        setFolders(directorio1,directorio2);
    }
    else {
        throw new DirectoryNotEmptyException("Excepcion");
    }
}

public void setFolders(File f1, File f2) throws DirectoryNotEmptyException {
        this.directorio1 = f1;
        this.directorio2 = f2;
}

    public File getDirectorio1() {
        return directorio1;
    }

    public void setDirectorio1(File directorio1) {
        this.directorio1 = directorio1;
    }

    public File getDirectorio2() {
        return directorio2;
    }

    public void setDirectorio2(File directorio2) {
        this.directorio2 = directorio2;
    }


    public Set<Estados> comparame(File f1, File f2) {
        Set<Estados> set = new HashSet<>();
        File[]lista1 = f1.listFiles();
        File[]lista2 = f2.listFiles();
        int lista1Size = lista1.length;
        int lista2Size = lista2.length;
        
        if(lista1Size > lista2Size) {

        }
        return set;
    }
}
