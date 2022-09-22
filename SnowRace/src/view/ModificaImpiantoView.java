package view;

import abstracts.Abstract_View;
import dto.ImpiantoDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

import java.util.List;

public class ModificaImpiantoView extends Abstract_View implements View
{
    private static ModificaImpiantoView instance;
    List<ImpiantoDTO> lista;
    private String oldTitolo, titolo, descrizione, foto, prezzo;

    private ModificaImpiantoView()
    {}

    public static ModificaImpiantoView getInstance()
    {
        if (instance == null)
            instance = new ModificaImpiantoView();

        return instance;
    }

    @Override
    public void showResults(Request request)
    {
        if (request != null)
        {
            System.out.println(request.get("PRINT"));
        }
    }

    @Override
    public void showOption()
    {
        Request request = new Request();
        MainDispatcher.getInstance().callAction("IMPIANTO", "GET_ALL", request);
        lista = (List<ImpiantoDTO>) request.get("LISTA_IMPIANTI");

        System.out.println("--- Lista impianti ---");

        for (ImpiantoDTO impiantoDTO : lista)
        {
            System.out.println("Titolo: " + impiantoDTO.getTitolo());
            System.out.println("Descrizione: " + impiantoDTO.getDescrizione());
            System.out.println("Foto: " + impiantoDTO.getFoto());
            System.out.println("Prezzo: " + impiantoDTO.getPrezzo());
            System.out.println();
        }

        System.out.println("\n--- Modifica impianto ---");

        do
        {
            System.out.println("Titolo dell'impianto da modificare: ");
            oldTitolo = getInput();
        }
        while (oldTitolo == null || oldTitolo.isBlank());

        do
        {
            System.out.println("Nuovo titolo: ");
            titolo = getInput();
        }
        while (titolo == null || titolo.isBlank());

        System.out.println("Nuova descrizione: ");
        descrizione = getInput();

        System.out.println("Nuovo percorso foto: ");
        foto = getInput();

        do
        {
            System.out.println("Nuovo prezzo: ");
            prezzo = getInput();

            if (prezzo != null && !prezzo.isBlank())
            {
                try
                {
                    Double temp = Double.parseDouble(prezzo);

                    if (temp >= 0.0)
                        break;
                }
                catch (Exception ex)
                {}
            }
        }
        while (true);
    }

    @Override
    public void submit()
    {
        ImpiantoDTO dto = null;

        for (ImpiantoDTO impiantoDTO : lista)
        {
            if (impiantoDTO.getTitolo().toUpperCase().equals(oldTitolo.toUpperCase().trim()))
                {
                    dto = impiantoDTO;
                    break;
                }
        }

        dto.setTitolo(titolo.trim());

        if (descrizione == null || descrizione.isBlank())
            dto.setDescrizione(null);
        else
            dto.setDescrizione(descrizione.trim());

        if (foto == null || foto.isBlank())
            dto.setFoto(null);
        else
            dto.setFoto(foto.trim());

        dto.setPrezzo(Double.parseDouble(prezzo));

        Request request = new Request();
        request.put("DATI", dto);

        MainDispatcher.getInstance().callAction("IMPIANTO", "UPDATE", request);
    }
}
