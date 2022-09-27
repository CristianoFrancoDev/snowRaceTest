package it.contrader.model;

/**
 * Classe Attrezzatura: definisce l'attrezzatura che l'utente può noleggiare
 */
public class Attrezzatura
{
    /**
     * Variabili di istanza
     */
    private int id;
    private String articolo;

    /**
     * Costruttore vuoto
     */
    public Attrezzatura() {
    }

    /**
     * Costruttore con parametro id
     */
    public Attrezzatura(int id)
    {
        this.id = id;
    }

    /**
     * Costruttore con un solo parametro
     */
    public Attrezzatura(String articolo) {
        this.articolo = articolo;
    }

    /**
     * Costruttore con un due parametri
     */
    public Attrezzatura(int id, String articolo)
    {
        this.id = id;
        this.articolo = articolo;
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

    public String getArticolo() {
        return articolo;
    }

    public void setArticolo(String articolo) {
        this.articolo = articolo;
    }


    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attrezzatura that = (Attrezzatura) o;

        return getId() == that.getId();
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Attrezzatura{" +
                "id=" + id +
                ", articolo='" + articolo + '\'' +
                '}';
    }
}
