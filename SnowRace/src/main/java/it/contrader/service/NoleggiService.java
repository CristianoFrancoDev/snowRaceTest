package it.contrader.service;

import it.contrader.converter.NoleggioConverter;
import it.contrader.converter.UtenteConverter;
import it.contrader.dao.Dao_Noleggi;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Attrezzatura;
import it.contrader.model.Noleggio;
import it.contrader.model.Utente;
import it.contrader.utils.VariabiliGlobali;

import java.util.List;

//classe singleton
public class NoleggiService extends AbstractService<Noleggio, NoleggioDTO>
{
    private static NoleggiService instance;

    private NoleggiService()
    {
        converter = NoleggioConverter.getInstance();
        dao = Dao_Noleggi.getInstance();
    }

    public static NoleggiService getInstance()
    {
        if (instance == null)
            instance = new NoleggiService();

        return instance;
    }

    public List<NoleggioDTO> getAllByUser()
    {
        List<NoleggioDTO> response;

        Utente utente = UtenteConverter.getInstance().toEntity(VariabiliGlobali.utenteLogged);
        List<Noleggio> listNoleggio = ((Dao_Noleggi)dao).getByUtente(utente);

        response = converter.toDTOList(listNoleggio);

        return response;
    }

    @Override
    public NoleggioDTO read(int id)
    {
        return converter.toDTO(dao.read(id));
    }

    @Override
    public boolean insert(NoleggioDTO noleggioDTO)
    {
        return dao.insert(converter.toEntity(noleggioDTO));
    }

    @Override
    public boolean update(NoleggioDTO noleggioDTO)
    {
        return dao.update(converter.toEntity(noleggioDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return dao.delete(id);
    }

    @Override
    public List<NoleggioDTO> getAll()
    {
        return converter.toDTOList(dao.getAll());
    }
}
