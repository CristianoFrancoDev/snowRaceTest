package classi;

public class Piste {
    private int numero;
    private Impianti impianto;
    public Piste(int numero, Impianti impianto){
        this.impianto=impianto;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Impianti getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianti impianto) {
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
