package it.contrader.converter;

import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.model.Attrezzatura;
import java.util.ArrayList;
import java.util.List;

//singleton
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

    @Override
    public List<AttrezzaturaDTO> toDTOList(List<Attrezzatura> attrezzature)
    {
        List<AttrezzaturaDTO> response = new ArrayList<>();

        for (Attrezzatura attrezzatura : attrezzature)
        {
            response.add(toDTO(attrezzatura));
        }

        return response;
    }
}
