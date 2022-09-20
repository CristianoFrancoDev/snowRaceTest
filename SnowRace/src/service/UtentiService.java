package service;

import converter.UtenteConverter;
import dao.Dao_Utenti;
import dto.UtenteDTO;
import interfaces.Service;
import model.Utente;
import java.util.List;

public class UtentiService implements Service<UtenteDTO>
{
    private static UtentiService instance;
    private Dao_Utenti daoUtenti;
    private UtenteConverter utenteConverter;

    private UtentiService()
    {
        daoUtenti = new Dao_Utenti();
        utenteConverter = UtenteConverter.getInstance();
    }

    public static UtentiService getInstance()
    {
        if (instance == null)
            instance = new UtentiService();

        return instance;
    }

    public String login(String nome, String password)
    {
        Utente utente = daoUtenti.findByNomeAndPassword(nome, password);

        if (utente == null)
            return null;
        else
            return (utente.getRuolo().name());
    }

    public boolean insert(UtenteDTO utenteDTO)
    {
        Utente utente = utenteConverter.toEntity(utenteDTO);
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
        return daoUtenti.save(utenteConverter.toEntity(utenteDTO));
    }

    public boolean delete(int id)
    {
        return daoUtenti.delete(id);
    }

    public List<UtenteDTO> getAll()
    {
        List<Utente> utenti = daoUtenti.findAll();

        if (utenti == null)
            return null;
        else
            return utenteConverter.toDTO(utenti);
    }

    public UtenteDTO read(int id)
    {
        Utente utente = daoUtenti.findById(id);

        if (utente == null)
            return null;
        else
            return utenteConverter.toDTO(daoUtenti.findById(id));
    }
}
