package dto;

import model.Impianto;

public class PistaDTO
{
    private int id;
    private String titolo;
    private ImpiantoDTO impianto;

    public PistaDTO(int id, String titolo, ImpiantoDTO impianto)
    {
        this.id = id;
        this.titolo = titolo;
        this.impianto = impianto;
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
        this.titolo = titolo;
    }

    public ImpiantoDTO getImpianto()
    {
        return impianto;
    }

    public void setImpianto(ImpiantoDTO impianto)
    {
        this.impianto = impianto;
    }

    @Override
    public String toString() {
        return "Pista: "  + titolo + "\t" +
                "impianto =" + impianto.toString();
    }
}
