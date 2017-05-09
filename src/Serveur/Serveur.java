package Serveur;

/**
 * Created by user on 15/03/2016.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur implements Runnable {

    private ServerSocket socketListener;


    public Serveur(int port) {

        try {

            socketListener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) try {
            Socket socket;
            socket = socketListener.accept();
            System.out.println("AppliClient accepté : " + socket.getInetAddress());
            new Service(socket).lancer();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void lancer() {
        new Thread(this).start();
    }
}

