package it.contrader.service;

import it.contrader.converter.PistaConverter;
import it.contrader.dao.Dao_Piste;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Pista;

import java.util.List;

//classe singleton
public class PisteService extends AbstractService<Pista, PistaDTO>
{
    private static PisteService instance;
    private Dao_Piste daoPiste;

    private PisteService()
    {
        dao = Dao_Piste.getInstance();
        converter = PistaConverter.getInstance();
    }

    public static PisteService getInstance()
    {
        if (instance == null)
            instance = new PisteService();

        return instance;
    }

    @Override
    public PistaDTO read(int id)
    {
        return converter.toDTO(dao.read(id));
    }

    @Override
    public boolean insert(PistaDTO pistaDTO)
    {
        return daoPiste.insert(converter.toEntity(pistaDTO));
    }

    @Override
    public boolean update(PistaDTO pistaDTO)
    {
        return daoPiste.update(converter.toEntity(pistaDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return Dao_Piste.getInstance().delete(id);
    }

    public PistaDTO findByTitolo (String titolo)
    {
        Pista pista = daoPiste.findPista(titolo);

        if (titolo == null)
            return null;
        else
            return PistaConverter.getInstance().toDTO(pista);
    }

    @Override
    public List<PistaDTO> getAll()
    {
        return converter.toDTOList(daoPiste.getAll());
    }
}
