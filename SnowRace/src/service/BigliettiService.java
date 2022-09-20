package service;

import converter.BigliettoConverter;
import dao.Dao_Biglietti;
import dto.BigliettoDTO;
import interfaces.Service;
import model.Biglietto;

import java.util.List;

public class BigliettiService implements Service<BigliettoDTO>
{
    BigliettoConverter bigliettoConverter;
    Dao_Biglietti daoBiglietti;

    public BigliettiService()
    {
        bigliettoConverter = BigliettoConverter.getInstance();
    }

    public BigliettoDTO read(int id)
    {
        daoBiglietti = new Dao_Biglietti();

        Biglietto biglietto = daoBiglietti.findById(id);

        return bigliettoConverter.toDTO(biglietto);
    }

    public boolean insert(BigliettoDTO bigliettoDTO)
    {
        return daoBiglietti.save(bigliettoConverter.toEntity(bigliettoDTO));
    }

    public boolean update(BigliettoDTO bigliettoDTO)
    {
        return daoBiglietti.save(bigliettoConverter.toEntity(bigliettoDTO));
    }

    public boolean delete(int id)
    {
        return daoBiglietti.delete(id);
    }

    @Override
    public List<BigliettoDTO> getAll()
    {
        return bigliettoConverter.toDTO(daoBiglietti.getAll());
    }
}
