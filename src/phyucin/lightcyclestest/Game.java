package phyucin.lightcyclestest;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements Runnable {

    // true if the instructions page is in the frame
    private boolean instructOn = false;
    // true if the high scores are displayed in the frame
    private boolean scoresOn = false;

    public void run() {

        // Top-level frame
        final JFrame frame = new JFrame("Light Cycles");
        frame.setBackground(Color.BLACK);
        frame.setPreferredSize(new Dimension(500,560));
        frame.setLocation(400, 100);
        frame.setResizable(false);

        /**
         *
         *  Main Menu
         *
         */
        // main menu panel
        final JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setLayout(new GridLayout(2,1));

        // buttons for main menu
        final JButton play = new JButton("Play");
        mainMenu.add(play);

        final JButton quit = new JButton("Quit");
        mainMenu.add(quit);


        // adds main menu panel to the frame
        frame.add(mainMenu);


        // play menu panel that replaces the main menu panel
        final JPanel playMenu = new JPanel();
        playMenu.setLayout(new BorderLayout());
        playMenu.setBackground(Color.BLACK);


        // panel that displays an image or high scores and game type buttons
        final JPanel playMenuUpper = new JPanel();
        playMenuUpper.setLayout(new GridLayout(2, 1));
        playMenuUpper.setBackground(Color.BLACK);

        // panel that holds the buttons for each game type
        final JPanel modes = new JPanel();
        modes.setLayout(new GridLayout(1,3));
        modes.setBackground(Color.BLACK);

        // buttons for playMenuUpper
        final JButton twoPlayer = new JButton("Two Player");
        modes.add(twoPlayer);

        // adds panels to playMenuUpper
        playMenuUpper.add(modes);

        // panel that hold high score and main menu buttons
        final JPanel bottomMenu = new JPanel();
        bottomMenu.setBackground(Color.BLACK);

        final JButton back = new JButton("Main Menu");
        bottomMenu.add(back);

        // adds bottomMenu and playMenuUpper to playMenu
        playMenu.add(playMenuUpper, BorderLayout.CENTER);
        playMenu.add(bottomMenu, BorderLayout.SOUTH);

        /**
         *
         *  Two-player Level Menu
         *
         */
        // panel that holds the buttons and labels for two-player mode
        final JPanel twoMenu = new JPanel();
        twoMenu.setLayout(new GridLayout(1,2));
        twoMenu.setBackground(Color.BLACK);

        final JPanel bottomGameMenu = new JPanel();
        twoMenu.setLayout(new GridLayout(1,2));
        twoMenu.setBackground(Color.BLACK);


        // the score labels for each player
        final JLabel scoreTwo1 = new JLabel("   Player 1: 0");
        scoreTwo1.setForeground(Color.WHITE);
        scoreTwo1.setBackground(Color.BLACK);
        twoMenu.add(scoreTwo1);
        final JLabel scoreTwo2 = new JLabel("   Player 2: 0");
        scoreTwo2.setForeground(Color.WHITE);
        scoreTwo2.setBackground(Color.BLACK);
        twoMenu.add(scoreTwo2);

        // the reset and main menu buttons for two-player mode
        final JButton resetTwo = new JButton("Restart");
        bottomGameMenu.add(resetTwo);
        final JButton exitTwo = new JButton("Back");
        bottomGameMenu.add(exitTwo);

        // the two-player level
        final TwoPlayerMap levelTwoPlayer =
                new TwoPlayerMap(scoreTwo1, scoreTwo2, 2);
        levelTwoPlayer.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        /**
         *
         *  Adding action listeners
         *
         */
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainMenu);
                frame.setLayout(new BorderLayout());
                frame.add(levelTwoPlayer, BorderLayout.CENTER);
                frame.add(twoMenu, BorderLayout.NORTH);
                frame.add(bottomGameMenu, BorderLayout.SOUTH);
                frame.update(frame.getGraphics());
                levelTwoPlayer.requestFocusInWindow();
                levelTwoPlayer.revalidate();
                levelTwoPlayer.reset();
            }
        });


        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });


        resetTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelTwoPlayer.reset();
            }
        });

        exitTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(levelTwoPlayer);
                frame.remove(twoMenu);
                frame.remove(bottomGameMenu);
                frame.add(mainMenu);
                frame.update(frame.getGraphics());
                mainMenu.revalidate();
                levelTwoPlayer.restartGame();
            }
        });

        // put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // start the game running
        levelTwoPlayer.reset();
    }

    // Start game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
