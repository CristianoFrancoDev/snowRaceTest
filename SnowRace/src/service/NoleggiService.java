package service;

import converter.NoleggioConverter;
import dao.Dao_Noleggi;
import dto.NoleggioDTO;
import interfaces.Service;

import java.util.List;

public class NoleggiService implements Service<NoleggioDTO>
{
    Dao_Noleggi daoNoleggi;
    NoleggioConverter noleggioConverter = NoleggioConverter.getInstance();

    public NoleggiService()
    {
        this.daoNoleggi = new Dao_Noleggi();
        this.noleggioConverter = NoleggioConverter.getInstance();
    }

    @Override
    public NoleggioDTO read(int id)
    {
        return noleggioConverter.toDTO(daoNoleggi.findById(id));
    }

    @Override
    public boolean insert(NoleggioDTO noleggioDTO)
    {
        return daoNoleggi.save(noleggioConverter.toEntity(noleggioDTO));
    }

    @Override
    public boolean update(NoleggioDTO noleggioDTO)
    {
        return daoNoleggi.save(noleggioConverter.toEntity(noleggioDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return daoNoleggi.delete(id);
    }

    @Override
    public List<NoleggioDTO> getAll()
    {
        return noleggioConverter.toDTO(daoNoleggi.getAll());
    }
}
