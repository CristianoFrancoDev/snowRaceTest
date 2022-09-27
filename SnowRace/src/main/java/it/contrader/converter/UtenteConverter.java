package it.contrader.converter;

import it.contrader.dto.UtenteDTO;
import it.contrader.model.Ruolo;
import it.contrader.model.Utente;
import java.util.ArrayList;
import java.util.List;

//singleton
public class UtenteConverter implements Converter<Utente, UtenteDTO>
{
    private static UtenteConverter instance;

    private UtenteConverter()
    {}

    public static UtenteConverter getInstance()
    {
        if (instance == null)
            instance = new UtenteConverter();

        return instance;
    }

    public UtenteDTO toDTO(Utente utente)
    {
        return new UtenteDTO(utente.getId(),
                utente.getNome(),
                utente.getIndirizzo(),
                utente.getLuogo(),
                utente.getRuolo(),
                utente.getPassword(),
                utente.isCancellato());
    }

    @Override
    public Utente toEntity(UtenteDTO utenteDTO)
    {
        return new Utente(utenteDTO.getId(),
                utenteDTO.getNome(),
                utenteDTO.getIndirizzo(),
                utenteDTO.getLuogo(),
                utenteDTO.getRuolo(),
                utenteDTO.getPassword(),
                utenteDTO.isCancellato());
    }

    public List<UtenteDTO> toDTOList(List<Utente> utenti)
    {
        List<UtenteDTO> response = new ArrayList<>();

        for (Utente utente : utenti)
        {
            response.add(toDTO(utente));
        }

        return response;
    }
}
