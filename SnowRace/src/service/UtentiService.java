package service;

import converter.UtenteConverter;
import dao.Dao_Utenti;
import dto.UtenteDTO;
import model.Utente;

import java.util.List;

public class UtentiService
{
    private Dao_Utenti daoUtenti;
    private UtenteConverter utenteConverter;

    public UtentiService()
    {
        daoUtenti = new Dao_Utenti();
        utenteConverter = new UtenteConverter();
    }

    public boolean login(String nome, String password)
    {
        Utente utente = daoUtenti.findByNomeAndPassword(nome, password);

        return (utente != null);
    }

    public boolean insert(UtenteDTO utenteDTO)
    {
        Utente utente = utenteConverter.DTOtoUtente(utenteDTO);
        boolean response = false;

        if (daoUtenti.save(utente))
        {
            utenteDTO.setId(utente.getId());
            response = true;
        }

        return response;
    }

    public boolean update(UtenteDTO utenteDTO)
    {
        return daoUtenti.save(utenteConverter.DTOtoUtente(utenteDTO));
    }

    public boolean delete(UtenteDTO utenteDTO)
    {
        return daoUtenti.delete(utenteConverter.DTOtoUtente(utenteDTO));
    }

    public List<UtenteDTO> getAll()
    {
        List<Utente> utenti = daoUtenti.findAll();

        if (utenti == null)
            return null;
        else
            return utenteConverter.utenteToDto(utenti);
    }

    public UtenteDTO read(int id)
    {
        Utente utente = daoUtenti.findById(id);

        if (utente == null)
            return null;
        else
            return utenteConverter.utenteToDto(daoUtenti.findById(id));
    }
}
