package test;

import dao.*;
import model.*;
import view.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Impianto impianto = new Impianto(1, "Impianto1", "neve", "foto1", 100);
        Dao_Impianti daoImpianti = new Dao_Impianti();


        Pista pista = new Pista(4);
        Dao_Piste daoPiste = new Dao_Piste();
        System.out.println("Resp: "+daoPiste.delete(pista.getId()));


    }
}
