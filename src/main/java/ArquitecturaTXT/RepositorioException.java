package ArquitecturaTXT;

public class RepositorioException extends Exception {

    public RepositorioException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
