package converter;

import dto.AttrezzaturaDTO;
import model.Attrezzatura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttrezzaturaConverter
{
    public AttrezzaturaDTO attrezzaturaToDTO(Attrezzatura attrezzatura)
    {
        return new AttrezzaturaDTO(attrezzatura.getId(), attrezzatura.getArticolo());
    }

    public Attrezzatura DTOtoAttrezzatura(AttrezzaturaDTO attrezzaturaDTO)
    {
        return new Attrezzatura(attrezzaturaDTO.getId(), attrezzaturaDTO.getArticolo());
    }

    public List<AttrezzaturaDTO> attrezzaturaToDTO(Collection<Attrezzatura> attrezzature)
    {
        List<AttrezzaturaDTO> response = new ArrayList<>();

        for (Attrezzatura attrezzatura : attrezzature)
        {
            response.add(attrezzaturaToDTO(attrezzatura));
        }

        return response;
    }
}
