package converter;

import dto.UtenteDTO;
import model.Utente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtenteConverter
{
    public UtenteDTO utenteToDto(Utente utente)
    {
        return new UtenteDTO(utente.getId(),
                utente.getNome(),
                utente.getIndirizzo(),
                utente.getLuogo(),
                utente.getRuolo(),
                utente.isCancellato());
    }

    public Utente DTOtoUtente(UtenteDTO utenteDTO)
    {
        return new Utente(utenteDTO.getId(),
                utenteDTO.getNome(),
                utenteDTO.getIndirizzo(),
                utenteDTO.getLuogo(),
                utenteDTO.getRuolo(),
                null,
                utenteDTO.isCancellato());
    }

    public List<UtenteDTO> utenteToDto(Collection<Utente> utenti)
    {
        List<UtenteDTO> response = new ArrayList<>();

        for (Utente utente : utenti)
        {
            response.add(utenteToDto(utente));
        }

        return response;
    }
}
