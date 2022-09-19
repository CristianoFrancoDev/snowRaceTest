package model;

/**
 * Classe Utente: definisce l'utente, ovvero colui che potrà accedere alla piattaforma SnowRace
 */
public class Utente
{
    /**
     * Variabili di istanza
     */
    private int id;
    private String nome;
    private String indirizzo;
    private String luogo;
    private Ruolo ruolo;
    private String password;
    private boolean cancellato;

    /**
     * Costruttore vuoto
     */
    public Utente(){

    }

    /**
     * Costruttore con parametro id
     */
    public Utente(int id) {
        this.id = id;
    }

    /**
     * Costruttore con 6 parametri
     */
    public Utente(String nome, String indirizzo, String luogo, Ruolo ruolo, String password, boolean cancellato)
    {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.ruolo = ruolo;
        this.password = password;
        this.cancellato = cancellato;
    }

    /**
     * Costruttore con 7 parametri
     */
    public Utente(int id, String nome, String indirizzo, String luogo, Ruolo ruolo, String password, boolean cancellato)
    {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.ruolo = ruolo;
        this.password = password;
        this.cancellato = cancellato;
    }

    /**
     * Metodi getter e setter
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCancellato() {
        return cancellato;
    }

    public void setCancellato(boolean cancellato) {
        this.cancellato = cancellato;
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        return getId() == utente.getId();
    }

    /**
     * Metodo toString
     */
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
