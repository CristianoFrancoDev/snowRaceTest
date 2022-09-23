package service;

import converter.ImpiantoConverter;
import converter.PistaConverter;
import dao.Dao_Impianti;
import dto.ImpiantoDTO;
import dto.PistaDTO;
import model.Impianto;
import model.Pista;

import java.util.List;

public class ImpiantiService
{
    private ImpiantoConverter impiantoConverter;
//    private PistaConverter pistaConverter;

    public ImpiantiService()
    {
        impiantoConverter = ImpiantoConverter.getInstance();
    }

    public boolean insert(ImpiantoDTO impiantoDTO)
    {
        boolean response = false;
        Impianto impianto = impiantoConverter.toEntity(impiantoDTO);

        if (Dao_Impianti.getInstance().save(impianto))
        {
            impiantoDTO.setId(impianto.getId());
            response = true;
        }

        return response;
    }

    public boolean update(ImpiantoDTO impiantoDTO)
    {
        return Dao_Impianti.getInstance().save(impiantoConverter.toEntity(impiantoDTO));
    }

    public boolean delete(ImpiantoDTO impiantoDTO)
    {
        return Dao_Impianti.getInstance().delete(impiantoConverter.toEntity(impiantoDTO));
    }

    public ImpiantoDTO read(int id)
    {
        Impianto impianto = Dao_Impianti.getInstance().findById(id);

        if (impianto == null)
            return null;
        else
            return impiantoConverter.toDTO(impianto);
    }

    public ImpiantoDTO findByTitolo(String titolo)
    {
        Impianto impianto = Dao_Impianti.getInstance().findByName(titolo);

        if (impianto == null)
            return null;
        else
            return impiantoConverter.toDTO(impianto);
    }

    public List<ImpiantoDTO> getAll()
    {
        List<Impianto> impianti = Dao_Impianti.getInstance().findAll();

        if (impianti == null)
            return null;
        else
            return impiantoConverter.toDTO(impianti);
    }

    public List<PistaDTO> getPiste(ImpiantoDTO impiantoDTO)
    {

        List<Pista> piste = Dao_Impianti.getInstance().getPiste(impiantoConverter.toEntity(impiantoDTO));

        if (piste == null)
            return null;
        else
            return PistaConverter.getInstance().toDTO(piste);
    }

    public ImpiantoDTO findByName(String name)
    {
        Impianto impianto = Dao_Impianti.getInstance().findByName(name);

        if (impianto == null)
            return null;
        else
            return impiantoConverter.toDTO(impianto);
    }
}
