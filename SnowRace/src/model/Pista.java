package model;

/**
 * Classe Pista: definisce la pista legata ad un impianto sciistico
 */
public class Pista
{
    /**
     * Variabili di istanza
     */
    private int id;
    private String titolo;
    private Impianto impianto;

    /**
     * Costruttore vuoto
     */
    public Pista(){

    }
    /**
     * Costruttore con parametro id
     */
    public Pista(int id) {
        this.id = id;
    }

    /**
     * Costruttore con 2 parametri
     */
    public Pista(String titolo, Impianto impianto)
    {
        this.impianto = impianto;
        this.titolo = titolo;
    }

    /**
     * Costruttore con 3 parametri
     */
    public Pista(int id, String titolo, Impianto impianto)
    {
        this.id = id;
        this.titolo = titolo;
        this.impianto = impianto;
    }

    /**
     * Metodi getter e setter
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pista pista = (Pista) o;

        return getId() == pista.getId();
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Piste{" +
                "titolo=" + titolo +
                ", impianto=" + impianto +
                '}'+"\n";
    }
}
