package it.contrader.service;

import it.contrader.converter.ImpiantoConverter;
import it.contrader.converter.PistaConverter;
import it.contrader.dao.DAO;
import it.contrader.dao.Dao_Impianti;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Pista;

import java.util.List;

//classe singleton
public class ImpiantiService extends AbstractService<Impianto, ImpiantoDTO>
{
    private static ImpiantiService instance;

    private ImpiantiService()
    {
        dao = Dao_Impianti.getInstance();
        converter = ImpiantoConverter.getInstance();
    }

    public static ImpiantiService getInstance()
    {
        if (instance == null)
            instance = new ImpiantiService();

        return instance;
    }

    @Override
    public boolean insert(ImpiantoDTO impiantoDTO)
    {
        boolean response = false;

        if (dao.insert(converter.toEntity(impiantoDTO)))
        {
            impiantoDTO.setId(impiantoDTO.getId());
            response = true;
        }

        return response;
    }

    @Override
    public boolean update(ImpiantoDTO impiantoDTO)
    {
        return dao.update(converter.toEntity(impiantoDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return dao.delete(id);
    }

    @Override
    public ImpiantoDTO read(int id)
    {
        return converter.toDTO(dao.read(id));
    }

    public ImpiantoDTO findByTitolo(String titolo)
    {
        Impianto impianto = ((Dao_Impianti)dao).findByName(titolo);

        if (impianto == null)
            return null;
        else
            return converter.toDTO(impianto);
    }

    @Override
    public List<ImpiantoDTO> getAll()
    {
        return converter.toDTOList(dao.getAll());
    }

    public List<PistaDTO> getPiste(ImpiantoDTO impiantoDTO)
    {

        List<Pista> piste = ((Dao_Impianti)dao).getPiste(converter.toEntity(impiantoDTO));

        if (piste == null)
            return null;
        else
            return PistaConverter.getInstance().toDTOList(piste);
    }

    public ImpiantoDTO findByName(String name)
    {
        Impianto impianto = ((Dao_Impianti)dao).findByName(name);

        if (impianto == null)
            return null;
        else
            return converter.toDTO(impianto);
    }
}
