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
    private final int PADDING = 1;
    private final int arc = 6;
    private int brickWidth, brickHeight, col, row, windowWidth, WindowHeight;
    private int brickRemaning = 0;

    public Grid(int col, int row, int windowWidth, int WindowHeight) {
        this.row = row;
        this.col = col;
        this.windowWidth = windowWidth;
        this.WindowHeight = WindowHeight;
        brickWidth = windowWidth / (row + PADDING);
        brickHeight = WindowHeight / (col + PADDING);
        col -= 20;
        brick = new Brick[col][row];

    }

    public void createGrid() {
        int lastX = 0;
        int lastY = 0;
        Random rand = new Random();
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length; j++) {
                brickRemaning++;

                brick[i][j] = new Brick(lastX, lastY, brickWidth, brickHeight, rand.nextInt(3));
                lastX += brickWidth + PADDING;
            }
            lastX = 0;
            lastY += brickHeight + PADDING;
        }
    }

    public void paint(Graphics g) {

        for (int i = 0; i < this.brick.length; i++) {
            for (int j = 0; j < this.brick[0].length; j++) {
                Brick brick = this.brick[i][j];
                g.setColor(brick.getColor());
                g.fillRoundRect(brick.getLeft(), brick.getTop(), brick.getLength(),
                        brick.getHeight(), arc, arc);
                g.setColor(Color.BLACK);
                // g.drawRect(brick.getLeft(), brick.getTop(), brick.getLength(),
                // brick.getHeight());
                if (brick.damage != 0) {
                    g.setColor(Color.BLACK);
                    g.drawLine(brick.getLeft(), brick.getTop(),
                            brick.getRight(), brick.getBottom());
                    g.drawLine(brick.getRight(), brick.getTop(),
                            brick.getLeft(), brick.getBottom());
                }
            }
        }
    }

    public void reduceBrick() {
        brickRemaning--;
    }
}
