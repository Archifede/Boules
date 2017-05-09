package Client;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by user on 15/03/2016.
 */
public class Joueur implements Runnable {
    private Socket socket;


    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private Game game;


    public Joueur(Socket socket) {
        try {
            this.socket = socket;
            socketOut = new PrintWriter(this.socket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }


    @Override
    public void run() {


        créationPlateau();
        new Waiter(game,socketIn);
        KeyAdapter keykey = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //envoi de la touche pressée;
                int key = e.getKeyCode();
                socketOut.println(key);
                System.out.println(key);

            }
        };
        game.addKeyListener(keykey);
        MouseAdapter moumous = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                game.requestFocus();
            }
        };
        game.addMouseListener(moumous);
        game.requestFocus();

    }

    private void créationPlateau(){
        this.game = new Game();

        JFrame frame = new JFrame("Sample Frame");
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
