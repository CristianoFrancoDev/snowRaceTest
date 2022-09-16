package model;

public class Noleggio
{
    private int id;
    private Attrezzatura attrezzatura;
    private Biglietto biglietto;

    public Noleggio(int id, Attrezzatura attrezzatura, Biglietto biglietto)
    {
        this.id = id;
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
    }

    public Noleggio(Attrezzatura attrezzatura, Biglietto biglietto)
    {
        this.attrezzatura = attrezzatura;
        this.biglietto = biglietto;
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noleggio noleggio = (Noleggio) o;

        return getId() == noleggio.getId();
    }

    @Override
    public int hashCode()
    {
        return getId();
    }
}
