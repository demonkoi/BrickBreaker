/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author lab_services_student
 */
public class Grid {
    Brick[][] brick;
    private int brickWidth,brickHeight,col,row,windowWidth,WindowHeight;
    private int brickRemaning = 0;
    public Grid(int col, int row,int windowWidth,int WindowHeight){
        this.row=row;
        this.col=col;
        this.windowWidth=windowWidth;
        this.WindowHeight=WindowHeight;
        brickWidth = windowWidth/row;
        brickHeight = WindowHeight/col; 
        col -= 20;
        brick = new Brick[col][row];
       
        
    }
    public void createGrid(){
      int lastX =0;
        int lastY =0;
        Random rand = new Random();
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length ; j++) {
                brickRemaning++;
                
              brick[i][j] = new Brick(lastX, lastY, brickWidth, brickHeight, rand.nextInt(3));
              lastX += brickWidth;
            }
            lastX =0;
            lastY += brickHeight;
        }  
    }
    public void paint(Graphics g){
       
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length; j++) {
               g.setColor(brick[i][j].getColor());
               g.fillRect(brick[i][j].getLeft(),brick[i][j].getTop(),brick[i][j].getLength(),brick[i][j].getHeight());
               g.setColor(Color.BLACK);
               g.drawRect(brick[i][j].getLeft(),brick[i][j].getTop(),brick[i][j].getLength(),brick[i][j].getHeight());
            }
        }
    }
    public void reduceBrick(){
        brickRemaning--;
    }
}
