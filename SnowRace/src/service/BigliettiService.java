package service;

import converter.BigliettoConverter;
import converter.PistaConverter;
import converter.UtenteConverter;
import dao.Dao_Biglietti;
import dto.BigliettoDTO;
import dto.PistaDTO;
import dto.UtenteDTO;
import interfaces.Service;
import model.Biglietto;
import model.Utente;
import util.VariabiliGlobali;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//classe singleton
public class BigliettiService implements Service<BigliettoDTO>
{
    private static BigliettiService instance;
    BigliettoConverter bigliettoConverter;

    private BigliettiService()
    {
        bigliettoConverter = BigliettoConverter.getInstance();
    }

    public static BigliettiService getInstance()
    {
        if (instance == null)
            instance = new BigliettiService();

        return instance;
    }

    public List<BigliettoDTO> findByLoggedUser ()
    {
            ArrayList<BigliettoDTO> listBigliettiDTO ;
            Utente utente = UtenteConverter.getInstance().toEntity(UtentiService.getInstance().findByName(VariabiliGlobali.userName));
            List<Biglietto> listaBiglietti = Dao_Biglietti.getInstance().findByUser(utente);
            listBigliettiDTO = new ArrayList<>(bigliettoConverter.toDTO(listaBiglietti));

            return listBigliettiDTO;
    }

    public BigliettoDTO read(int id)
    {
        Biglietto biglietto = Dao_Biglietti.getInstance().findById(id);

        return bigliettoConverter.toDTO(biglietto);
    }

    public boolean insert(BigliettoDTO bigliettoDTO)
    {
        return Dao_Biglietti.getInstance().save(bigliettoConverter.toEntity(bigliettoDTO));
    }

    public boolean update(BigliettoDTO bigliettoDTO)
    {
        return Dao_Biglietti.getInstance().save(bigliettoConverter.toEntity(bigliettoDTO));
    }

    public boolean delete(int id)
    {
        return Dao_Biglietti.getInstance().delete(id);
    }

    @Override
    public List<BigliettoDTO> getAll()
    {
        return bigliettoConverter.toDTO(Dao_Biglietti.getInstance().getAll());
    }

    public List<BigliettoDTO> filterByPista(PistaDTO pistaDTO, UtenteDTO utenteDTO)
    {
        List<BigliettoDTO> bigliettoDTOList = new ArrayList<>();
        List<Biglietto> bigliettoList = new ArrayList<>();
        bigliettoList = Dao_Biglietti.getInstance().filterByPista(PistaConverter.getInstance().toEntity(pistaDTO),UtenteConverter.getInstance().toEntity(utenteDTO));
        bigliettoDTOList = BigliettoConverter.getInstance().toDTO(bigliettoList);
        return bigliettoDTOList;
    }

    public List<BigliettoDTO> filterByDate(UtenteDTO utenteDTO, LocalDate from, LocalDate to)
    {
        Utente utente = UtenteConverter.getInstance().toEntity(utenteDTO);
        ArrayList<Biglietto> biglietti = Dao_Biglietti.getInstance().filterBigliettiByData(utente, from, to);

        return BigliettoConverter.getInstance().toDTO(biglietti);
    }

    public List<BigliettoDTO> filterByImpianto(String titolo, UtenteDTO utenteDTO)
    {
        List<BigliettoDTO> bigliettoDTOList = new ArrayList<>();
        List<Biglietto> bigliettoList = new ArrayList<>();
        bigliettoList = Dao_Biglietti.getInstance().filterByImpianto(titolo,UtenteConverter.getInstance().toEntity(utenteDTO));
        bigliettoDTOList = BigliettoConverter.getInstance().toDTO(bigliettoList);
        return bigliettoDTOList;
    }
}
