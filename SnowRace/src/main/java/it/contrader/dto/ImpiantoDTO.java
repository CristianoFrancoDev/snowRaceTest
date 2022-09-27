package it.contrader.dto;

public class ImpiantoDTO
{
    private int id;
    private String titolo;
    private String descrizione;
    private String foto;
    private double prezzo;

    public ImpiantoDTO()
    {
    }

    public ImpiantoDTO(int id, String titolo, String descrizione, String foto, double prezzo)
    {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.foto = foto;
        this.prezzo = prezzo;
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

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public double getPrezzo()
    {
        return prezzo;
    }

    public void setPrezzo(double prezzo)
    {
        this.prezzo = prezzo;
    }

    @Override
    public String toString()
    {
        return "  titolo='" + titolo.toUpperCase() + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", foto='" + foto + '\'' +
                ", prezzo=" + prezzo + "\n";
    }
}
