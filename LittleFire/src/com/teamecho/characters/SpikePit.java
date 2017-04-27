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
 * This class is a representation of a spike pit the player must avoid in the
 * game
 *
 * @author 1622542 Created by Jeff Grant 18/04/17
 */
public class SpikePit {

    private int x; // The spikepit x coordinate
    private int y; // The spikepit y coordinate
    private int spriteWidth; //This variable stores the width of the image
    private int spriteHeight; //This variable stores the height of the image

    //this is used to track wheather the object should be loaded and interacted with or not
    private boolean isVisible = true;

    //This image represents the spike pit
    private BufferedImage sprite;

    public SpikePit(int newX, int newY) {
        //Starting X and Y coordinates
        x = newX;
        y = newY;
        //loads an image and sets the sprite to it
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Enemy_Images/SpikePit.png"));
        } catch (Exception ex) {
            System.err.println("Error loading SpikePit image");
        }

        //this calculates the current dimensions of  the set image
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

}
