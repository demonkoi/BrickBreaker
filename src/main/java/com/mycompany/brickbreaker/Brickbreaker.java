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
public class Brickbreaker extends JFrame {
    
    Gameboard game = new Gameboard();
    public static void main(String[] args) {
        System.out.println("Hello World!");
        JFrame board = new JFrame();
        board.setVisible(true);
        board.setSize(510, 810);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
