package service;

import converter.NoleggioConverter;
import converter.UtenteConverter;
import dao.Dao_Noleggi;
import dto.NoleggioDTO;
import interfaces.Service;
import model.Noleggio;
import model.Utente;
import util.VariabiliGlobali;

import java.util.ArrayList;
import java.util.List;

public class NoleggiService implements Service<NoleggioDTO>
{
    NoleggioConverter noleggioConverter = NoleggioConverter.getInstance();

    public NoleggiService()
    {
        this.noleggioConverter = NoleggioConverter.getInstance();
    }

    public List<NoleggioDTO> getAllByUser(){
        Utente utente = UtenteConverter.getInstance().toEntity(UtentiService.getInstance().findByName(VariabiliGlobali.userName));
        List<Noleggio> listNoleggioDao = Dao_Noleggi.getInstance().showAllNoleggiByUser(utente);
        List<NoleggioDTO> noleggioDTOList = noleggioConverter.toDTO(listNoleggioDao);

        return noleggioDTOList;
    }

    @Override
    public NoleggioDTO read(int id)
    {
        return noleggioConverter.toDTO(Dao_Noleggi.getInstance().findById(id));
    }

    @Override
    public boolean insert(NoleggioDTO noleggioDTO)
    {
        return Dao_Noleggi.getInstance().save(noleggioConverter.toEntity(noleggioDTO));
    }

    @Override
    public boolean update(NoleggioDTO noleggioDTO)
    {
        return Dao_Noleggi.getInstance().save(noleggioConverter.toEntity(noleggioDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return Dao_Noleggi.getInstance().delete(id);
    }

    @Override
    public List<NoleggioDTO> getAll()
    {
        return noleggioConverter.toDTO(Dao_Noleggi.getInstance().getAll());
    }
}
