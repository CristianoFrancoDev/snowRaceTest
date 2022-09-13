package classi;

public class Noleggi {
    private Attrezzature attrezzatura;
    private Biglietti biglietti;

    public Noleggi(Attrezzature attrezzatura, Biglietti biglietti) {
        this.attrezzatura = attrezzatura;
        this.biglietti = biglietti;
    }

    public Attrezzature getAttrezzatura() {
        return attrezzatura;
    }

    public void setAttrezzatura(Attrezzature attrezzatura) {
        this.attrezzatura = attrezzatura;
    }

    public Biglietti getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(Biglietti biglietti) {
        this.biglietti = biglietti;
    }
}
