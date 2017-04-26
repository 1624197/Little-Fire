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
 * This class is a static representation of an ember that the player can collect
 * to increase score and charge up a powerful attack
 *
 * Created by Jeff Grant 01/03/17
 *
 * @author 1622542
 */
public class Ember {

    private int x;
    private int y;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    private int ImagePosition = 1;
    private int AnimationDelay = 0;
    private int AnimationDelayMax = 10;
    private BufferedImage sprite;
    private BufferedImage[] Animation;
    private int framecount=5;
    private int score;

    /**
     * The default constructor that initialises values to suitable defaults
     */
    public Ember(int newX, int newY, int newScore) {
        x = newX;
        y = newY;
        score = newScore;
        Animation = new BufferedImage[5];
        isVisible = true;
        for (int i = 0; i < framecount; i++) {
            try {
                Animation[i] = ImageIO.read(getClass().getResourceAsStream("/Images/Ember/Ember" + String.valueOf(i+1) + ".png"));
            } catch (Exception ex) {
                System.err.println("Error loading ember sprite");
            }

        }
        sprite = Animation[0];
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle(x, y, spriteWidth, spriteHeight);
        return objectRect;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getX() {
        return x;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getY() {
        return y;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public int getScore() {
        return score;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public void Animate() {
        AnimationDelay++;
        if (AnimationDelay == AnimationDelayMax) {
            switch (ImagePosition) {
                case 1:
                    sprite = Animation[0];
                    ImagePosition = 2;
                    break;
                case 2:
                    sprite = Animation[1];
                    ImagePosition = 3;
                    break;
                case 3:
                    sprite = Animation[2];
                    ImagePosition = 4;
                    break;
                case 4:
                    sprite = Animation[3];
                    ImagePosition = 5;
                    break;
                case 5:
                    sprite = Animation[4];
                    ImagePosition = 1;
                    break;
                default:
                    sprite = Animation[0];
                    ImagePosition = 2;

            }
            AnimationDelay = 0;
        }
    }
}
