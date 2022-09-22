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
    private UtenteConverter utenteConverter;

    private UtentiService()
    {
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
        Utente utente = Dao_Utenti.getInstance().findByNomeAndPassword(nome, password);

        if (utente == null)
            return null;
        else
            return (utente.getRuolo().name());
    }

    public UtenteDTO findByName (String name)
    {
        Utente utente = Dao_Utenti.getInstance().findUser(name);

        if (utente == null)
            return null;
        else
            return utenteConverter.toDTO(utente);
    }

    public boolean insert(UtenteDTO utenteDTO)
    {
        Utente utente = utenteConverter.toEntity(utenteDTO);
        boolean response = false;

        if (Dao_Utenti.getInstance().save(utente))
        {
            utenteDTO.setId(utente.getId());
            response = true;
        }

        return response;
    }

    public boolean update(UtenteDTO utenteDTO)
    {
        return Dao_Utenti.getInstance().save(utenteConverter.toEntity(utenteDTO));
    }

    public boolean delete(int id)
    {
        return Dao_Utenti.getInstance().delete(id);
    }

    public List<UtenteDTO> getAll()
    {
        List<Utente> utenti = Dao_Utenti.getInstance().findAll();

        if (utenti == null)
            return null;
        else
            return utenteConverter.toDTO(utenti);
    }

    public UtenteDTO read(int id)
    {
        Utente utente = Dao_Utenti.getInstance().findById(id);

        if (utente == null)
            return null;
        else
            return utenteConverter.toDTO(Dao_Utenti.getInstance().findById(id));
    }
}
