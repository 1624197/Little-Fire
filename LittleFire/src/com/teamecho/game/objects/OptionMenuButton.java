package com.teamecho.game.objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *Created by Calum Blair  23/04/2017
 * @author 1624197
 */
public class OptionMenuButton {

    public OptionMenuButton() {
        //Starting X and Y coordinates
        x = 100;
        y = 400;
        initButton();
    }

    private BufferedImage sprite;

    //This variable stores the width of the image
    private int spriteWidth;
    //This variable stores the height of the image
    private int spriteHeight;
    private int x;
    private int y;

    public void initButton() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Objects/OptionMenuButton.png"));
        } catch (Exception ex) {
            System.err.println("Error loading player sprite");
        }
        //getSpriteWidth and getSpriteHeight are used to let other classes access the 
        //width and height of the button

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    //this is used to forcefully set the x value  from other classes
    public void setX(int newX) {
        x = newX;
    }

    //this is used to get the X value of the player character from other clasess
    public int getX() {
        return x;
    }

    //this is used to forcefully set the y value  from other classes
    public void setY(int newY) {
        y = newY;
    }

    //this is used to get the Y value of the player character from other clasess
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, spriteWidth, spriteHeight);
    }
}
