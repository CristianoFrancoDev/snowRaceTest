package controller;

import dto.ImpiantoDTO;
import service.ImpiantiService;

import java.util.List;

public class ImpiantoController
{
    private ImpiantiService impiantiService;

    public ImpiantoController()
    {
        impiantiService = new ImpiantiService();
    }

    public List<ImpiantoDTO> getAllImpianti()
    {
        List<ImpiantoDTO> response = null;



        return response;
    }
}
