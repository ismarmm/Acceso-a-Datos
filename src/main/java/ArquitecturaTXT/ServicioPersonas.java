package ArquitecturaTXT;

import java.util.ArrayList;
import java.util.List;

public class ServicioPersonas implements IServicioPersonas {
    private IRepositorioPersonas repositorio;

    public ServicioPersonas(String ruta) {
        repositorio = new RepositorioPersonasTxT(ruta);
        // Para usar JSON en vez de TXT (la ruta deberia acabar en .json):
        // repositorio = new RepositorioPersonasJson(ruta);
    }

    private void datoNoValido(String mensaje) {
        System.out.println("Dato no valido: " + mensaje);
    }

    private void errorPersistencia(RepositorioException e) {
        System.out.println("Error de persistencia: " + e.getMessage());
        if (e.getCause() != null) {
            System.out.println("Causa: " + e.getCause().getMessage());
        }
    }

    // Comprueba nombre y notas. Devuelve true si todo es correcto
    private boolean datosCorrectos(String nombre, List<Double> notas) {
        if (nombre == null || nombre.trim().isEmpty()) {
            datoNoValido("el nombre no puede estar vacio");
            return false;
        }
        // El fichero TXT se lee con next(), asi que el nombre no puede tener espacios
        if (nombre.contains(" ")) {
            datoNoValido("el nombre no puede tener espacios");
            return false;
        }
        if (notas == null) {
            datoNoValido("la lista de notas no puede ser null");
            return false;
        }
        for (int i = 0; i < notas.size(); i++) {
            double nota = notas.get(i);
            if (nota < Persona.NOTA_MIN || nota > Persona.NOTA_MAX) {
                datoNoValido("la nota " + nota + " debe estar entre "
                        + Persona.NOTA_MIN + " y " + Persona.NOTA_MAX);
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();
        try {
            lista = repositorio.findAll();
        } catch (RepositorioException e) {
            errorPersistencia(e);
        }
        return lista;
    }

    @Override
    public Persona buscar(String nombre) {
        Persona encontrada = null;
        if (nombre == null || nombre.trim().isEmpty()) {
            datoNoValido("el nombre no puede estar vacio");
        } else {
            try {
                encontrada = repositorio.findById(nombre);
            } catch (RepositorioException e) {
                errorPersistencia(e);
            }
        }
        return encontrada;
    }

    @Override
    public boolean alta(String nombre, List<Double> notas) {
        boolean hecho = false;
        if (datosCorrectos(nombre, notas)) {
            try {
                if (repositorio.existById(nombre)) {
                    datoNoValido("ya existe una persona llamada " + nombre);
                } else {
                    repositorio.save(new Persona(nombre, notas));
                    hecho = true;
                }
            } catch (RepositorioException e) {
                errorPersistencia(e);
            }
        }
        return hecho;
    }

    @Override
    public boolean modificarNotas(String nombre, List<Double> notas) {
        boolean hecho = false;
        if (datosCorrectos(nombre, notas)) {
            try {
                if (repositorio.existById(nombre)) {
                    repositorio.save(new Persona(nombre, notas));
                    hecho = true;
                } else {
                    datoNoValido("no existe ninguna persona llamada " + nombre);
                }
            } catch (RepositorioException e) {
                errorPersistencia(e);
            }
        }
        return hecho;
    }

    @Override
    public boolean baja(String nombre) {
        boolean hecho = false;
        if (nombre == null || nombre.trim().isEmpty()) {
            datoNoValido("el nombre no puede estar vacio");
        } else {
            try {
                hecho = repositorio.deleteById(nombre);
            } catch (RepositorioException e) {
                errorPersistencia(e);
            }
        }
        return hecho;
    }

    @Override
    public double media(Persona p) {
        double suma = 0;
        double media = 0;
        if (p != null && !p.getNotas().isEmpty()) {
            for (int i = 0; i < p.getNotas().size(); i++) {
                suma = suma + p.getNotas().get(i);
            }
            media = suma / p.getNotas().size();
        }
        return media;
    }
}
