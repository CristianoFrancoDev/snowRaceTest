package test;

import classi.Ruoli;
import classi.Utenti;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
    Ruoli.tipo r = Ruoli.tipo.admin;
    Scanner s= new Scanner(System.in);

    System.out.print("Nome: ");
    String nome = s.next();

    System.out.print("Indirizzo: ");
    String indirizzo = s.next();

    System.out.print("Luogo: ");
    String luogo = s.next();

    System.out.print("Password: ");
    String password = s.next();


    Utenti u = new Utenti (nome, indirizzo, luogo, r, password, false );
    System.out.println(u.toString());

    }

}
