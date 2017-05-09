package Serveur;

import java.util.Scanner;

/**
 * Created by user on 15/03/2016.
 */
public class AppliServeur {
    public static void main(String[] args) {
        int port;
        System.out.println("Entrer le port");
        Scanner clavier = new Scanner(System.in);
        port = Integer.parseInt(clavier.next());


        new Serveur(port).lancer();

    }


}
