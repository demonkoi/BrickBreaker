/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lab_services_student
 */
public class Gameboard extends JPanel implements KeyListener{
    
    
    int ballSpeedX = 5,BallSpeedY = 8;
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 800;
    Ball ball = new Ball(ballSpeedX,BallSpeedY,WINDOW_WIDTH/2, (int)(WINDOW_HEIGHT*0.8));
    Paddle paddle = new Paddle(200);
    Grid grid;
    int score = 0;
    
    public void initGame(){
      grid = new Grid(30,15,WINDOW_WIDTH,WINDOW_HEIGHT);
      grid.createGrid();
    }
    public void gameLogic(){
        paddleCollision();
        for (int i = 0; i < grid.brick.length; i++) {
            for (int j = 0; j < grid.brick[0].length; j++) {
                checkcol(ball, grid.brick[i][j]);
            }
        }
        ball.move();
        paddle.move();
    }
    public void checkcol(Ball a, Brick b){
        Rectangle ba = new Rectangle(a.getLeft()+a.getSpeedx(), a.getTop()+a.getSpeedy(), a.getHeight(), a.getHeight());
        Rectangle br = new Rectangle(b.getLeft(),b.getTop(),b.getHeight(),b.getLength());
        outerloop:
        if (ba.intersects(br)&&b.isAlive()) {
            b.destroy();
                    grid.reduceBrick();
                    score++;
                if (a.getRight()<b.getLeft() || a.getRight()>b.getRight()) {
                    a.setXSpeed();
                    break outerloop;
                }
                else {
                a.setYSpeed();
                break outerloop;
                }
            }
        }
   
    
    
    public void paddleCollision(){
        //check collision
        ball.ySpeed = ((ball.getRight() > paddle.x && ball.x < paddle.x + paddle.paddleWidth)
                && ball.y + ball.size>= paddle.y && ball.y<paddle.y+paddle.paddleHeight)
                    ? ball.ySpeed *= -1 : ball.ySpeed;
        
    }
    @Override
    public void paintComponent(Graphics g){
        //paint components
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.paint(g);
        ball.paint(g);
        paddle.paint(g);
        //add score
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Scores: " + score, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("cal");
    }

    @Override
    public void keyPressed(KeyEvent e) {
          if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {
            paddle.directon(false);
            System.out.println("left");
        }
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
            paddle.directon(true);
            System.out.println("right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     
    }

}
