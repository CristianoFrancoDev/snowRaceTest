package test;

import classi.Ruoli;
import classi.Utenti;

import java.util.Scanner;

public class Main
{
   public static void main(String[] args) {
      Ruoli.tipo r = Ruoli.tipo.USER;
      Scanner s = new Scanner(System.in);

      System.out.print("Nome: ");
      String nome = s.next();
      System.out.print("Indirizzo: ");
      String ind = s.next();
      System.out.print("Luogo: ");
      String luogo = s.next();
      System.out.print("Password: ");
      String pass = s.next();

      Utenti u = new Utenti(nome, ind, luogo, r, pass, false);
      System.out.println(u.toString());
   }
}
