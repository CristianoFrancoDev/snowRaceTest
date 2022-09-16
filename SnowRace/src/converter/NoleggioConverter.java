package converter;

import dto.NoleggioDTO;
import model.Noleggio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoleggioConverter
{
    public NoleggioDTO noleggioToDTO(Noleggio noleggio)
    {
        AttrezzaturaConverter attrezzaturaConverter = new AttrezzaturaConverter();
        BigliettoConverter bigliettoConverter = new BigliettoConverter();

        return new NoleggioDTO(noleggio.getId(),
                attrezzaturaConverter.attrezzaturaToDTO(noleggio.getAttrezzatura()),
                bigliettoConverter.bigliettoToDTO(noleggio.getBiglietto()));
    }

    public Noleggio DTOtoNoleggio(NoleggioDTO noleggioDTO)
    {
        AttrezzaturaConverter attrezzaturaConverter = new AttrezzaturaConverter();
        BigliettoConverter bigliettoConverter = new BigliettoConverter();

        return new Noleggio(noleggioDTO.getId(),
                attrezzaturaConverter.DTOtoAttrezzatura(noleggioDTO.getAttrezzatura()),
                bigliettoConverter.DTOtoBiglietto(noleggioDTO.getBiglietto()));
    }

    public List<NoleggioDTO> noleggioToDTO(Collection<Noleggio> noleggi)
    {
        List<NoleggioDTO> response = new ArrayList<>();

        for (Noleggio noleggio : noleggi)
        {
            response.add(noleggioToDTO(noleggio));
        }

        return response;
    }
}
