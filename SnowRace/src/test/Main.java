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
        Scanner scanner = new Scanner(System.in);
        Impianto impianto = new Impianto(1, "Impianto1", "neve", "foto1", 100);
        Pista pista = new Pista(4,"PistaBianca", impianto);
        Utente utente = new Utente(8,"igor", "via2", "bn", Ruolo.USER, "password123", false);

        LocalDate localDate1 = LocalDate.of(2022, 12, 10);
        LocalDate localDate2 = LocalDate.of(2023, 01, 23);

        //Biglietto biglietto = new Biglietto(utente, pista, localDate);
        Dao_Biglietti daoBiglietti = new Dao_Biglietti();
        System.out.println(daoBiglietti.filterBigliettiByData(utente, localDate1,localDate2));

    }
}
