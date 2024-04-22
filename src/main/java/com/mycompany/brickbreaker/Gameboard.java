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
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lab_services_student
 */
public class Gameboard extends JPanel implements KeyListener {

    public static final int WINDOW_WIDTH = Brickbreaker.WINDOW_WIDTH;
    public static final int WINDOW_HEIGHT = Brickbreaker.WINDOW_HEIGHT;
    int ballSpeedX = 5, BallSpeedY = 8;
    ArrayList<Ball> ball = new ArrayList<>();
    Paddle paddle;
    Grid grid;
    int score = 0;

    public void initGame() {
        grid = new Grid(30, 15, WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.createGrid();
        ball.add(new Ball(ballSpeedX, BallSpeedY, WINDOW_WIDTH / 2, (int) (WINDOW_HEIGHT * 0.8)));
        paddle = new Paddle(200);
        System.out.println(ball.getFirst() + "call");

    }

    public void gameLogic() {
        System.out.println(WINDOW_WIDTH);
        for (int i = 0; i < grid.brick.length; i++) {
            for (int j = 0; j < grid.brick[0].length; j++) {

                for (Ball ball : ball) {
                    checkcol(ball, grid.brick[i][j]);
                }
            }
        }
        for (Ball ball : ball) {
            paddleCollision(ball);
            ball.move();
        }
    }

    public void checkcol(Ball a, Brick b) {
        Rectangle ba = new Rectangle(a.getLeft() + a.getSpeedx(), a.getTop() + a.getSpeedy(), a.getHeight(),
                a.getHeight());
        Rectangle br = new Rectangle(b.getLeft(), b.getTop(), b.getHeight(), b.getLength());
        outerloop: if (ba.intersects(br) && b.isAlive()) {
            b.destroy();
            grid.reduceBrick();
            score++;
            if (a.getRight() < b.getLeft() || a.getRight() > b.getRight()) {
                a.setXSpeed();
                break outerloop;
            } else {
                a.setYSpeed();
                break outerloop;
            }
        }
    }

    public void paddleCollision(Ball ball) {
        // check collision
        if (ball.getRight() > paddle.x && ball.x < paddle.x + paddle.paddleWidth
                && ball.y + ball.size >= paddle.y && ball.y < paddle.y + paddle.paddleHeight) {
            ball.ySpeed *= -1;
            if (ball.x + ball.size / 2 < paddle.x + paddle.paddleWidth / 2) {
                ball.xSpeed = -Math.abs(ball.xSpeed);
            } else {
                ball.xSpeed = Math.abs(ball.xSpeed);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        // paint components
        g.setColor(Color.black);
        System.out.println(WINDOW_WIDTH + " " + WINDOW_HEIGHT);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.paint(g);
        for (Ball ball : ball) {
            ball.paint(g);
        }
        
        paddle.paint(g);
        // add score
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
        // restart game
        if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
            initGame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
