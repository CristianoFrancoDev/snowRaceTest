package service;

import converter.BigliettoConverter;
import converter.UtenteConverter;
import dao.Dao_Biglietti;
import dto.BigliettoDTO;
import dto.UtenteDTO;
import interfaces.Service;
import model.Biglietto;
import model.Utente;
import util.VariabiliGlobali;

import java.util.ArrayList;
import java.util.List;

public class BigliettiService implements Service<BigliettoDTO>
{
    BigliettoConverter bigliettoConverter;

    public BigliettiService()
    {
        bigliettoConverter = BigliettoConverter.getInstance();
    }

    public List<BigliettoDTO> findByLoggedUser ()
    {
            Dao_Biglietti daoBiglietti1 =  Dao_Biglietti.getInstance();
            ArrayList<BigliettoDTO> listBigliettiDTO ;
            Utente utente = UtenteConverter.getInstance().toEntity(UtentiService.getInstance().findByName(VariabiliGlobali.userName));
            List<Biglietto> listaBiglietti = daoBiglietti1.findByUser(utente);
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
}
