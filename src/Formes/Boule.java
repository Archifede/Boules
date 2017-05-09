package Formes;

import java.awt.*;

/**
 * Created by user on 16/03/2016.
 */
public class Boule {
    private int x;
    private int y;
    private int id;
    private Color color;


    public Boule(int id){
        this.x = 0;
        this.y = 0;
        this.id = id;
    }

    public void move(int x){

    }

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return this.id + " " + this.x + " " +  this.y;
    }

    public Color getColor() {
        return color;
    }
}

