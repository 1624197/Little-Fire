/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game;

import com.teamecho.game.screens.StartGamePanel;
import com.teamecho.game.screens.OptionMenuPanel;
import com.teamecho.game.screens.GameOverPanel;
import com.teamecho.levels.Level1;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.CardLayout;

/**
 * Created by Jeff Grant 21/02/17
 *
 * @author 1622542
 */
public class Game {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    public final int Level1_Height = 800;
    public final int Level1_Width = 3600;
    private final String Title = "Little Fire";

    private int lastGameScore = 0;

    JFrame gameWindow; // Main Game Window - We add game components here
    StartGamePanel startScreen; // the starting screen displayed before the game is played
    Level1 lvl1; // this is level 1
    OptionMenuPanel optionMenu;//this is the options menu
    GameOverPanel GameOverScreen;//this is the game over screen
    public Game() {
        initWindow(); // Call the initWindow method to initialise window properties
        initScreens(); // Call the initStartScreen to set up the start screen
    }

    private void initWindow() {
        gameWindow = new JFrame();
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null); // Centers game on screen
        gameWindow.setTitle(Title);
    }

    private void initScreens() {
        gameWindow.setLayout(new CardLayout());
        startScreen = new StartGamePanel(this);
        startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        lvl1 = new Level1(this);
        //the size for level 1 is set separetly to ensure functionality for the whole level
        lvl1.setPreferredSize(new Dimension(Level1_Width, Level1_Height));
        optionMenu = new OptionMenuPanel(this);
        optionMenu.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        GameOverScreen = new GameOverPanel(this);
        GameOverScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        // This will add a start Screen to the Main Window
        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.getContentPane().add(lvl1, "LVL1");
        gameWindow.getContentPane().add(optionMenu, "OPTION");
        
        gameWindow.getContentPane().add(GameOverScreen, "OVER");
        
    }

    public void showStartScreen() {
        gameWindow.setVisible(true);
        startScreen.requestFocus();
    }

    /**
     * display level1 panel
     *
     * @param Screen
     */
    public void SelectScreen(int Screen) {
        //this methood will decide the screen to load based on an input
        //to change this screen call this method and use the variable above to specify the screen
        CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();

        /**
         * a switch statement is used to select the various screens within the
         * game
         *
         * Screen 1 = Main menu
         *
         * Screen 2 = Options menu
         *
         * Screen 3 = Level 1
         *
         * Screen 4 = Level 2 (Not yet implemented)
         *
         * screen 5 = Game Over Screen 
         *
         * Any further Screens added or planned to be added should be added here
         * and adjustments to the above list should be made
         */
        switch (Screen) {
            case 1:
                /**
                 * the show command is used as this allows the screens to be
                 * loaded dynamically without using repeated lines of next and
                 * previous to cycle through the cardlayout
                 *
                 */
                cl.show(gameWindow.getContentPane(), "INTRO");
                startScreen.requestFocus();
                break;
            case 2:
                cl.show(gameWindow.getContentPane(), "OPTION");
                optionMenu.requestFocus();
                break;
            case 3:
                cl.show(gameWindow.getContentPane(), "LVL1");
                lvl1.requestFocus();
                break;
            case 5:
                cl.show(gameWindow.getContentPane(), "OVER");
                GameOverScreen.requestFocus();
                break;
            default:

        }
    }

    public static void main(String[] args) {
        Game window = new Game();
        window.showStartScreen();
    }

}
