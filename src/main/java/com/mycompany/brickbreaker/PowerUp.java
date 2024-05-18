package com.mycompany.brickbreaker;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class PowerUp implements Object {
    private int x, y, type, ySpeed;
    private int size = 20;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random(6);
        this.type = type;
        ySpeed = 2;
    }

    public void move() {
        y += ySpeed;
    }

    public void paint(Graphics g) {
        switch (type) {
            case 1:
                g.setColor(Color.RED);
                g.fillOval(x, y, size, size);
                break;
            case 2:
                g.setColor(Color.BLUE);
                g.fillOval(x, y, size, size);
                break;
            case 3:
                g.setColor(Color.GREEN);
                g.fillOval(x, y, size, size);
                break;
            case 4:
                g.setColor(Color.YELLOW);
                g.fillOval(x, y, size, size);
                break;
            case 5:
                g.setColor(Color.PINK);
                g.fillOval(x, y, size, size);
                break;
        }

    }

    public int getType() {
        return type;
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

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public boolean destroy() {
        if (y > Gameboard.WINDOW_HEIGHT) {
            return true;
        }
        return false;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getSpeedy() {
        return ySpeed;
    }

    @Override
    public int getSpeedx() {
        return 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

}
