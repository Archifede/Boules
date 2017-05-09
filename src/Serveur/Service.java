package Serveur;

/**
 * Created by user on 15/03/2016.
 */

import Client.Game;
import Formes.Boule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Service implements Runnable {

    private Socket socket;
    private static Object lock = new Object();
    private static ArrayList<Service> listeServices = new ArrayList<Service>();
    private Boule boule;
    private static int noIncrement = 0;

    PrintWriter socketOut;


    public Service(Socket socket) {
        this.socket = socket;
        Service.listeServices.add(this);
    }


    @Override
    public void run() {

        BufferedReader socketIn = null;


        try {
            socketOut = new PrintWriter(this.socket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            boule = new Boule(Service.noIncrement);
            String former = "init ";
            boolean first = true;
            for (Service s : Service.listeServices) {
                if(first){
                    former += s.boule.getId();
                    first = false;
                }
                else
                    former += " " + s.boule.getId();


            }
            for (Service s : Service.listeServices) {
                s.socketOut.println(former);
            }

            Service.noIncrement++;



        }


        try {
            while (true) {

                //Réception du code de touche pressée
                String reception = socketIn.readLine();

                int toucheCode = Integer.parseInt(reception);
                System.out.println(toucheCode);
                moveBall(toucheCode);

                String message = "";
                boolean first = true;
                for (Service s : Service.listeServices) {
                    if (first) {
                        message += s.boule;
                        first = false;
                    }
                    else{
                        message += "," + s.boule;
                    }



                }
                for (Service s : Service.listeServices) {
                    s.socketOut.println(message);
                    System.out.println(message);
                }


            }
        } catch (IOException e) {

            try {
                String deco = "deco " + this.boule.getId();
                for (Service s : Service.listeServices) {
                    s.socketOut.println(deco);

                }
                System.out.println(deco);

                listeServices.remove(this);
                this.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void lancer() {
        new Thread(this).start();
    }


    private void moveBall(int e) {
        int x = boule.getX();
        int y = boule.getY();
        if (e >= 37 && e <= 40) {
            e = e - 38;
            x += (e % 2) * 5;
            y += ((e - 1) % 2) * 5;
            boule.setX(x);
            boule.setY(y);

        }


    }
}
