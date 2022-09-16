package dto;

import model.Attrezzatura;
import model.Biglietto;

public class NoleggioDTO
{
    private int id;
    private AttrezzaturaDTO attrezzatura;
    private BigliettoDTO biglietto;

    public NoleggioDTO(int id, AttrezzaturaDTO attrezzatura, BigliettoDTO biglietto)
    {
        this.id = id;
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

    public AttrezzaturaDTO getAttrezzatura()
    {
        return attrezzatura;
    }

    public void setAttrezzatura(AttrezzaturaDTO attrezzatura)
    {
        this.attrezzatura = attrezzatura;
    }

    public BigliettoDTO getBiglietto()
    {
        return biglietto;
    }

    public void setBiglietto(BigliettoDTO biglietto)
    {
        this.biglietto = biglietto;
    }
}
