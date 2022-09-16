package converter;

import dto.BigliettoDTO;
import model.Biglietto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BigliettoConverter
{
    public BigliettoDTO bigliettoToDTO(Biglietto biglietto)
    {
        UtenteConverter utenteConverter = new UtenteConverter();
        PistaConverter pistaConverter = new PistaConverter();

        return new BigliettoDTO(biglietto.getId(),
                utenteConverter.utenteToDto(biglietto.getUtente()),
                pistaConverter.pistaToDto(biglietto.getPista()),
                biglietto.getData());
    }

    public Biglietto DTOtoBiglietto(BigliettoDTO bigliettoDTO)
    {
        UtenteConverter utenteConverter = new UtenteConverter();
        PistaConverter pistaConverter = new PistaConverter();

        return new Biglietto(bigliettoDTO.getId(),
                utenteConverter.DTOtoUtente(bigliettoDTO.getUtente()),
                pistaConverter.DTOtoPista(bigliettoDTO.getPista()),
                bigliettoDTO.getData());
    }

    public List<BigliettoDTO> bigliettoToDTO(Collection<Biglietto> biglietti)
    {
        List<BigliettoDTO> response = new ArrayList<>();

        for (Biglietto biglietto : biglietti)
        {
            response.add(bigliettoToDTO(biglietto));
        }

        return response;
    }
}
