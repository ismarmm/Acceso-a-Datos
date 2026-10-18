package BoletinDiffFolder;

public class ResultadoComparacion {

    private String nomFichero;
    private ValorComparacion valorComparacion;

    public ResultadoComparacion(String nomFichero, ValorComparacion valorComparacion) {
        this.nomFichero = nomFichero;
        this.valorComparacion = valorComparacion;
    }

    public String getNomFichero() {
        return nomFichero;
    }

    public ValorComparacion getValorComparacion() {
        return valorComparacion;
    }

    @Override
    public String toString() {
        return nomFichero + ": " + valorComparacion;
    }
}
