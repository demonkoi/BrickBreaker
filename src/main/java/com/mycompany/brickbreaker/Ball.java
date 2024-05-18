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
public class Ball implements Object {
    final int size = 20;
    int xSpeed, ySpeed, x, y;

    public Ball(int xSpeed, int ySpeed, int x, int y) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, size, size);
    }

    public void move() {
        xSpeed = x > Gameboard.WINDOW_WIDTH - size && xSpeed > 0 ? -xSpeed : xSpeed;
        xSpeed = x < 0 && xSpeed < 0 ? -xSpeed : xSpeed;
        ySpeed = y < 0 && ySpeed < 0 ? -ySpeed : ySpeed;
        // ySpeed = y>Gameboard.WINDOW_HEIGHT -size? -ySpeed : ySpeed;

        x += xSpeed;
        y += ySpeed;

    }

    @Override
    public int getLeft() {
        return x;
    }

    @Override
    public int getRight() {
        return x + size;
    }

    @Override
    public int getTop() {
        return y;
    }

    @Override
    public int getBottom() {
        return y + size;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

    @Override
    public int getSpeedx() {
        return xSpeed;
    }

    @Override
    public int getSpeedy() {
        return ySpeed;
    }
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
    public void revSetXSpeed() {
        xSpeed *= -1;
    }

    public void revSetYSpeed() {
        ySpeed *= -1;
    }

    @Override
    public boolean destroy() {
        return (y > Gameboard.WINDOW_HEIGHT);
    }

}
