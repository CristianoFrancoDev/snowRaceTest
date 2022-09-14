package model;

public class Pista
{
    private int numero;
    private Impianto impianto;

    public Pista(int numero, Impianto impianto)
    {
        this.impianto = impianto;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    @Override
    public String toString() {
        return "Piste{" +
                "numero=" + numero +
                ", impianto=" + impianto +
                '}';
    }
}
