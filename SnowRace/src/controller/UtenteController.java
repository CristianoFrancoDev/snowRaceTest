package controller;

import dao.Dao_Utenti;
import dto.UtenteDTO;
import model.Utente;
import service.UtentiService;

public class UtenteController
{
    private UtentiService utentiService;

    public UtenteController()
    {
        utentiService = new UtentiService();
    }

    //controllo esistenza utente
    public boolean userExists(String nome, String passwordDecrypted)
    {
        return utentiService.login(nome, passwordDecrypted);
    }

    public boolean createUtente(UtenteDTO utenteDTO)
    {
        return utentiService.insert(utenteDTO);
    }

    public boolean updateUtente(UtenteDTO utenteDTO)
    {
        return utentiService.update(utenteDTO);
    }

    public boolean deleteUtente(UtenteDTO utenteDTO)
    {
        return utentiService.delete(utenteDTO);
    }

    public boolean checkUserIsActive(UtenteDTO utenteDTO)
    {
        return !utenteDTO.isCancellato();
    }
}
