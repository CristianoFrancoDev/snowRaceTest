package classi;

public class Utenti {
    // variabili di istanza
    private String nome;
    private String indirizzo;
    private String luogo;
    private Ruoli.tipo ruolo;
    private String password;
    private  boolean cancellato;
    // costruttore
    public Utenti (String nome, String indirizzo, String luogo, Ruoli.tipo ruolo, String password, boolean cancellato){
        this.nome= nome ;
        this.indirizzo= indirizzo ;
        this.luogo= luogo ;
        this.password= password ;
        this.ruolo= ruolo ;
        this.cancellato= cancellato ;
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
