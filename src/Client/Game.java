

package Client;

import Formes.Boule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Game extends JPanel {
    private ArrayList<Boule> boules = new ArrayList<Boule>();


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for(Boule boule : boules){
            g2d.setColor(boule.getColor());
            g2d.fillOval(boule.getX(),boule.getY(),30,30);
        }



    }

    public void actualiser(int id, int x, int y){


        for(Boule boule : boules){
            if(boule.getId() == id){
                boule.setX(x);
                boule.setY(y);
            }

        }

    }


    public void créerBoule(int id) {

        for(Boule boule : boules){
           if(boule.getId()==id)
               return;
        }
        Boule boule = new Boule(id);
        this.boules.add(boule);
        System.out.println("Boule ajoutée : " +id);
    }

    public void supprimerBoule(int id){
        for(int i = 0; i < boules.size(); ++i){
            if(boules.get(i).getId() == id)
                boules.remove(i);
        }


    }
}