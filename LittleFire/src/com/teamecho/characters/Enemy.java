/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
    /**
     * start is used to store weather the object is moving towards end x and y
     * or start x and y, when 1 it is moving towards end and when 2 it is moving
     * towards start
     *
     */
    private int start = 1;
    // displacement from current x coordinate (i.e. how far the x coord has changed 
    // by user interaction
    //This image represents the enemy
    private BufferedImage sprite;

    //This variable stores the width of the image
    private int spriteWidth;
    //This variable stores the height of the image
    private int spriteHeight;

    //this stores the direction that the enemy was to to move in
    private int direction;
    //this is used to track wheather the object should be loaded and interacted with or not
    private boolean isVisible = true;

    /**
     * Default constructor that sets X and Y coordinates to 10
     *
     * @param newX
     * @param newY
     * @param Direction
     * @param Distance
     */
    public Enemy(int newX, int newY, String Direction, int Distance) {
        //this sets the Starting X and Y coordinates and sets the x and y co ordinates to them
        Startx = newX;
        Starty = newY;
        x = Startx;
        y = Starty;

        switch (Direction) {
            case "UP":
                //this alters the start value for the displacement
                dY -= 1;
                //this calculates the end value for the movement
                Endy = Starty - Distance;
                //this stores the set direction globaly for other functions
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
        initEnemy();
    }

    /**
     * This method is called to initialise the enemy
     */
    public void initEnemy() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Enemy_Images/Enemy.png"));
        } catch (Exception ex) {
            System.err.println("Error loading enemy sprite");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    //used to get the x value of the sprite to render it in the right location
    public int getX() {
        return x;
    }

    //used to get the y value of the sprite to render it in the right location
    public int getY() {
        return y;
    }

    /**
     * This method returns the set sprite so that it can be drawn into the game.
     *
     * @return
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    //used to track whether the ember should be rendered/interacted with
    public boolean getVisible() {
        return isVisible;
    }

    //used to get the area of the sprite for collision
    public Rectangle getBounds() {
        return new Rectangle(x, y, spriteWidth, spriteHeight);
    }

    public void move() {
        /**
         * this alters the x and y value of the enemy if start = 1 the enemy has
         * just spawned or has returned to the start x and y values
         *
         * if start =2 then the enemy has reached the end position and needs to
         * return to the start position
         */
        if (start == 1) {
            y += (dY * Movespeed);
            x += (dX * Movespeed);
        }
        //as the obect is reversing the displacement calculation is inverted
        if (start == 2) {
            y -= (dY * Movespeed);
            x -= (dX * Movespeed);
        }
        /**
         * Direction is set based on the direction that the object is told to go
         *
         * 1= up 2= down 3=left and 4=right
         *
         * when start is 1 it checks to see if the relevant x,y value has moved
         * passed the end point then sets start to 2 to make it move back
         * towards the start point
         *
         * when start is 2 it checks to see if the relevant x,y value has moved
         * passed the start point then sets start to 1 to make it move back
         * towards the end point
         */
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

}
