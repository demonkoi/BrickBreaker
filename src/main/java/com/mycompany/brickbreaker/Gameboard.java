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
import javax.swing.text.html.HTMLDocument.Iterator;

import org.w3c.dom.css.Rect;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author lab_services_student
 */
public class Gameboard extends JPanel implements KeyListener {

    public static final int WINDOW_WIDTH = Brickbreaker.WINDOW_WIDTH;
    public static final int WINDOW_HEIGHT = Brickbreaker.WINDOW_HEIGHT;
    int ballSpeedX = 5, BallSpeedY = 8;
    ArrayList<Ball> ball = new ArrayList<>();
    ArrayList<PowerUp> powerUp = new ArrayList<>();
    Paddle paddle;
    Grid grid;
    int score = 0;
    int life = 3;

    public void initGame() {
        grid = new Grid(30, 15, WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.createGrid();
        ball.add(new Ball(ballSpeedX, BallSpeedY, WINDOW_WIDTH / 2, (int) (WINDOW_HEIGHT * 0.8)));
        paddle = new Paddle(200);
        System.out.println(ball.getFirst() + "call");
        powerUp.add(new PowerUp(100, 100));

    }

    public void gameLogic() {
       
        //check brick collision
        brickColl();
        paddle.move();
        powerUpMove();
        // check collision for ball and paddle and powerup
        checkballcoll();
        checkPowerUpColl();
    }

    public void brickColl(){
        for (int i = 0; i < grid.brick.length; i++) {
            for (int j = 0; j < grid.brick[0].length; j++) {
                for (Ball ball : ball) {
                    checkcol(ball, grid.brick[i][j]);
                }
            }
        }
    }
    public void powerUpMove() {
        powerUp.forEach((power) -> {
            power.move();
        });
    }
    public void checkballcoll() {

        if (ball.size() > 0 && life > 0) {
            ball.removeIf(ball -> {
                paddleCollision(ball);
                ball.move();
                if (ball.y > WINDOW_HEIGHT) {
                    System.out.println("ball size" + this.ball.size() + "life" + life);
                    return true;
                }
                return false;
            });
            if (ball.isEmpty()) {
                life--;
                ball.add(new Ball(ballSpeedX, -BallSpeedY, WINDOW_WIDTH / 2, (int) (WINDOW_HEIGHT * 0.5)));
            }
        } else {
            System.out.println("Game Over press enter to restart");
        }


    }

    public void checkPowerUpColl(){
        powerUp.removeIf(power -> {
            if (power.getTop() > WINDOW_HEIGHT) {
                //remove powerup if it goes out of screen
            return true;
            }
            if (powerupColl(paddle, power)) {
            System.out.println("scream");
            switch (power.getType()) {
                case 1:
                paddle.paddleWidth += 50;
                break;
                case 2:
                paddle.paddleWidth -= 50;
                break;
                case 3:
                ball.add(new Ball(ballSpeedX, BallSpeedY, this.ball.get(0).getLeft(),
                    this.ball.get(0).getTop()));
                break;
                case 4:
                ball.forEach(ball -> {
                    ball.setYSpeed((int)(ball.getSpeedy() * 1.3));
                    ball.setXSpeed((int)(ball.getSpeedx() * 1.3));
                });
                break;
                case 5:
                ball.forEach(ball -> {
                    ball.setYSpeed((int)(ball.getSpeedy() * 0.7));
                    ball.setXSpeed((int)(ball.getSpeedx() * 0.7));
                });
                break;
            }
            return true;
            }
            return false;
        });
    }
    public void checkcol(Ball ball, Brick brick) {
        Rectangle rball = new Rectangle(ball.getLeft() + ball.getSpeedx(), ball.getTop() + ball.getSpeedy(),
                ball.getHeight(),
                ball.getHeight());
        Rectangle rbrick = new Rectangle(brick.getLeft(), brick.getTop(), brick.getHeight(), brick.getLength());
        outerloop: if (rball.intersects(rbrick) && brick.isAlive()) {
            if (brick.destroy()){
                powerUp.add(new PowerUp(brick.getLeft(), brick.getTop()));
            }
            grid.reduceBrick();
            score++;
            if (ball.getRight() < brick.getLeft() || ball.getRight() > brick.getRight()) {
                ball.revSetXSpeed();
                break outerloop;
            } else {
                ball.revSetYSpeed();
                break outerloop;
            }
        }
    }

    public boolean powerupColl(Object paddle, Object powerUp) {
        Rectangle rpaddle = new Rectangle(paddle.getLeft(), paddle.getTop(), paddle.getLength(), paddle.getHeight());
        Rectangle rpowerUp = new Rectangle(powerUp.getLeft(), powerUp.getTop(), powerUp.getHeight(),
                powerUp.getLength());
        return (rpaddle.intersects(rpowerUp)) ? true : false;
    }

    public void paddleCollision(Ball ball) {
        // check collision
        if (ball.getRight() > paddle.x && ball.x < paddle.x + paddle.paddleWidth
                && ball.y + ball.size >= paddle.y && ball.y < paddle.y + paddle.paddleHeight && ball.ySpeed > 0) {
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
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.paint(g);
        ball.forEach(ball -> {
            ball.paint(g);
        });
        powerUp.forEach((power) -> {
            power.paint(g);
        });

        paddle.paint(g);
        g.setFont(new Font("serif", Font.BOLD, 18));
        g.drawString("lifes: " + life, 10, 30);
        // add score
        g.setFont(new Font("serif", Font.BOLD, 18));
        g.drawString("Scores: " + score, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("cal");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)) {
            paddle.directon(false);
            System.out.println("left");
        }
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) {
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
