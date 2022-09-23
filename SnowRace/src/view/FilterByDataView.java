package view;

import abstracts.Abstract_View;
import dto.BigliettoDTO;
import interfaces.View;
import singleton.MainDispatcher;
import util.Request;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FilterByDataView extends Abstract_View implements View
{
    private static FilterByDataView instance;
    SimpleDateFormat simpleDateFormat;
    DateFormatter dateFormatter;
    private String inf, sup;
    private LocalDate locInf, locSup;

    private FilterByDataView()

    {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public static FilterByDataView getInstance()
    {
        if (instance == null)
            instance = new FilterByDataView();

        return instance;
    }


    @Override
    public void showResults(Request request)
    {
        if (request != null)
            System.out.println((List<BigliettoDTO>)request.get("PRINT_BIGLIETTI"));
    }

    @Override
    public void showOption()
    {
        System.out.println("---- Ricerca biglietti per data ----");

        while (true)
        {
            System.out.println("Inserire la data inferiore nel formato (GG-MM-AAAA): ");
            inf = getInput();

            if (inf != null && !inf.isBlank())
            {
                try
                {
                    locInf = LocalDate.ofInstant(simpleDateFormat.parse(inf.trim()).toInstant(), ZoneId.systemDefault());
                    break;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        while (true)
        {
            System.out.println("Inserire la data superiore nel formato (GG-MM-AAAA): ");
            sup = getInput();

            if (sup != null && !sup.isBlank())
            {
                try
                {
                    locSup = LocalDate.ofInstant(simpleDateFormat.parse(sup.trim()).toInstant(), ZoneId.systemDefault());
                    break;
                }
                catch (Exception ex)
                {}
            }
        }
    }

    @Override
    public void submit()
    {
        Request request = new Request();
        request.put("DATE_FROM", locInf);
        request.put("DATE_TO", locSup);
        MainDispatcher.getInstance().callAction("UTENTE", "FILTER_BY_DATE", request);
    }
}
