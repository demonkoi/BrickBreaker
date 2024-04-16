/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lab_services_student
 */
public class Paddle {
    int paddleWidth = 75;
    int paddleHeight = 15;
    int x = (Gameboard.WINDOW_WIDTH / 2) - (paddleWidth);
    int y = Gameboard.WINDOW_HEIGHT - 60;
    int move = 0;
    int moveSpeed = 7;

    public Paddle(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, paddleWidth, paddleHeight);
    }

    public void directon(boolean b) {
        if (b)
            move = moveSpeed;
        else
            move = -moveSpeed;
    }

    public void move() {

        x += move;
        if (x < 0 || x > Gameboard.WINDOW_WIDTH - paddleWidth) {
            move = 0;
            // System.out.println((Gameboard.WINDOW_WIDTH - paddleWidth));
        }
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return y;
    }

}
