package converter;

import dto.ImpiantoDTO;
import interfaces.Converter;
import model.Impianto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImpiantoConverter implements Converter<Impianto, ImpiantoDTO>
{
    private static ImpiantoConverter instance;

    private ImpiantoConverter()
    {}

    public static ImpiantoConverter getInstance()
    {
        if (instance == null)
            instance = new ImpiantoConverter();

        return instance;
    }

    public ImpiantoDTO toDTO(Impianto impianto)
    {
        return new ImpiantoDTO(impianto.getId(),
                impianto.getTitolo(),
                impianto.getDescrizione(),
                impianto.getFoto(),
                impianto.getPrezzo());
    }

    public Impianto toEntity(ImpiantoDTO impiantoDTO)
    {
        return new Impianto(impiantoDTO.getId(),
                impiantoDTO.getTitolo(),
                impiantoDTO.getDescrizione(),
                impiantoDTO.getFoto(),
                impiantoDTO.getPrezzo());
    }

    public List<ImpiantoDTO> toDTO(Collection<Impianto> impianti)
    {
        List<ImpiantoDTO> response = new ArrayList<>();

        for (Impianto impianto : impianti)
        {
            response.add(toDTO(impianto));
        }

        return response;
    }
}
