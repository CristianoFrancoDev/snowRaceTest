package service;

import converter.AttrezzaturaConverter;
import dao.Dao_Attrezzature;
import dto.AttrezzaturaDTO;
import model.Attrezzatura;
import java.util.List;

//singleton
public class AttrezzatureService
{
    private static AttrezzatureService instance;
    private Dao_Attrezzature daoAttrezzatura;
    private AttrezzaturaConverter attrezzaturaConverter;

    private AttrezzatureService()
    {
        daoAttrezzatura = Dao_Attrezzature.getInstance();
        attrezzaturaConverter = AttrezzaturaConverter.getInstance();
    }

    public static AttrezzatureService getInstance()
    {
        if (instance == null)
            instance = new AttrezzatureService();

        return instance;
    }

    public boolean insert(AttrezzaturaDTO attrezzaturaDTO)
    {
        Attrezzatura attrezzatura = attrezzaturaConverter.toEntity(attrezzaturaDTO);
        boolean response = false;

        if (daoAttrezzatura.save(attrezzatura))
        {
            attrezzaturaDTO.setId(attrezzatura.getId());
            response = true;
        }

        return response;
    }

    public boolean update(AttrezzaturaDTO attrezzaturaDTO)
    {
        return daoAttrezzatura.save(attrezzaturaConverter.toEntity(attrezzaturaDTO));
    }

    public boolean delete(AttrezzaturaDTO attrezzaturaDTO)
    {
        return daoAttrezzatura.delete(attrezzaturaConverter.toEntity(attrezzaturaDTO));
    }

    public AttrezzaturaDTO read(int id)
    {
        Attrezzatura attrezzatura = daoAttrezzatura.findById(id);

        if (attrezzatura == null)
            return null;
        else
            return attrezzaturaConverter.toDTO(attrezzatura);
    }

    public List<AttrezzaturaDTO> getAll()
    {
        List<Attrezzatura> attrezzature = daoAttrezzatura.findAll();

        if (attrezzature == null)
            return null;
        else
            return attrezzaturaConverter.toDTO(attrezzature);
    }
}
