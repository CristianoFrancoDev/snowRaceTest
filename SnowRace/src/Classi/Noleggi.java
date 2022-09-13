package classi;

public class Noleggi {
    private Attrezzature attrezzatura;
    private Biglietti biglietto;

    public Noleggi(Attrezzature attrezzatura, Biglietti biglietto) {
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
    }

    public Attrezzature getAttrezzatura() {
        return attrezzatura;
    }

    public void setAttrezzatura(Attrezzature attrezzatura) {
        this.attrezzatura = attrezzatura;
    }

    public Biglietti getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietti biglietto) {
        this.biglietto = biglietto;
    }

    @Override
    public String toString() {
        return "Noleggi{" +
                "attrezzatura=" + attrezzatura +
                ", biglietto=" + biglietto +
                '}';
    }
}
