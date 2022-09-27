package it.contrader.service;

import it.contrader.converter.BigliettoConverter;
import it.contrader.converter.PistaConverter;
import it.contrader.converter.UtenteConverter;
import it.contrader.dao.Dao_Biglietti;
import it.contrader.dto.BigliettoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.UtenteDTO;
import it.contrader.model.Biglietto;
import it.contrader.model.Utente;
import it.contrader.servlets.LoginServlet;
import it.contrader.utils.VariabiliGlobali;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//classe singleton
public class BigliettiService extends AbstractService<Biglietto, BigliettoDTO>
{
    private static BigliettiService instance;

    private BigliettiService()
    {
        dao = Dao_Biglietti.getInstance();
        converter = BigliettoConverter.getInstance();
    }

    public static BigliettiService getInstance()
    {
        if (instance == null)
            instance = new BigliettiService();

        return instance;
    }

    public List<BigliettoDTO> findByLoggedUser()
    {
        ArrayList<BigliettoDTO> response = null;

        Utente utente = UtenteConverter.getInstance().toEntity(VariabiliGlobali.utenteLogged);

        if (utente != null)
        {
            List<Biglietto> listBiglietti = ((Dao_Biglietti)dao).findByUser(utente);

            if (listBiglietti != null)
                response = new ArrayList<>(converter.toDTOList(listBiglietti));
        }

        return response;
    }

    @Override
    public BigliettoDTO read(int id)
    {
        return converter.toDTO(dao.read(id));
    }

    @Override
    public boolean insert(BigliettoDTO bigliettoDTO)
    {

        return dao.insert(converter.toEntity(bigliettoDTO));
    }

    @Override
    public boolean update(BigliettoDTO bigliettoDTO)
    {
        return Dao_Biglietti.getInstance().update(converter.toEntity(bigliettoDTO));
    }

    @Override
    public boolean delete(int id)
    {
        return Dao_Biglietti.getInstance().delete(id);
    }

    @Override
    public List<BigliettoDTO> getAll()
    {
        return converter.toDTOList(dao.getAll());
    }

    public List<BigliettoDTO> filterByPista(PistaDTO pistaDTO, UtenteDTO utenteDTO)
    {
        List<BigliettoDTO> bigliettoDTOList = new ArrayList<>();
        List<Biglietto> bigliettoList = new ArrayList<>();
        bigliettoList = Dao_Biglietti.getInstance().filterByPista(PistaConverter.getInstance().toEntity(pistaDTO),UtenteConverter.getInstance().toEntity(utenteDTO));
        bigliettoDTOList = BigliettoConverter.getInstance().toDTOList(bigliettoList);

        return bigliettoDTOList;
    }

    public List<BigliettoDTO> filterByDate(UtenteDTO utenteDTO, LocalDate from, LocalDate to)
    {
        Utente utente = UtenteConverter.getInstance().toEntity(utenteDTO);
        ArrayList<Biglietto> biglietti = Dao_Biglietti.getInstance().filterBigliettiByData(utente, from, to);

        return BigliettoConverter.getInstance().toDTOList(biglietti);
    }

    public List<BigliettoDTO> filterByImpianto(String titolo, UtenteDTO utenteDTO)
    {
        List<BigliettoDTO> bigliettoDTOList = new ArrayList<>();
        List<Biglietto> bigliettoList = new ArrayList<>();
        bigliettoList = Dao_Biglietti.getInstance().filterByImpianto(titolo,UtenteConverter.getInstance().toEntity(utenteDTO));
        bigliettoDTOList = BigliettoConverter.getInstance().toDTOList(bigliettoList);

        return bigliettoDTOList;
    }
}
