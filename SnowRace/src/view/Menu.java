package view;

import util.CryptoHelper;
import model.Ruolo;
import model.Utente;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu
{
    Scanner scanner = new Scanner(System.in);

    public String execLogin()
    {
        String nome = null;

        do
        {
            System.out.println("--- Login: ---\n\n");
            System.out.println("Nome: ");

            nome = scanner.nextLine();
        }
        while (nome == null || nome.isBlank());

        return nome.trim();
    }

    public boolean askRegisterUser()
    {
        String response;

        System.out.println("Utente inesistente. Vuoi registrarti? (Y/N)");

        do
        {
            response = scanner.nextLine();

            if (response != null)
            {
                if (!response.isBlank())
                {
                    if (response.trim().toUpperCase().equals("Y") || response.trim().toUpperCase().equals("N"))
                    {
                        response = response.trim().toUpperCase();
                        break;
                    }
                }
            }
        }
        while (true);

        if (response.equals("N"))
            System.out.println("Ok, grazie comunque!");

        return (response.equals("Y"));
    }

    public Utente registerUser()
    {
        String nome, indirizzo, luogo, password;
        Utente utente;

        System.out.println("--- Registrazione nuovo utente ---\n");

        //richiesta nome
        do
        {
            System.out.println("Inserire nome: ");
            nome = scanner.nextLine();

            if (nome != null)
            {
                if (!nome.isBlank())
                {
                    nome = nome.trim().toLowerCase();
                    break;
                }
                else
                    System.out.println("Nome incorretto!\n");
            }
            else
                System.out.println("Nome incorretto!\n");
        }
        while (true);

        //richiesta indirizzo
        System.out.println("Inserire indirizzo: ");

        indirizzo = scanner.nextLine();

        if (indirizzo == null || indirizzo.isBlank())
            indirizzo = null;
        else
            indirizzo = indirizzo.trim().toLowerCase();

        //richiesta luogo di nascita
        System.out.println("Inserire luogo di nascita: ");

        luogo = scanner.nextLine();

        if (luogo == null || luogo.isBlank())
            luogo = null;
        else
            luogo = luogo.trim().toLowerCase();

        //richiesta password
        do
        {
            System.out.println("Inserire password: ");
            password = scanner.nextLine();

            if (password != null)
            {
                if (!password.isBlank())
                {
                    if (password.trim().length() >= 8)
                    {
                        password = password.trim();
                        break;
                    }
                    else
                        System.out.println("La password deve contenere almeno 8 caratteri!");
                }
                else
                    System.out.println("La password non puo' essere vuota!\n");
            }
            else
                System.out.println("La password non puo' essere vuota!\n");
        }
        while (true);

        password = CryptoHelper.encode(password);

        utente = new Utente(nome, indirizzo, luogo, Ruolo.USER, password, false);

        return utente;
    }

    public String askAmministratore()
    {
        boolean exit1, exit2;
        String tipo = null;
        String scelta;

        do
        {
            exit1 = false;

            System.out.println("--- Opzioni amministratore ---\n\n");
            System.out.println("(I)mpianto\n");
            System.out.println("(U)tente\n");
            System.out.println("(P)ista\n");

            scelta = scanner.nextLine();

            if (scelta != null)
            {
                scelta = scelta.trim().toUpperCase();

                switch (scelta)
                {
                    case "I":
                        tipo = "I";
                        System.out.println("--- Amministratore ==> Impianti\n\n");
                        exit1 = true;
                        break;
                    case "U":
                        tipo = "U";
                        System.out.println("--- Amministratore ==> Utenti\n\n");
                        exit1 = true;
                        break;
                    case "P":
                        tipo = "P";
                        System.out.println("--- Amministratore ==> Piste\n\n");
                        exit1 = true;
                        break;
                }

                if (exit1)
                {
                    do
                    {
                        exit2 = false;

                        System.out.println("(C)reazione\n");
                        System.out.println("(M)odifica\n");
                        System.out.println("(V)isualizzazione\n");
                        System.out.println("(E)liminazione\n\n");

                        scelta = scanner.nextLine();

                        if (scelta != null)
                        {
                            scelta = scelta.trim().toUpperCase();

                            switch (scelta)
                            {
                                case "C":
                                    tipo += "C";
                                    exit2 = true;
                                    break;
                                case "M":
                                    tipo += "M";
                                    exit2 = true;
                                    break;
                                case "V":
                                    tipo += "V";
                                    exit2 = true;
                                    break;
                                case "E":
                                    tipo += "E";
                                    exit2 = true;
                                    break;
                            }
                        }

                    if (exit2) break;
                }
                    while (true);
                }
            }

            if (exit1) break;
        }
        while (true);

        return tipo;
    }

    public String askAdmin()
    {
        boolean exit1, exit2;
        String tipo = null;
        String scelta;

        do
        {
            exit1 = false;

            System.out.println("--- Opzioni admin ---\n\n");
            System.out.println("(A)ccount\n");
            System.out.println("(P)ista\n");

            scelta = scanner.nextLine();

            if (scelta != null)
            {
                scelta = scelta.trim().toUpperCase();

                switch (scelta)
                {
                    case "A":
                        tipo = "A";
                        System.out.println("--- Admin ==> Account admin\n\n");
                        exit1 = true;
                        break;
                    case "P":
                        tipo = "P";
                        System.out.println("--- Admin ==> Piste\n\n");
                        exit1 = true;
                        break;
                }

                if (exit1)
                {
                    do
                    {
                        exit2 = false;

                        System.out.println("(C)reazione\n");
                        System.out.println("(M)odifica\n");
                        System.out.println("(V)isualizzazione\n");
                        System.out.println("(E)liminazione\n\n");

                        scelta = scanner.nextLine();

                        if (scelta != null)
                        {
                            scelta = scelta.trim().toUpperCase();

                            switch (scelta)
                            {
                                case "C":
                                    tipo += "C";
                                    exit2 = true;
                                    break;
                                case "M":
                                    tipo += "M";
                                    exit2 = true;
                                    break;
                                case "V":
                                    tipo += "V";
                                    exit2 = true;
                                    break;
                                case "E":
                                    tipo += "E";
                                    exit2 = true;
                                    break;
                            }
                        }

                        if (exit2) break;
                    }
                    while (true);
                }
            }

            if (exit1) break;
        }
        while (true);

        return tipo;
    }

    public String askUser()
    {
        boolean exit;
        String tipo = null, scelta;

        do
        {
            exit = false;

            System.out.println("--- Opzioni utente ---\n\n");
            System.out.println("(V)isualizza tutti gli impianti\n");
            System.out.println("(A)cquistare un biglietto\n");
            System.out.println("(N)oleggio attrezzatura\n");
            System.out.println("(D)ati personali\n");
            System.out.println("Storico acquisto (B)iglietti\n");
            System.out.println("(S)torico noleggi\n");
            System.out.println("(F)iltra per data\n");
            System.out.println("Filtra per (I)mpianto\n");
            System.out.println("Filtra per (P)ista\n");

            scelta = scanner.nextLine();

            if (scelta != null)
            {
                scelta = scelta.trim().toUpperCase();

                switch (scelta)
                {
                    case "V":
                        exit = true;
                        tipo = "V";
                        break;
                    case "A":
                        exit = true;
                        tipo = "A";
                        break;
                    case "N":
                        exit = true;
                        tipo = "N";
                        break;
                    case "D":
                        exit = true;
                        tipo = "D";
                        break;
                    case "B":
                        exit = true;
                        tipo = "B";
                        break;
                    case "S":
                        exit = true;
                        tipo = "S";
                        break;
                    case "F":
                        exit = true;
                        tipo = "F";
                        break;
                    case "I":
                        exit = true;
                        tipo = "I";
                        break;
                    case "P":
                        exit = true;
                        tipo = "P";
                        break;
                }
            }

            if (exit) break;
        }
        while (true);

        return tipo;
    }

    public String askString(String prompt)
    {
        String request;

        System.out.println(prompt);

        request = scanner.nextLine();

        if (request == null || request.isBlank())
            request = null;
        else
            request = request.trim();

        return request;
    }

    public String askPassword(String prompt)
    {
        String request;

        do
        {
            System.out.println(prompt);

            request = scanner.nextLine();
        }
        while (request == null || request.isBlank() || request.startsWith(" ") || request.endsWith(" "));

        return request;
    }

    public String askDate(String prompt)
    {
        String scelta;
        SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY");

        do
        {
            if (prompt != null)
                System.out.println(prompt);
            else
                System.out.println("Inserire la data (DD/MM/YYYY) : ");

            scelta = scanner.nextLine();

            if (scelta != null)
            {
                if (!scelta.isBlank())
                {
                    scelta = scelta.trim();

                    try
                    {
                        Date data = format.parse(scelta);
                        break;
                    }
                    catch (Exception ex) {}
                }
            }
        }
        while (true);

        return scelta;
    }
}
