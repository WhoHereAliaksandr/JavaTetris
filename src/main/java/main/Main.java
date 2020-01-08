package main;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static int sizeCell = 30;
    public static int indent = 5;
    public static int countCellWidth = 10, countCellHeight = 22;
    public static int WIDTH = countCellWidth * sizeCell + sizeCell * 6, HEIGHT = countCellHeight * sizeCell + 45;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Tetris");
        frame.setResizable(false);
        frame.setLocation(800, 100);
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        Game game = new Game(frame);
        frame.add(game);
        new Thread(game).start();
        Thread.sleep(100);
        frame.setVisible(true);
    }

}
