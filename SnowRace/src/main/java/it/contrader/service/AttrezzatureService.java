package it.contrader.service;

import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.dao.Dao_Attrezzature;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.model.Attrezzatura;

import java.util.List;

//singleton
public class AttrezzatureService extends AbstractService<Attrezzatura, AttrezzaturaDTO>
{
    private static AttrezzatureService instance;
    private AttrezzaturaConverter attrezzaturaConverter;

    private AttrezzatureService()
    {
        dao = Dao_Attrezzature.getInstance();
        converter = AttrezzaturaConverter.getInstance();
    }

    public static AttrezzatureService getInstance()
    {
        if (instance == null)
            instance = new AttrezzatureService();

        return instance;
    }

    @Override
    public boolean insert(AttrezzaturaDTO attrezzaturaDTO)
    {
        Attrezzatura attrezzatura = converter.toEntity(attrezzaturaDTO);
        boolean response = false;

        if (dao.insert(attrezzatura))
        {
            attrezzaturaDTO.setId(attrezzaturaDTO.getId());
            response = true;
        }

        return response;
    }

    @Override
    public boolean update(AttrezzaturaDTO attrezzaturaDTO)
    {
        Attrezzatura attrezzatura = converter.toEntity(attrezzaturaDTO);

        return dao.update(attrezzatura);
    }

    @Override
    public boolean delete(int id)
    {
        return dao.delete(id);
    }

    @Override
    public AttrezzaturaDTO read(int id)
    {
        return converter.toDTO(dao.read(id));
    }

    @Override
    public List<AttrezzaturaDTO> getAll()
    {
        return converter.toDTOList(dao.getAll());
    }
}
