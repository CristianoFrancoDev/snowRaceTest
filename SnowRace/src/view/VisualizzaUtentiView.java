package view;

import abstracts.Abstract_View;
import dto.UtenteDTO;
import interfaces.View;
import util.Request;
import java.util.List;

public class VisualizzaUtentiView extends Abstract_View implements View
{
    private static VisualizzaUtentiView instance;

    private VisualizzaUtentiView()
    {}

    public static VisualizzaUtentiView getInstance()
    {
        if (instance == null)
            instance = new VisualizzaUtentiView();
        return  instance;
    }

    @Override
    public void showResults(Request request)
    {
        List<UtenteDTO> lista = (List<UtenteDTO>)request.get("LISTA_UTENTI");

        System.out.println("-----------LISTA UTENTI-------");

        for (UtenteDTO utenteDTO : lista)
        {
            System.out.println("Nome: " + utenteDTO.getNome());
            System.out.println("indirizzo: " + utenteDTO.getIndirizzo());
            System.out.println("luogo: " + utenteDTO.getLuogo());
            System.out.println("ruolo: " + utenteDTO.getRuolo());
            System.out.println("------------------ \n");
        }
    }

    @Override
    public void showOption()
    {

    }

    @Override
    public void submit()
    {

    }
}
