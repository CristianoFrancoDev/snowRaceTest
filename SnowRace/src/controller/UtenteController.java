package controller;

import dao.Dao_Utenti;
import dto.UtenteDTO;
import interfaces.Controller;
import model.Utente;
import service.UtentiService;
import util.Request;

public class UtenteController implements Controller
{
    private UtentiService utentiService;

    public UtenteController()
    {
        utentiService = UtentiService.getInstance();
    }

    //controllo esistenza utente
    public boolean userExists(String nome, String passwordDecrypted)
    {
        return !(utentiService.login(nome, passwordDecrypted) == null);
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
        return utentiService.delete(utenteDTO.getId());
    }

    public boolean checkUserIsActive(UtenteDTO utenteDTO)
    {
        return !utenteDTO.isCancellato();
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            //
        }
    }
}
