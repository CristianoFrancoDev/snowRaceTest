package it.contrader.dto;

public class AttrezzaturaDTO
{
    @Override
    public String toString() {
        return "Attrezzatura : \n" +
                " nome articolo ='" + articolo + '\'' ;
    }

    private int id;
    private String articolo;

    public AttrezzaturaDTO(int id, String articolo)
    {
        this.id = id;
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

    public String getArticolo()
    {
        return articolo;
    }

    public void setArticolo(String articolo)
    {
        this.articolo = articolo;
    }
}
