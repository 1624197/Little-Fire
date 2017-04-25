/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.levels;

/**
 * Created by Jeff Grant 22/02/17
 *
 * @author 1622542
 */
import com.teamecho.game.Game;
import com.teamecho.game.objects.Platform;
import com.teamecho.game.objects.Portal;
import com.teamecho.characters.Player;
import com.teamecho.characters.Ember;
import com.teamecho.characters.Enemy;
import com.teamecho.characters.SpikePit;
import com.teamecho.game.Sound;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 * This panel represents the game world It contains all of the objects that take
 * part in the game It uses a timer to update every 10ms It uses a keyAdapter to
 * listen for user key presses and update the player's position
 */
public class Level1 extends JPanel implements ActionListener {

    private int score = 0;
    private int health = 100;
    private Timer timer;
    BufferedImage background;
    private Game game;
    private Player thePlayer;
    private Portal thePortal;
    private Ember[] embers;
    private Enemy[] enemies;
    private SpikePit[] spikepit;
    private Platform[] Platform;
    private int InAir;
    int VIEWPORT_SIZE_X = 800;
    int offsetMaxX = 3600 - VIEWPORT_SIZE_X;
    int offsetMinX = 0;
    int camX = 0;
    int camY = 0;

    public int CurrentCollisionDelay = 0;
    public int MaxCollisionDelay = 30;

    private final int NUMBER_OF_ENEMIES = 7;
    private final int[] EnemyX = {5, 12, 16, 30, 42, 44, 46};
    private final int[] EnemyY = {8, 1, 1, 3, 6, 1, 7};
    private final String[] EnemyDirection = {"RIGHT", "UP","LEFT","UP","LEFT","UP", "DOWN"};
    private final int[] EnemyDistance = {3, 6, 3, 1, 2, 3, 4};
    private final int NUMBER_OF_EMBERS = 10;
    private final int NUMBER_OF_SPIKEPITS = 10;
    private final int[] SpikepitX = {11, 17, 24, 25, 27, 34, 39, 40, 48, 49};
    private final int[] SpikepitY = {1, 1, 1, 6, 1, 1, 6, 1, 1, 1};
    private final int NUMBER_OF_PLATFORMS = 34;
    private final int[] PlatformX = {5, 6, 7, 8, 9, 10, 13, 14, 15, 19, 20, 21, 22, 23, 24, 25, 26, 31, 32, 33, 35, 36, 37, 38, 38, 39, 40, 41, 42, 43, 45, 46, 46, 47};
    private final int[] PlatformY = {1, 2, 3, 4, 4, 4, 4, 4, 3, 1, 2, 3, 4, 4, 5, 5, 5, 1, 2, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 4, 1, 1, 2, 1};

    private final int PortalX = 3400;
    private final int PortalY = 200;
    private final int blockspace = 64;
    private final int startline = 100;

    private final int GroundLevel = 552;

    public Level1(Game theGame) {

        game = theGame;
        thePlayer = new Player();
        thePlayer.setY(GroundLevel);
        thePlayer.setX(64);
        thePortal = new Portal(startline + PortalX, GroundLevel - PortalY);
        embers = new Ember[NUMBER_OF_EMBERS];
        enemies = new Enemy[NUMBER_OF_ENEMIES];
        spikepit = new SpikePit[NUMBER_OF_SPIKEPITS];
        Platform = new Platform[NUMBER_OF_PLATFORMS];
        Random rand = new Random();

        int emberX, emberY; // X and Y coordinates for the collectables
        int spikepitY; // X and Y coordinates for the spike pits

        //Initialise all embers
        for (int i = 0; i < NUMBER_OF_EMBERS; i++) {
            emberX = rand.nextInt(3600) + (thePlayer.getX() + 20); // will ensure that the enemy cannot spawn under the player start position
            emberY = rand.nextInt(GroundLevel) + 1; // pick a random Y coorinate

            // Use the overloaded ember constructor to create a new
            // ember object with the X and Y coordinates selected
            // and a score value
            embers[i] = new Ember(emberX, emberY, 30);
        }

        // Initialise all Monster Objects
        for (int j = 0; j < NUMBER_OF_ENEMIES; j++) {
            enemies[j] = new Enemy(startline + (EnemyX[j] * blockspace), GroundLevel - (EnemyY[j] * blockspace) , EnemyDirection[j] , EnemyDistance[j]*blockspace);
        }

        // Initialise all Spike Pits
        for (int k = 0; k < NUMBER_OF_SPIKEPITS; k++) {
            spikepit[k] = new SpikePit(startline + (SpikepitX[k] * blockspace), GroundLevel - (SpikepitY[k] * blockspace));
        }

        for (int i = 0; i < NUMBER_OF_PLATFORMS; i++) {
            Platform[i] = new Platform(startline + (PlatformX[i] * blockspace), GroundLevel - (PlatformY[i] * blockspace));
        }

        init();
    }
    
//This is the private init method that we use to set the defaults for the 3. * level.
//We can call this method to reset the level (if required) - we can't do that
//with the constructor method - that can only be called once.
    public void init() {
        score = 0;
        health = 100;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);

        try {
            background = ImageIO.read(getClass().getResource("/Images/Screens/level1_background.png"));
        } catch (Exception ex) {
            System.err.println("Error loading Level 1 background image");
        }

        timer = new Timer(10, this);
        timer.start();

        //Starts the background music
        Sound.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);

    }

    /**
     * This method initiates the in game drawing. The graphics parameter allows
     * drawing operations to be carried out on the component. We use this method
     * to draw all of the game components - layering them from front to back
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(-camX, 0);
        //Draw Background
        g.drawImage(background, 0, 0, null);
        //Draw Obsticles

        //Draw the player Character
        g.drawImage(thePlayer.getSprite(), thePlayer.getX(), thePlayer.getY(), null);
        g.drawImage(thePortal.getSprite(), thePortal.getX(), thePortal.getY(), null);
        //Draw the ember if it has not been picked up
        for (int i = 0; i < NUMBER_OF_EMBERS; i++) {
            if (embers[i].getVisible() == true) {
                g.drawImage(embers[i].getSprite(), embers[i].getX(), embers[i].getY(), null);
            }
        }

        //Draw each monster on screen if it is alive
        for (int j = 0; j < NUMBER_OF_ENEMIES; j++) {
            if (enemies[j].getVisible() == true) {
                g.drawImage(enemies[j].getSprite(), enemies[j].getX(), enemies[j].getY(), null);
            }
        }

        //Draw Spike Pits on screen
        for (int k = 0; k < NUMBER_OF_SPIKEPITS; k++) {
            g.drawImage(spikepit[k].getSprite(), spikepit[k].getX(), spikepit[k].getY(), null);
        }
        for (int i = 0; i < NUMBER_OF_PLATFORMS; i++) {

            g.drawImage(Platform[i].getSprite(), Platform[i].getX(), Platform[i].getY(), null);

        }

        //Code to draw the score and health on screen
        Font uiFont = new Font("Arial", Font.PLAIN, 20);
        g.setColor(Color.black);
        g.setFont(uiFont);
        g.drawString("Score: " + score, camX, 20);
        g.drawString("Health " + health + "/100", camX, 42);
        g.dispose();
    }

    /**
     * This method will be called to check for collisions
     */
    public void checkCollisions() {
        Rectangle playerBounds = thePlayer.getBounds();
        Rectangle currentEmberBounds; //this variable will be updated with the bounds of each ember in a loop
        Rectangle currentEnemyBounds;
        Rectangle currentSpikePitBounds;
        InAir = 1;
        if (thePlayer.getY() > GroundLevel - thePlayer.getSpriteHeight()) {
            thePlayer.Land();
            thePlayer.setY(GroundLevel - thePlayer.getSpriteHeight());
            InAir = 2;

        }
        for (int i = 0; i < NUMBER_OF_PLATFORMS; i++) {
            if (thePlayer.getY() < Platform[i].getY() && thePlayer.getY() > (Platform[i].getY() - thePlayer.getSpriteHeight()) && thePlayer.getX() > Platform[i].getX() && thePlayer.getX() < (Platform[i].getX() + Platform[i].getSpriteWidth())) {
                thePlayer.Land();
                thePlayer.setY(Platform[i].getY() - thePlayer.getSpriteHeight());
                InAir = 2;
            }

        }
        if (thePlayer.getY() == GroundLevel - thePlayer.getSpriteHeight()) {
            InAir = 2;
        }

        for (int k = 0; k < NUMBER_OF_PLATFORMS; k++) {
            if (thePlayer.getY() == Platform[k].getY() - thePlayer.getSpriteHeight() && thePlayer.getX() > Platform[k].getX() && thePlayer.getX() < (Platform[k].getX() + Platform[k].getSpriteWidth())) {
                InAir = 2;
            }
        }

        if (InAir == 1) {
            thePlayer.falling();

        }
        // Check to see if the player boundary (rectangle) intersects
        // with the ember boundary (i.e. there is a collision)
        for (int i = 0; i < NUMBER_OF_EMBERS; i++) {
            if (embers[i].getVisible() == true) {
                currentEmberBounds = embers[i].getBounds();

                if (playerBounds.intersects(currentEmberBounds) == true) {
                    score += embers[i].getScore();
                    embers[i].setVisible(false);
                }
            }
        }

        if (CurrentCollisionDelay <= 0) {
            for (int j = 0; j < NUMBER_OF_ENEMIES; j++) {
                currentEnemyBounds = enemies[j].getBounds();

                if (enemies[j].getVisible() == true) {
                    if (playerBounds.intersects(currentEnemyBounds)) {
                        DamagePlayer(5);
                    }
                }

            }

            for (int k = 0; k < NUMBER_OF_SPIKEPITS; k++) {
                currentSpikePitBounds = spikepit[k].getBounds();

                if (spikepit[k].getVisible() == true) {
                    if (playerBounds.intersects(currentSpikePitBounds)) {
                        DamagePlayer(5);
                    }
                }
                CurrentCollisionDelay = MaxCollisionDelay;
            }
        }

        if (playerBounds.intersects(thePortal.getBounds())) {
            reset();
            game.SelectScreen(6);
        }

        for (int i = 0; i < NUMBER_OF_PLATFORMS; i++) {
            if (thePlayer.getX() > (Platform[i].getX() - thePlayer.getSpriteWidth()) && thePlayer.getX() < (Platform[i].getX() - thePlayer.getSpriteWidth()) + 32 && thePlayer.getY() > Platform[i].getY() && thePlayer.getY() < (Platform[i].getY() + Platform[i].getSpriteHeight())) {
                thePlayer.setX(Platform[i].getX() - thePlayer.getSpriteWidth());
            }
            if (thePlayer.getX() < (Platform[i].getX() + Platform[i].getSpriteWidth()) && thePlayer.getX() > (Platform[i].getX() + Platform[i].getSpriteWidth() - 32) && thePlayer.getY() > Platform[i].getY() && thePlayer.getY() < (Platform[i].getY() + Platform[i].getSpriteHeight())) {
                thePlayer.setX(Platform[i].getX() + Platform[i].getSpriteWidth());
            }
        }

        if (thePlayer.getX() < 1) {
            thePlayer.setX(1);
        }
        if (thePlayer.getX() > (3600 - thePlayer.getSpriteWidth())) {
            thePlayer.setX(3600 - thePlayer.getSpriteWidth());
        }

    }

    /**
     * This method calls the movement methods on characters and NPCs
     */
    public void DoMovement() {
        thePlayer.updateMove();

        for (int i = 0; i < NUMBER_OF_ENEMIES; i++) {
            enemies[i].move();
        }
    }

    /**
     * This method is called in response to the timer firing Every 10ms, this
     * method will update the state of the game in response to changes such as
     * key presses and to generate computer movement
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        /**
         * The repaint method starts the process of updating the screen -
         * calling /our version of the paintComponent method, which has the code
         * for drawing /our characters and objects
         */
        DoCameraMove();
        DoMovement();
        checkCollisions();
        //DoAnimate();
        repaint();
        if (CurrentCollisionDelay > 0) {
            CurrentCollisionDelay--;
        }
    }

    /**
     * This is a private KeyAdapter Class that we use to process keypresses it
     * assigns each keypress a value then sends it to the player class to be
     * translated into movement
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int move = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    move = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    move = 2;
                    break;
                case KeyEvent.VK_UP:
                    move = 3;
                    break;

                default:
                    break;
            }
            thePlayer.move(move);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int stop = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    stop = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    stop = 2;
                    break;

                default:
                    break;
            }
            //the key release for jump is not included here as it would have no effect
            thePlayer.stop(stop);
        }
    }

    private void DoCameraMove() {
        /**
         * this is where the calculations for camera movement are handled only x
         * is included here as level one does not need to move up or down so
         * including x would be redundant camX is set to the player's x value it
         * is then adjusted by how much you can see of the screen to centre it
         * on the player
         *
         *
         * the camX value is then checked to make sure that it is within the
         * bounds of the drawn level and adjusted if it is not
         */
        camX = thePlayer.getX() - VIEWPORT_SIZE_X / 2;
        if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        }

    }

    private void DamagePlayer(int Damage) {
        health -= Damage;
        if (health <= 0) {
            reset();
            game.SelectScreen(5);
        }

    }

    public void reset() {
        health = 100;
        thePlayer.setX(64);
        thePlayer.setY(GroundLevel);
        thePlayer.Land();
        thePlayer.setdX(0);
        score = 0;
        health = 100;

        Random rand = new Random();

        int emberX, emberY; // X and Y coordinates for the collectables
        int enemyX, enemyY; // X and Y coordinates for the enemies

        //Initialise all embers
        for (int i = 0; i < NUMBER_OF_EMBERS; i++) {
            emberX = rand.nextInt(3600) + (thePlayer.getX() + 20); // will ensure that the enemy cannot spawn under the player start position
            emberY = rand.nextInt(GroundLevel) + 1; // pick a random Y coorinate

            // Use the overloaded ember constructor to create a new
            // ember object with the X and Y coordinates selected
            // and a score value
            embers[i] = new Ember(emberX, emberY, 30);
        }

        // Initialise all Monster Objects
        for (int j = 0; j < NUMBER_OF_ENEMIES; j++) {
            enemies[j] = new Enemy(startline + (EnemyX[j] * blockspace), GroundLevel - (EnemyY[j] * blockspace) , EnemyDirection[j] , EnemyDistance[j]*blockspace);
        }

    }

    public void DoAnimate() {
        for (int i = 0; i < NUMBER_OF_EMBERS; i++) {
            embers[i].Animate();
        }
    }
}
