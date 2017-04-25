/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author 1622542 Jeff Grant Created 18/4/17
 */
public class Enemy {

    private int x; // The enemy x coordinate
    private int y; // The enemy y coordinate
    private int Startx; // The enemy x coordinate
    private int Starty; // The enemy y coordinate
    private int Endx = 0; // The enemy x coordinate
    private int Endy = 0; // The enemy y coordinate
    private int dX; // The enemy x coordinate
    private int dY; // The enemy y coordinate
    private int Movespeed = 2; // The enemy y coordinate
    private int start = 1;
    // displacement from current x coordinate (i.e. how far the x coord has changed 
    // by user interaction
    //This image represents the enemy
    private BufferedImage sprite;

    //This variable stores the width of the image
    private int spriteWidth;
    //This variable stores the height of the image
    private int spriteHeight;

    private int damage = 5;
    private int direction;
    private boolean isVisible;

    /**
     * Default constructor that sets X and Y coordinates to 10
     *
     * @param newX
     * @param newY
     * @param Direction
     * @param Distance
     */
    public Enemy(int newX, int newY, String Direction, int Distance) {
        //Starting X and Y coordinates
        Startx = newX;
        Starty = newY;
        x = Startx;
        y = Starty;

        switch (Direction) {
            case "UP":
                dY -= 1;
                Endy = Starty - Distance;
                direction = 1;
                break;
            case "DOWN":
                dY = 1;
                Endy = Starty + Distance;
                direction = 2;
                break;
            case "LEFT":
                dX -= 1;
                Endx = Startx - Distance;
                direction = 3;
                break;
            case "RIGHT":
                dX = 1;
                Endx = Startx + Distance;
                direction = 4;
                break;
            default:
                System.out.println("error in enemy direction");
        }
        isVisible = true;
        initEnemy();
    }

    /**
     * This method is called to initialise the enemy
     */
    public void initEnemy() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Enemy_Images/EnemyImage1.png"));
        } catch (Exception ex) {
            System.err.println("Error loading enemy sprite");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * This method returns the set sprite so that it can be drawn into the game.
     *
     * @return
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    public void move() {

        if (start == 1) {
            y += (dY * Movespeed);
            x += (dX * Movespeed);
        }
        if (start == 2) {
            y -= (dY * Movespeed);
            x -= (dX * Movespeed);
        }
        switch (direction) {
            case 1:
                if (start == 1) {
                    if (y < Endy) {
                        start = 2;
                    }
                }
                if (start == 2) {
                    if (y > Starty) {
                        start = 1;
                    }
                }
                break;
            case 2:
                if (start == 1) {
                    if (y > Endy) {
                        start = 2;
                    }
                }
                if (start == 2) {
                    if (y < Starty) {
                        start = 1;
                    }
                }
                break;
            case 3:
                if (start == 1) {
                    if (x < Endx) {
                        start = 2;
                    }
                }
                if (start == 2) {
                    if (x > Startx) {
                        start = 1;
                    }
                }
                break;
            case 4:
                if (start == 1) {
                    if (x > Endx) {
                        start = 2;
                    }
                }
                if (start == 2) {
                    if (x < Startx) {
                        start = 1;
                    }
                }
                break;

            default:
        }

    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, spriteWidth, spriteHeight);
    }

}
