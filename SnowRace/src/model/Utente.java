package model;

public class Utente
{
    //variabili di istanza
    private String nome;
    private String indirizzo;
    private String luogo;
    private Ruolo ruolo;
    private String password;
    private boolean cancellato;

    //Costruttore
    public Utente(String nome, String indirizzo, String luogo, Ruolo ruolo, String password, boolean cancellato)
    {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.ruolo = ruolo;
        this.password = password;
        this.cancellato = cancellato;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getLuogo() {
        return luogo;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCancellato() {
        return cancellato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCancellato(boolean cancellato) {
        this.cancellato = cancellato;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", luogo='" + luogo + '\'' +
                ", ruolo=" + ruolo +
                ", password='" + password + '\'' +
                ", cancellato=" + cancellato +
                '}';
    }

}