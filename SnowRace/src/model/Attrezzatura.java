package model;

public class Attrezzatura
{
    private int id;
    private String articolo;

    public Attrezzatura(int id, String articolo)
    {
        this.id = id;
        this.articolo = articolo;
    }

    public Attrezzatura(String articolo) {
        this.articolo = articolo;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getArticolo() {
        return articolo;
    }

    public void setArticolo(String articolo) {
        this.articolo = articolo;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attrezzatura that = (Attrezzatura) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode()
    {
        return getId();
    }
}
