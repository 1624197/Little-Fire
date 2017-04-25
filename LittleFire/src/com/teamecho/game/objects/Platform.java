/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game.objects;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Calum Blair
 */
public class Platform {

    private int x;
    private int y;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;

    private BufferedImage sprite;

    public Platform(int newX, int newY) {
        x = newX;
        y = newY;
        isVisible = true;

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Objects/Platform.png"));
        } catch (Exception ex) {
            System.err.println("Error loading platform sprite");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }
    
    
    
    public BufferedImage getSprite()
    {
        return sprite;
    }
    
    public void setX(int newX)
    {
        x = newX;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setY(int newY)
    {
        y = newY;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
    
    public boolean getVisible()
    {
        return isVisible;
    }
    
    public int getSpriteHeight() 
    {
        return spriteHeight;
    }
    public int getSpriteWidth() 
    {
        return spriteWidth;
    }
}
