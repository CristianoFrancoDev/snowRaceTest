package it.contrader.dto;

import it.contrader.model.Ruolo;

public class UtenteDTO
{
    private int id;
    private String nome;
    private String indirizzo;
    private String luogo;
    private Ruolo ruolo;
    private String password;
    private boolean cancellato;

    public UtenteDTO(int id, String nome, String indirizzo, String luogo, Ruolo ruolo, String password, boolean cancellato)
    {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.ruolo = ruolo;
        this.password = password;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isCancellato()
    {
        return cancellato;
    }

    public void setCancellato(boolean cancellato)
    {
        this.cancellato = cancellato;
    }

    public String toString() {
        return "Utente : \n " +
                "nome='" + nome + '\n' +
                " indirizzo='" + indirizzo + '\n' +
                " luogo='" + luogo + '\n' +
                " ruolo=" + ruolo + '\n' +
                " password='" + password + '\n' +
                " cancellato=" + cancellato
                ;
    }
}
