package dto;

import model.Ruolo;

public class UtenteDTO
{
    private int id;
    private String nome;
    private String indirizzo;
    private String luogo;
    private Ruolo ruolo;
    private boolean cancellato;

    public UtenteDTO(int id, String nome, String indirizzo, String luogo, Ruolo ruolo, boolean cancellato)
    {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.ruolo = ruolo;
        this.cancellato = cancellato;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getIndirizzo()
    {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo)
    {
        this.indirizzo = indirizzo;
    }

    public String getLuogo()
    {
        return luogo;
    }

    public void setLuogo(String luogo)
    {
        this.luogo = luogo;
    }

    public Ruolo getRuolo()
    {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo)
    {
        this.ruolo = ruolo;
    }

    public boolean isCancellato()
    {
        return cancellato;
    }

    public void setCancellato(boolean cancellato)
    {
        this.cancellato = cancellato;
    }
}
