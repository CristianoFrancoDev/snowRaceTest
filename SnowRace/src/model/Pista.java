package model;

public class Pista
{
    private int id;
    private String titolo;
    private Impianto impianto;

    public Pista(int id, String titolo, Impianto impianto)
    {
        this.id = id;

        this.impianto = impianto;
        this.titolo = titolo;

    }

    public Pista(String titolo, Impianto impianto)
    {
        this.impianto = impianto;
        this.titolo = titolo;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public void setTitolo(String titolo)
    {

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pista pista = (Pista) o;

        return getId() == pista.getId();
    }

    @Override
    public int hashCode()
    {
        return getId();
    }

    @Override
    public String toString() {
        return "Piste{" +
                "id=" + id + 
                " ,titolo=" + titolo +

                ", impianto=" + impianto +
                '}';
    }
}
