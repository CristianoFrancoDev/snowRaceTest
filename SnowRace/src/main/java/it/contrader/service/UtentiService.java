package it.contrader.service;

import it.contrader.converter.UtenteConverter;
import it.contrader.dao.Dao_Utenti;
import it.contrader.dto.UtenteDTO;
import it.contrader.model.Utente;
import java.util.List;

//classe singleton
public class UtentiService extends AbstractService<Utente, UtenteDTO>
{
    private static UtentiService instance;

    private UtentiService()
    {
        dao = Dao_Utenti.getInstance();
        converter = UtenteConverter.getInstance();
    }

    public static UtentiService getInstance()
    {
        if (instance == null)
            instance = new UtentiService();

        return instance;
    }

    public UtenteDTO login(String nome, String password)
    {
        Utente utente = ((Dao_Utenti)dao).findByNomeAndPassword(nome, password);

        if (utente == null)
            return null;
        else
            return converter.toDTO(utente);
    }

    public UtenteDTO findByName (String name)
    {
        Utente utente = Dao_Utenti.getInstance().findUser(name);

        if (utente == null)
            return null;
        else
            return converter.toDTO(utente);
    }

    @Override
    public boolean insert(UtenteDTO utenteDTO)
    {
        Utente utente = converter.toEntity(utenteDTO);
        boolean response = false;

        if (dao.insert(utente))
        {
            utenteDTO.setId(utente.getId());
            response = true;
        }

        return response;
    }

    @Override
    public boolean update(UtenteDTO utenteDTO)
    {
        return dao.update(converter.toEntity(utenteDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return dao.delete(id);
    }

    @Override
    public List<UtenteDTO> getAll()
    {
        List<Utente> utenti = dao.getAll();

        if (utenti == null)
            return null;
        else
            return converter.toDTOList(utenti);
    }

    @Override
    public UtenteDTO read(int id)
    {
        Utente utente = dao.read(id);

        if (utente == null)
            return null;
        else
            return converter.toDTO(utente);
    }
}
