package Client;

import Formes.Boule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 16/03/2016.
 */
public class Waiter implements Runnable {


    BufferedReader socketIn;
    Game game;


    public Waiter(Game game,BufferedReader socketIn) {

        this.socketIn = socketIn;

        this.game = game;

        new Thread(this).start();
    }


    @Override
    public void run() {
        while (true) {
            try {

                String reception = socketIn.readLine();
                if(reception.contains("init")){
                    System.out.println("init reçu");
                    String[] init = reception.split(" ");
                    for(String e : init){
                        System.out.println(e + ", ");

                    }
                    for(String e : init){

                        if(!e.equals("init")){
                            int id = Integer.parseInt(e);
                            game.créerBoule(id);
                            game.repaint();
                        }

                    }



                }
                else if(reception.contains("deco")){

                    String[] deco = reception.split(" ");
                    int id = Integer.parseInt(deco[1]);
                    game.supprimerBoule(id);




                }
                else {
                    String[] messages = reception.split(",");
                    int id=0,x=0,y=0;
                    for(String e : messages){

                        String[] boule = e.split(" ");

                        id = Integer.parseInt(boule[0]);
                        x = Integer.parseInt(boule[1]);
                        y = Integer.parseInt(boule[2]);
                        System.out.println("reception : " + "id : " + id + ", x : " + x +", y : " + y);
                        game.actualiser(id,x,y);
                        game.repaint();
                    }



                    //on repaint le jeu à mettre dans l'autre thread

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
