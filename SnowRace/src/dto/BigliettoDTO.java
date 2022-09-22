package dto;

import model.Pista;
import model.Utente;

import java.time.LocalDate;

public class BigliettoDTO
{
    private int id;
    private UtenteDTO utente;
    private PistaDTO pista;
    private LocalDate data;

    public BigliettoDTO(int id, UtenteDTO utente, PistaDTO pista, LocalDate data)
    {
        this.id = id;
        this.utente = utente;
        this.pista = pista;
        this.data = data;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public UtenteDTO getUtente()
    {
        return utente;
    }

    public void setUtente(UtenteDTO utente)
    {
        this.utente = utente;
    }

    public PistaDTO getPista()
    {
        return pista;
    }

    public void setPista(PistaDTO pista)
    {
        this.pista = pista;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate data)
    {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BIGLIETTO : " + " utente= " +   utente.getNome() +
                ", pista=" + pista.getTitolo() +
                ", data=" + data + "\n";
    }
}
