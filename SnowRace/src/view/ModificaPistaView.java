package view;

import abstracts.Abstract_View;
import controller.PistaController;
import dto.PistaDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

import java.util.List;

public class ModificaPistaView extends Abstract_View implements View
{
    private static ModificaPistaView instance;
    private String titolo;
    private int id;

    private ModificaPistaView()
    {}

    public static ModificaPistaView getInstance()
    {
        if (instance == null)
            instance = new ModificaPistaView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.getString("PRINT") + "\n");
        }
    }

    @Override
    public void showOption()
    {
        System.out.println("--- Modifica di una PISTA ---\n");
        PistaController pistaController = PistaController.getInstance();
        List<PistaDTO> listPist = pistaController.getListaPistaDTO();

        for (int i= 0; i< listPist.size();i++){

            System.out.println(" ID: " + listPist.get(i).getId() + " Titolo: " + listPist.get(i).getTitolo());
        }

        System.out.println(" inserire id PISTA da modificare \n ");
        String a =  getInput();
        id = Integer.parseInt(a);
        System.out.println(" Inserire nuovo titolo di una pista : ");
        titolo = getInput();

    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("ID_PISTA", id);
        request.put("NOME_PISTA", titolo);
        MainDispatcher.getInstance().callAction("PISTA", "MODIFY", request);
    }
}
