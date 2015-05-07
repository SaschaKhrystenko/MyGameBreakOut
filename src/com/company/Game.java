package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ua001022 on 24.03.2015.
 */
public class Game extends JFrame {
    public Game(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        Game myGame = new Game("BreakOut");
        myGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myGame.setContentPane(new GamePanel());
        myGame.setResizable(false);
        myGame.setVisible(true);
        myGame.pack();
    }

}
