/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author lab_services_student
 */
// push worked
public class Brickbreaker extends JFrame {
    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = 790;
    private static final int PADDING = 10;
    Gameboard game = new Gameboard();

    public static void main(String[] args) {
        JFrame board = new JFrame();
        board.setVisible(true);
        board.setSize(WINDOW_HEIGHT + PADDING, WINDOW_HEIGHT + PADDING);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(false);

        Gameboard game = new Gameboard();
        board.add(game);
        board.addKeyListener(game);
        game.initGame();
        Timer timer = new Timer(15, (ActionEvent e) -> {
            game.gameLogic();
            board.repaint();
        });
        timer.start();
    }

}
