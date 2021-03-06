/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game;

import com.teamecho.game.screens.StartGamePanel;
import com.teamecho.game.screens.TutorialPanel;
import com.teamecho.game.screens.GameOverPanel;
import com.teamecho.game.screens.GameWinPanel;
import com.teamecho.game.screens.CreditsPanel;
import com.teamecho.levels.Level1;
import com.teamecho.levels.Level2;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.CardLayout;

/**
 * Created by Jeff Grant 21/02/17
 *
 * @author 1622542
 */
public class Game {
    
    //this is the sizes of the window
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    /**
     * the height and width of level 1 and 2 are set seperetly due to their
     * physical size being larger than the screen size
     *
     */
    public final int Level1_Height = 800;
    public final int Level1_Width = 3600;
    public final int Level2_Height = 800;
    public final int Level2_Width = 3600;
    
    private final String Title = "Little Fire";

    JFrame gameWindow; // Main Game Window - We add game components here
    StartGamePanel startScreen; // the starting screen displayed before the game is played
    Level1 lvl1; // this is level 1
    Level2 lvl2; // this is level 2
    TutorialPanel Tutorial;//this is the options menu
    CreditsPanel Credits;//this is the level screen
    GameOverPanel GameOverScreen;//this is the game over screen
    GameWinPanel GameWinScreen; //this is the screen on completing the game

    public Game() {
        initWindow(); // Call the initWindow method to initialise window properties
        initScreens(); // Call the initStartScreen to set up the start screen
    }

    private void initWindow() {
        gameWindow = new JFrame();
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits the program whenthe x button is clicked
        gameWindow.setResizable(false);//stops the player from chjanging the dimentions of the screen
        gameWindow.setLocationRelativeTo(null); // Centers game on screen
        gameWindow.setTitle(Title);
    }

    private void initScreens() {
        gameWindow.setLayout(new CardLayout());// this creates a new card layout
        
        startScreen = new StartGamePanel(this);
        startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        lvl1 = new Level1(this);
        //the size for level 1 is set separetly to ensure functionality for the whole level
        lvl1.setPreferredSize(new Dimension(Level1_Width, Level1_Height));

        lvl2 = new Level2(this);
        //the size for level 1 is set separetly to ensure functionality for the whole level
        lvl2.setPreferredSize(new Dimension(Level2_Width, Level2_Height));

        Tutorial = new TutorialPanel(this);
        Tutorial.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        GameOverScreen = new GameOverPanel(this);
        GameOverScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        GameWinScreen = new GameWinPanel(this);
        GameWinScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        Credits = new CreditsPanel(this);
        Credits.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.getContentPane().add(lvl1, "LVL1");
        gameWindow.getContentPane().add(lvl2, "LVL2");
        gameWindow.getContentPane().add(Tutorial, "TUTORIAL");
        gameWindow.getContentPane().add(GameOverScreen, "OVER");
        gameWindow.getContentPane().add(GameWinScreen, "WIN");
        gameWindow.getContentPane().add(Credits, "CREDITS");
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
         * Screen 4 = Level 2
         *
         * screen 5 = Game Over Screen
         *
         * screen 6 = credits screen
         *
         * screen 7 = game win screen
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

                cl.show(gameWindow.getContentPane(), "TUTORIAL");
                Tutorial.requestFocus();
                break;
            case 3:
                lvl1.reset();//this resets the variables and objects within level 1
                lvl1.start();//this restarts the tiomer for level1
                cl.show(gameWindow.getContentPane(), "LVL1");
                lvl1.requestFocus();
                break;
            case 4:
                lvl2.reset();
                lvl2.start();
                cl.show(gameWindow.getContentPane(), "LVL2");
                lvl2.requestFocus();
                break;
            case 5:
                cl.show(gameWindow.getContentPane(), "OVER");
                GameOverScreen.requestFocus();
                break;
            case 6:
                cl.show(gameWindow.getContentPane(), "CREDITS");
                Credits.requestFocus();
                break;
            case 7:
                cl.show(gameWindow.getContentPane(), "WIN");
                GameWinScreen.requestFocus();
                break;
            default:

        }
    }

    public static void main(String[] args) {
        Game window = new Game();
        window.showStartScreen();
    }

}
