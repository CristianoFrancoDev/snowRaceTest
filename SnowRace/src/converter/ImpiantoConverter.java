package converter;

import dto.ImpiantoDTO;
import model.Impianto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImpiantoConverter
{
    public ImpiantoDTO impiantoToDto(Impianto impianto)
    {
        return new ImpiantoDTO(impianto.getId(),
                impianto.getTitolo(),
                impianto.getDescrizione(),
                impianto.getFoto(),
                impianto.getPrezzo());
    }

    public Impianto DTOtoImpianto(ImpiantoDTO impiantoDTO)
    {
        return new Impianto(impiantoDTO.getId(),
                impiantoDTO.getTitolo(),
                impiantoDTO.getDescrizione(),
                impiantoDTO.getFoto(),
                impiantoDTO.getPrezzo());
    }

    public List<ImpiantoDTO> impiantoToDto(Collection<Impianto> impianti)
    {
        List<ImpiantoDTO> response = new ArrayList<>();

        for (Impianto impianto : impianti)
        {
            response.add(impiantoToDto(impianto));
        }

        return response;
    }
}
