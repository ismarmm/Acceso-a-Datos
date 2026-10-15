package Boletin1.EJ8;

import java.io.File;
import java.util.Comparator;

public class ComparadorTamano implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        return (int) (o1.length() - o2.length());
    }
}
