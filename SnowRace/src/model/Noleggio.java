package model;

/**
 * Classe Noleggio: definisce un noleggio di un'attrezzatura
 */
public class Noleggio
{
    /**
     * Variabili di istanza
     */
    private int id;
    private Attrezzatura attrezzatura;
    private Biglietto biglietto;

    /**
     * Costruttore vuoto
     */
    public Noleggio() {
    }

    /**
     * Costruttore con parametro id
     */
    public Noleggio(int id) {
        this.id = id;
    }

    /**
     * Costruttore con 2 parametri
     */
    public Noleggio(Attrezzatura attrezzatura, Biglietto biglietto)
    {
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
    }

    /**
     * Costruttore con 3 parametri
     */
    public Noleggio(int id, Attrezzatura attrezzatura, Biglietto biglietto)
    {
        this.id = id;
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
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

    public Attrezzatura getAttrezzatura() {
        return attrezzatura;
    }

    public void setAttrezzatura(Attrezzatura attrezzatura) {
        this.attrezzatura = attrezzatura;
    }

    public Biglietto getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto) {
        this.biglietto = biglietto;
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noleggio noleggio = (Noleggio) o;

        return getId() == noleggio.getId();
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Noleggio{" +
                "id=" + id +
                ", attrezzatura=" + attrezzatura +
                ", biglietto=" + biglietto +
                '}';
    }
}
