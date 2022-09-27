package it.contrader.converter;

import it.contrader.dto.BigliettoDTO;
import it.contrader.model.Biglietto;
import java.util.ArrayList;
import java.util.List;

//singleton
public class BigliettoConverter implements Converter<Biglietto, BigliettoDTO>
{
    private static BigliettoConverter instance;

    private BigliettoConverter()
    {}

    public static BigliettoConverter getInstance()
    {
        if (instance == null)
            instance = new BigliettoConverter();

        return instance;
    }

    public BigliettoDTO toDTO(Biglietto biglietto)
    {
        UtenteConverter utenteConverter = UtenteConverter.getInstance();
        PistaConverter pistaConverter = PistaConverter.getInstance();

        return new BigliettoDTO(biglietto.getId(),
                utenteConverter.toDTO(biglietto.getUtente()),
                pistaConverter.toDTO(biglietto.getPista()),
                biglietto.getData());
    }

    public Biglietto toEntity(BigliettoDTO bigliettoDTO)
    {
        UtenteConverter utenteConverter = UtenteConverter.getInstance();
        PistaConverter pistaConverter = PistaConverter.getInstance();

        return new Biglietto(bigliettoDTO.getId(),
                utenteConverter.toEntity(bigliettoDTO.getUtente()),
                pistaConverter.toEntity(bigliettoDTO.getPista()),
                bigliettoDTO.getData());
    }

    public List<BigliettoDTO> toDTOList(List<Biglietto> biglietti)
    {
        List<BigliettoDTO> response = new ArrayList<>();

        for (Biglietto biglietto : biglietti)
        {
            response.add(toDTO(biglietto));
        }

        return response;
    }
}
