package model;

import java.time.LocalDate;

public class Biglietto
{
    private int id;
    private Utente utente;
    private Pista pista;
    private LocalDate data;

    public Biglietto(int id, Utente utente, Pista pista, LocalDate data)
    {
        this.id = id;
        this.utente = utente;
        this.pista = pista;
        this.data = data;
    }

    public Biglietto(Utente utente, Pista pista, LocalDate date) {
        this.utente = utente;
        this.pista = pista;
        this.data = date;
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

    public LocalDate getDate() {
        return data;
    }

    public void setDate(LocalDate data) {
        this.data = data;
    }
}
