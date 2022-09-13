package classi;

import java.util.Date;

public class Biglietti {
    private Utenti utente;
    private Piste pista;
    private Date data;

    public Biglietti(Utenti utente, Piste pista, Date data) {
        this.utente = utente;
        this.pista = pista;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "utente=" + utente +
                ", pista=" + pista +
                ", data=" + data +
                '}';
    }
}
