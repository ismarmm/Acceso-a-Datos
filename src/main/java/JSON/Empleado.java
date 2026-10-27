package JSON;

public class Empleado {
    private String identificador;
    private String nombreApellido;
    private int edad;
    private String empresa;

    public Empleado(String identificador, String nombreApellido, int edad, String empresa) {
        this.identificador = identificador;
        this.nombreApellido = nombreApellido;
        this.edad = edad;
        this.empresa = empresa;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "identificador='" + identificador + '\'' +
                ", nombreApellido='" + nombreApellido + '\'' +
                ", edad=" + edad +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
