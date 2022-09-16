package converter;

import dto.PistaDTO;
import model.Pista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PistaConverter
{
    public PistaDTO pistaToDto(Pista pista)
    {
        ImpiantoConverter impiantoConverter = new ImpiantoConverter();

        return  new PistaDTO(pista.getId(),
                pista.getTitolo(),
                impiantoConverter.impiantoToDto(pista.getImpianto()));
    }

    public Pista DTOtoPista(PistaDTO pistaDTO)
    {
        ImpiantoConverter impiantoConverter = new ImpiantoConverter();

        return new Pista(pistaDTO.getId(),
                pistaDTO.getTitolo(),
                impiantoConverter.DTOtoImpianto(pistaDTO.getImpianto()));
    }

    public List<PistaDTO> pistaToDto(Collection<Pista> piste)
    {
        List<PistaDTO> response = new ArrayList<>();

        for (Pista pista : piste)
        {
            response.add(pistaToDto(pista));
        }

        return response;
    }
}
