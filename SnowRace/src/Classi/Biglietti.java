package classi;

import java.time.LocalDate;
import java.util.Date;

public class Biglietti {
    private Utenti utente;
    private Piste pista;
    private LocalDate data;

    public Biglietti(Utenti utente, Piste pista, LocalDate date) {
        this.utente = utente;
        this.pista = pista;
        this.data = date;
    }

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public Piste getPista() {
        return pista;
    }

    public void setPista(Piste pista) {
        this.pista = pista;
    }

    public LocalDate getDate() {
        return data;
    }

    public void setDate(LocalDate data) {
        this.data = data;
    }
}
