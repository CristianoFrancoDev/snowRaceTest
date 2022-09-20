package converter;

import dto.UtenteDTO;
import interfaces.Converter;
import model.Utente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                null,
                utenteDTO.isCancellato());
    }

    public List<UtenteDTO> toDTO(Collection<Utente> utenti)
    {
        List<UtenteDTO> response = new ArrayList<>();

        for (Utente utente : utenti)
        {
            response.add(toDTO(utente));
        }

        return response;
    }
}
