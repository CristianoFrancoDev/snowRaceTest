package view;

import abstracts.Abstract_View;
import dto.PistaDTO;
import interfaces.View;
import util.Request;

import java.util.List;

public class VisualizzaPistaView extends Abstract_View implements View
{
    private static VisualizzaPistaView instance;

    private VisualizzaPistaView()
    {}

    public static VisualizzaPistaView getInstance()
    {
        if (instance == null)
            instance = new VisualizzaPistaView();
        return  instance;
    }

    @Override
    public void showResults(Request request)
    {
        List<PistaDTO> lista = (List<PistaDTO>)request.get("LISTA_PISTE");

        System.out.println("-----------LISTA PISTA-------");

        for (PistaDTO pistaDTO : lista)
        {
            System.out.println("Titolo: " + pistaDTO.getTitolo());
            System.out.println("Nome impianto: " + pistaDTO.getImpianto().getTitolo());
            System.out.println("------------------");
            System.out.println();
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
