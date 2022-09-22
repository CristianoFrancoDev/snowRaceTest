package controller;

import dto.ImpiantoDTO;
import dto.PistaDTO;
import interfaces.Controller;
import service.ImpiantiService;
import service.PisteService;
import singleton.MainDispatcher;
import util.Request;

import java.util.List;

public class PistaController implements Controller
{
    private static PistaController instance;
    private PisteService pisteService;
    private List<PistaDTO> listaPistaDTO;
    private boolean ok;

    private PistaController()
    {
        pisteService = PisteService.getInstance();
    }

    public static PistaController getInstance()
    {
        if (instance == null)
            instance =new PistaController();

        return instance;
    }

    public List<PistaDTO> getListaPistaDTO()
    {
        listaPistaDTO = pisteService.getAll();

        return listaPistaDTO;
    }

    @Override
    public void doControl(Request request)
    {
        if (request != null)
        {
            PistaDTO pisteDTO;

            switch (request.getString("OPERATION"))
            {
                case "CREATE":
                    ImpiantiService impiantiService = new ImpiantiService();
                    ImpiantoDTO impiantoDTO = impiantiService.findByName(request.getString("NOME_IMPIANTO"));
                    System.out.println(impiantoDTO.getId());
                    PistaDTO pistaDTO = new PistaDTO(0, request.getString("NOME_PISTA"),impiantoDTO );
                    System.out.println(pistaDTO.getTitolo());
                    listaPistaDTO = pisteService.getAll();
                    Boolean yesNot2 = false;

                    for (int i=0 ; i< listaPistaDTO.size(); i++){
                        if (listaPistaDTO.get(i).getImpianto().getId() == impiantoDTO.getId() &&
                                listaPistaDTO.get(i).getTitolo().equals(request.getString("NOME_PISTA"))) {

                            yesNot2 = true;
                            System.out.println(yesNot2);

                            break;

                        }

                    }
                    if (yesNot2 == true){
                        System.out.println("NOME GIA ESISTENTE");
                    } else{
                        ok = pisteService.insert(pistaDTO);

                        request = new Request();
                        if (ok)
                            request.put("PRINT", "PISTA creata correttamente.");
                        else
                            request.put("PRINT", "Errore nella creazione della pista");
                        MainDispatcher.getInstance().callAction("PISTA", "PRINT", request);

                    }

                    break;
                case "MODIFY":
                    try{
                        ImpiantiService impiantiService2 = new ImpiantiService();
                        ImpiantoDTO impiantoDTO2 = pisteService.read((Integer) request.get("ID_PISTA")).getImpianto();
                        PistaDTO pistaDTO2 = new PistaDTO((Integer) request.get("ID_PISTA"), request.getString("NOME_PISTA"), impiantoDTO2 );
                        System.out.println(pistaDTO2.getId());

                        //IF nomePISTA esiste nell'IMPIANTO . ->
                        // FILTRARE LE PISTE PER IDIMPIANTO. DAOIMPIANTO.GETPISTE()
                        // CHECK .
                        // dal controller booleano
                        //  se true -> esci, con messaggio "ESISTE GIA QUESTO NOME NELL'IMPIANTO"
                        // se false -> ok

                        boolean yesNot = false;
                        listaPistaDTO = pisteService.getAll();
                        boolean x = false;

                        for (int i=0 ; i< listaPistaDTO.size(); i++){
                            if (listaPistaDTO.get(i).getId() == pistaDTO2.getId())
                            {
                                System.out.println("ID TROVATO ");
                                x = true;

                            }

                            if (x=true){
                                if (listaPistaDTO.get(i).getImpianto().getId() == impiantoDTO2.getId() &&
                                        listaPistaDTO.get(i).getTitolo().equals(request.getString("NOME_PISTA"))) {

                                    yesNot = true;
                                    System.out.println(yesNot);

                                    break;

                                }
                            }
                        }

                        if(yesNot == true)
                        {
                            System.out.println("ESISTE GIA QUESTO NOME NELL'IMPIANTO");
                        } else{

                            ok = pisteService.update(pistaDTO2);

                            request = new Request();

                            if (ok)
                                request.put("PRINT", "PISTA modificata correttamente.");
                            else
                                request.put("PRINT", "Errore nella Modifica della pista");

                            MainDispatcher.getInstance().callAction("PISTA", "PRINT", request);
                        }

                        break;
                        }
                        catch (Exception e)
                        {
                            System.out.println("ERRORE NELLA MODIFICA, id Inesistente!!!!");
                        }

                    break;
                case "DELETE":
                    pisteDTO = pisteService.findByTitolo(request.getString("TITOLO"));
                    ok = pisteService.delete(pisteDTO.getId());

                    if (ok)
                        System.out.println("Pista eliminata correttamente.");
                    else
                        System.out.println("Errore nell'eliminazione della pista!");

                    break;
                case "GET_ALL_PISTE":
                    request= new Request();
                    request.put("LISTA_PISTE", pisteService.getAll());

                    MainDispatcher.getInstance().callAction("PISTA","GET_ALL", request );

                    break;
            }
        }
    }
}
