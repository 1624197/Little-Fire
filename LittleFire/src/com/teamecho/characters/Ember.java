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

    private int score;

    /**
     * The default constructor that initialises values to suitable defaults
     */
    public Ember(int newX, int newY, int newScore) {
        x = newX;
        y = newY;
        score = newScore;

        isVisible = true;

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Ember/Ember1.png"));
        } catch (Exception ex) {
            System.err.println("Error loading ember sprite");
        }

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
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember1.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember1 sprite");
                    }
                    ImagePosition = 2;
                    break;
                case 2:
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember2.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember2 sprite");
                    }
                    ImagePosition = 3;
                    break;
                    case 3:
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember3.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember3 sprite");
                    }
                    ImagePosition = 4;
                    break;
                    case 4:
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember4.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember4 sprite");
                    }
                    ImagePosition = 5;
                    break;
                    case 5:
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember5.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember5 sprite");
                    }
                    ImagePosition = 1;
                    break;
                default:
                    try {
                        sprite = ImageIO.read(getClass().getResource("/Images/Ember/Ember1.png"));
                    } catch (Exception ex) {
                        System.err.println("Error loading ember1 sprite");
                    }
                    ImagePosition = 2;

            }
            AnimationDelay = 0;
        }
    }
}
