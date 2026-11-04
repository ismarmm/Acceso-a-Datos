package ArquitecturaTXT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String RUTA_DATOS = "src/main/resources/fichero3.txt";

    public static void main(String[] args) {
        IServicioPersonas servicio = new ServicioPersonas(RUTA_DATOS);
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            System.out.println("1. Listar personas");
            System.out.println("2. Buscar persona");
            System.out.println("3. Alta de persona");
            System.out.println("4. Modificar notas");
            System.out.println("5. Baja de persona");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            if (opcion == 1) {
                List<Persona> lista = servicio.listar();
                if (lista.isEmpty()) {
                    System.out.println("No hay personas.");
                }
                for (int i = 0; i < lista.size(); i++) {
                    Persona p = lista.get(i);
                    System.out.println(p + " -> media: " + servicio.media(p));
                }
            } else if (opcion == 2) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                Persona p = servicio.buscar(nombre);
                if (p != null) {
                    System.out.println(p + " -> media: " + servicio.media(p));
                } else {
                    System.out.println("No se ha encontrado.");
                }
            } else if (opcion == 3) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                List<Double> notas = pedirNotas(teclado);
                if (notas != null && servicio.alta(nombre, notas)) {
                    System.out.println("Persona dada de alta.");
                }
            } else if (opcion == 4) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                List<Double> notas = pedirNotas(teclado);
                if (notas != null && servicio.modificarNotas(nombre, notas)) {
                    System.out.println("Notas modificadas.");
                }
            } else if (opcion == 5) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                if (servicio.baja(nombre)) {
                    System.out.println("Persona eliminada.");
                } else {
                    System.out.println("No se ha podido eliminar.");
                }
            } else if (opcion != 0) {
                System.out.println("Opcion no valida.");
            }
        }

        teclado.close();
        System.out.println("Adios.");
    }

    // Lee una linea tipo "7.5 8 6.25" y la convierte en lista. Devuelve null si hay un error
    private static List<Double> pedirNotas(Scanner teclado) {
        System.out.print("Notas (separadas por espacios): ");
        String linea = teclado.nextLine().trim();
        List<Double> notas = new ArrayList<>();

        if (!linea.isEmpty()) {
            String[] partes = linea.split("\\s+");
            try {
                for (int i = 0; i < partes.length; i++) {
                    notas.add(Double.parseDouble(partes[i].replace(',', '.')));
                }
            } catch (NumberFormatException e) {
                System.out.println("Alguna nota no es un numero.");
                notas = null;
            }
        }
        return notas;
    }
}
