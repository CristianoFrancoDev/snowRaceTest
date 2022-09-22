package converter;

import dto.PistaDTO;
import interfaces.Converter;
import model.Pista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//singleton
public class PistaConverter implements Converter<Pista, PistaDTO>
{
    private static PistaConverter instance;

    private PistaConverter()
    {}

    public static PistaConverter getInstance()
    {
        if (instance == null)
            instance = new PistaConverter();

        return instance;
    }

    public PistaDTO toDTO(Pista pista)
    {
        ImpiantoConverter impiantoConverter = ImpiantoConverter.getInstance();

        return  new PistaDTO(pista.getId(),
                pista.getTitolo(),
                impiantoConverter.toDTO(pista.getImpianto()));
    }

    public Pista toEntity(PistaDTO pistaDTO)
    {
        ImpiantoConverter impiantoConverter = ImpiantoConverter.getInstance();

        return new Pista(pistaDTO.getId(),
                pistaDTO.getTitolo(),
                impiantoConverter.toEntity(pistaDTO.getImpianto()));
    }

    public List<PistaDTO> toDTO(Collection<Pista> piste)
    {
        List<PistaDTO> response = new ArrayList<>();

        for (Pista pista : piste)
        {
            response.add(toDTO(pista));
        }

        return response;
    }
}
