package it.contrader.converter;

import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Noleggio;

import java.util.ArrayList;
import java.util.List;

//singleton
public class NoleggioConverter implements Converter<Noleggio, NoleggioDTO>
{
    private static NoleggioConverter instance;

    private NoleggioConverter()
    {}

    public static NoleggioConverter getInstance()
    {
        if (instance == null)
            instance = new NoleggioConverter();

        return instance;
    }
    public NoleggioDTO toDTO(Noleggio noleggio)
    {
        AttrezzaturaConverter attrezzaturaConverter = AttrezzaturaConverter.getInstance();
        BigliettoConverter bigliettoConverter = BigliettoConverter.getInstance();

        return new NoleggioDTO(noleggio.getId(),
                attrezzaturaConverter.toDTO(noleggio.getAttrezzatura()),
                bigliettoConverter.toDTO(noleggio.getBiglietto()));
    }

    public Noleggio toEntity(NoleggioDTO noleggioDTO)
    {
        AttrezzaturaConverter attrezzaturaConverter = AttrezzaturaConverter.getInstance();
        BigliettoConverter bigliettoConverter = BigliettoConverter.getInstance();

        return new Noleggio(noleggioDTO.getId(),
                attrezzaturaConverter.toEntity(noleggioDTO.getAttrezzatura()),
                bigliettoConverter.toEntity(noleggioDTO.getBiglietto()));
    }

    public List<NoleggioDTO> toDTOList(List<Noleggio> noleggi)
    {
        List<NoleggioDTO> response = new ArrayList<>();

        for (Noleggio noleggio : noleggi)
        {
            response.add(toDTO(noleggio));
        }

        return response;
    }
}
