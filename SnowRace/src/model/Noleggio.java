package model;

public class Noleggio
{
    private Attrezzatura attrezzatura;
    private Biglietto biglietto;

    public Noleggio(Attrezzatura attrezzatura, Biglietto biglietto)
    {
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
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
}
