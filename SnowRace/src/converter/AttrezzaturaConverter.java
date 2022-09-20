package converter;

import dto.AttrezzaturaDTO;
import interfaces.Converter;
import model.Attrezzatura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttrezzaturaConverter implements Converter<Attrezzatura, AttrezzaturaDTO>
{
    private static AttrezzaturaConverter instance;

    private AttrezzaturaConverter()
    {}

    public static AttrezzaturaConverter getInstance()
    {
        if (instance == null)
            instance = new AttrezzaturaConverter();

        return instance;
    }

    public AttrezzaturaDTO toDTO(Attrezzatura attrezzatura)
    {
        return new AttrezzaturaDTO(attrezzatura.getId(), attrezzatura.getArticolo());
    }

    public Attrezzatura toEntity(AttrezzaturaDTO attrezzaturaDTO)
    {
        return new Attrezzatura(attrezzaturaDTO.getId(), attrezzaturaDTO.getArticolo());
    }

    public List<AttrezzaturaDTO> toDTO(Collection<Attrezzatura> attrezzature)
    {
        List<AttrezzaturaDTO> response = new ArrayList<>();

        for (Attrezzatura attrezzatura : attrezzature)
        {
            response.add(toDTO(attrezzatura));
        }

        return response;
    }
}
