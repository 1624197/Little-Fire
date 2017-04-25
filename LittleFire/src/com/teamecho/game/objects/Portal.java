/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game.objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author 1624197
 */
public class Portal {
    private int x;
    private int y;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    private BufferedImage sprite;
    
    private int score;
    
    
    /**
     * The default constructor that initialises values to suitable defaults
     */
    
    public Portal(int newX, int newY)
    {
        x = newX;
        y = newY;
        
        try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Objects/GameObjects/Portal.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading portal sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight(); 
    }
    
    public BufferedImage getSprite()
    {
        return sprite;
    }
        
    public Rectangle getBounds()
    {
        Rectangle objectRect = new Rectangle(x, y, spriteWidth, spriteHeight);
        return objectRect;
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
}
