/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.Color;

/**
 *
 * @author lab_services_student
 */
public class Brick implements Object{
    
    int x,y,height,width,health;
    Color color;
    
    
    public Brick(int x, int y,int width,int height,int health){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.health = health;
         switch (health){
            case 0: color = Color.BLACK;
            break;
            case 1: color = Color.BLUE;
            break;
            case 2: color = Color.GREEN;
            break;
            case 3: color = Color.darkGray;
        }
    }
    public Boolean isAlive(){
       boolean state = (health == 0) ? false : true;
       return state;
    }
    @Override
    public int getLeft() {
        return x;
    }

    @Override
    public int getRight() {
        return x+width;
    }
    
    public Color getColor(){
        return color;
    }
    @Override
    public int getTop() {
        return y;
    }

    @Override
    public int getBottom() {
        return y+height;
    }

    @Override
    public int getLength() {
       return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSpeedx() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getSpeedy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void destroy() {
        health--;
         switch (health){
            case 0: color = Color.BLACK;
            break;
            case 1: color = Color.BLUE;
            break;
            case 2: color = Color.GREEN;
            break;
            case 3: color = Color.darkGray;
        }
    }
    
}
