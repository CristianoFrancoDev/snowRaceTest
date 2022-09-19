package model;

import java.time.LocalDate;

/**
 * Classe Biglietto: definisce l'oggetto biglietto che l'utente può acquistare
 */
public class Biglietto
{
    /**
     * Variabili di istanza
     */
    private int id;
    private Utente utente;
    private Pista pista;
    private LocalDate data;

    /**
     * Costruttore vuoto
     */
    public Biglietto(){
    }

    /**
     * Costruttore con parametro id
     */
    public Biglietto(int id){
        this.id = id;
    }

    /**
     * Costruttore con 3 parametri
     */
    public Biglietto(Utente utente, Pista pista, LocalDate date) {
        this.utente = utente;
        this.pista = pista;
        this.data = date;
    }

    /**
     * Costruttore con 4 parametri
     */
    public Biglietto(int id, Utente utente, Pista pista, LocalDate data)
    {
        this.id = id;
        this.utente = utente;
        this.pista = pista;
        this.data = data;
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

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate data)
    {
        this.data = data;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Biglietto biglietto = (Biglietto) o;

        return id == biglietto.id;
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Biglietto {" +
                "id=" + id +
                ", utente=" + utente +
                ", pista=" + pista +
                ", data=" + data +
                '}' + "\n";
    }
}
