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
    private Dao_Impianti daoImpianti;
    private ImpiantoConverter impiantoConverter;
    private PistaConverter pistaConverter;

    public ImpiantiService()
    {
        daoImpianti = new Dao_Impianti();
        impiantoConverter = ImpiantoConverter.getInstance();
    }

    public boolean insert(ImpiantoDTO impiantoDTO)
    {
        boolean response = false;
        Impianto impianto = impiantoConverter.toEntity(impiantoDTO);

        if (daoImpianti.save(impianto))
        {
            impiantoDTO.setId(impianto.getId());
            response = true;
        }

        return response;
    }

    public boolean update(ImpiantoDTO impiantoDTO)
    {
        return daoImpianti.save(impiantoConverter.toEntity(impiantoDTO));
    }

    public boolean delete(ImpiantoDTO impiantoDTO)
    {
        return daoImpianti.delete(impiantoConverter.toEntity(impiantoDTO));
    }

    public ImpiantoDTO read(int id)
    {
        Impianto impianto = daoImpianti.findById(id);

        if (impianto == null)
            return null;
        else
            return impiantoConverter.toDTO(impianto);
    }

    public List<ImpiantoDTO> getAll()
    {
        List<Impianto> impianti = daoImpianti.findAll();

        if (impianti == null)
            return null;
        else
            return impiantoConverter.toDTO(impianti);
    }

    public List<PistaDTO> getPiste(ImpiantoDTO impiantoDTO)
    {
        List<Pista> piste = daoImpianti.getPiste(impiantoConverter.toEntity(impiantoDTO));

        if (piste == null)
            return null;
        else
            return pistaConverter.toDTO(piste);
    }
}
