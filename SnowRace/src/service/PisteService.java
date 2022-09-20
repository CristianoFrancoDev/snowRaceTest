package service;

import converter.PistaConverter;
import dao.Dao_Piste;
import dto.PistaDTO;
import interfaces.Service;
import model.Pista;

import java.util.List;

public class PisteService implements Service<PistaDTO>
{
    PistaConverter pistaConverter;
    Dao_Piste daoPiste;

    public PisteService()
    {
        pistaConverter = PistaConverter.getInstance();
    }

    public PistaDTO read(int id)
    {
        daoPiste = new Dao_Piste();

        Pista pista = daoPiste.findById(id);

        return pistaConverter.toDTO(pista);
    }

    public boolean insert(PistaDTO pistaDTO)
    {
        return daoPiste.save(pistaConverter.toEntity(pistaDTO));
    }

    public boolean update(PistaDTO pistaDTO)
    {
        return daoPiste.save(pistaConverter.toEntity(pistaDTO));
    }

    public boolean delete(int id)
    {
        return daoPiste.delete(id);
    }

    @Override
    public List<PistaDTO> getAll()
    {
        return pistaConverter.toDTO(daoPiste.getAll());
    }
}
