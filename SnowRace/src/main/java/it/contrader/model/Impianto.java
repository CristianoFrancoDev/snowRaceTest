package it.contrader.model;

/**
 * Classe Impianto: definisce un impianto sciistico
 */
public class Impianto
{
    /**
     * Variabili di istanza
     */
    private int id;
    private String titolo;
    private String descrizione;
    private String foto;
    private double prezzo;

    /**
     * Costruttore vuoto
     */
    public Impianto(){
    }

    /**
     * Costruttore con parametro id
     */
    public Impianto(int id) {
        this.id = id;
    }

    /**
     * Costruttore con 4 parametri
     */
    public Impianto(String titolo, String descrizione, String foto, double prezzo)
    {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.foto = foto;
        this.prezzo = prezzo;
    }

    /**
     * Costruttore con 5 parametri
     */
    public Impianto(int id, String titolo, String descrizione, String foto, double prezzo)
    {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.foto = foto;
        this.prezzo = prezzo;
    }

    /**
     * Metodi gettere e setter
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Impianto impianto = (Impianto) o;

        return getId() == impianto.getId();
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Impianti{" +
                "titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", foto='" + foto + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
