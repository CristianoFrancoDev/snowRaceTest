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

    public int getId(){
        return id;
    }
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    @Override
    public String toString() {
        return "Piste{" +
                "titolo=" + titolo +
                ", impianto=" + impianto +
                '}';
    }
}
