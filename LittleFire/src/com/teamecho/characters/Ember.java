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

    // Tis is the current x and y position
    private int x;
    private int y;
    //this hild the dimension of the currently loaded sprite
    private int spriteWidth;
    private int spriteHeight;
    //this is used to track wheather the object should be loaded and interacted with or not
    private boolean isVisible = true;
    //this is used to track the next position in the animation cycle
    private int ImagePosition = 1;
    //this is used to delay the changing of the sprite in the animation
    private int CurrentAnimationDelay = 0;
    //this is used to store the amount of frames that it takes for the ember sprite to change
    private int AnimationDelayMax = 10;
    //this holds the current image for the object
    private BufferedImage sprite;
    //this is used to store all images in an easily accessable way
    private BufferedImage[] Animation;
    //this is used to hold how many animation frames there are
    private int framecount = 5;
    //this holds the score value of the ember
    private int score;

    /**
     * The default constructor that initialises values to suitable defaults it
     * takes in the desired co-ordinates and desired score value
     *
     * @param newX
     * @param newY
     * @param newScore
     */
    public Ember(int newX, int newY, int newScore) {
        //this sets the x,y and score values
        x = newX;
        y = newY;
        score = newScore;
        //this creates an array of buffred images wit the same size as the number of animation frames
        Animation = new BufferedImage[framecount];
        /**
         * sets the array of buffered images to its own individual image
         */
        for (int i = 0; i < framecount; i++) {
            try {
                /**
                 * i+1 is used as the value for the images starts at 1 and goes
                 * to 5, an array is used so the all images onlY need to be
                 * loaded once
                 *
                 */
                Animation[i] = ImageIO.read(getClass().getResourceAsStream("/Images/Ember/Ember" + String.valueOf(i + 1) + ".png"));
            } catch (Exception ex) {
                System.err.println("Error loading ember sprite");
            }

        }
        //sets the default value for the sprite
        sprite = Animation[0];
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

    //used to givethe score value when colided with
    public int getScore() {
        return score;
    }

    //this return the current image for sprite
    public BufferedImage getSprite() {
        return sprite;
    }

    //used to track whether the ember should be rendered/interacted with
    public boolean getVisible() {
        return isVisible;
    }

    //used to get the area of the sprite for collision
    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle(x, y, spriteWidth, spriteHeight);
        return objectRect;
    }

    //these are used to set whether the induvidual ember is to be rendered/ interacted with
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void Animate() {
        //every time this function is called the current animation delay is incremented
        CurrentAnimationDelay++;
        //when the current delay is equal to max the current image value for sprite is changed
        if (CurrentAnimationDelay == AnimationDelayMax) {
            //Image position is used to select the next Image to load
            switch (ImagePosition) {
                case 1:
                    //the image for sprite is  set to the appropriate value
                    sprite = Animation[0];
                    //Image position is then set to the next Image to be loaded
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
            //it is then reset the delay to zero
            CurrentAnimationDelay = 0;
        }
    }
}
