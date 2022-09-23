package service;

import converter.PistaConverter;
import dao.Dao_Piste;
import dto.PistaDTO;
import interfaces.Service;
import model.Pista;

import java.util.List;

//classe singleton
public class PisteService implements Service<PistaDTO>
{
    private static PisteService instance;
    private PistaConverter pistaConverter;

    private PisteService()
    {
        pistaConverter = PistaConverter.getInstance();
    }

    public static PisteService getInstance()
    {
        if (instance == null)
            instance = new PisteService();

        return instance;
    }

    public PistaDTO read(int id)
    {
        Pista pista = Dao_Piste.getInstance().findById(id);

        return pistaConverter.toDTO(pista);
    }

    public boolean insert(PistaDTO pistaDTO)
    {
        return Dao_Piste.getInstance().save(pistaConverter.toEntity(pistaDTO));
    }

    public boolean update(PistaDTO pistaDTO)
    {
        return Dao_Piste.getInstance().save(pistaConverter.toEntity(pistaDTO));
    }

    public boolean delete(int id)
    {
        return Dao_Piste.getInstance().delete(id);
    }

    public PistaDTO findByTitolo (String titolo)
    {
        Pista pista =Dao_Piste.getInstance().findPista(titolo);

        if (titolo == null)
            return null;
        else
            return pistaConverter.toDTO(pista);
    }

    @Override
    public List<PistaDTO> getAll()
    {
        return pistaConverter.toDTO(Dao_Piste.getInstance().getAll());
    }
}
