/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game.screens;

/**
 * Created by Jeff Grant 22/02/17
 *
 * @author 1622542
 */
import com.teamecho.game.Game;
import com.teamecho.game.objects.TutorialButton;
import com.teamecho.game.objects.PlayButton;
import com.teamecho.game.objects.CreditsButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class StartGamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private Game game; // this is a link back to the game controller class
    private BufferedImage backgroundImage = null;
    private final String SCREEN_TITLE = "Main Menu"; // sets screen title
    private PlayButton StartButton;
    private TutorialButton TutorialButton;
    private CreditsButton CreditsButton;

    public StartGamePanel(Game theGame) {
        game = theGame;
        StartButton = new PlayButton();
        TutorialButton = new TutorialButton();
        CreditsButton = new CreditsButton();
        initPanel();
    }

    private void initPanel() {

        //Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/Screens/MainMenuScreen.png"));
        } catch (Exception ex) {
            System.err.println("Error Loading Start Game Image");
        }

        //Make sure the panle has GUI focus
        //So keypresses are registered to this panel
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        //call the paintComponent method on the superclass to initialise drawing
        super.paintComponent(g);
        Font titleFont = new Font("Arial", Font.PLAIN, 22);

        //Start drawing -- the background goes first
        g.setFont(titleFont);
        g.drawString(SCREEN_TITLE, 20, 20);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(StartButton.getSprite(), StartButton.getX(), StartButton.getY(), null);
        g.drawImage(TutorialButton.getSprite(), TutorialButton.getX(), TutorialButton.getY(), null);
        g.drawImage(CreditsButton.getSprite(), CreditsButton.getX(), CreditsButton.getY(), null);
        Toolkit.getDefaultToolkit().sync(); // Ensure that the objects visual state is up to date
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //on mouse click checks to see if the mouse is within the area of the button
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getX() > StartButton.getX() && e.getX() < (StartButton.getX() + StartButton.getSpriteWidth()) && e.getY() > StartButton.getY() && e.getY() < (StartButton.getY() + StartButton.getSpriteHeight())) {

                /**
                 * if the mouse is within the area a function is run within the
                 * Game class that sets the screen to the right one as this is
                 * for the starting the game this should send the player to
                 * level 1
                 */
                game.SelectScreen(3);

            }
            if (e.getX() > TutorialButton.getX() && e.getX() < (TutorialButton.getX() + TutorialButton.getSpriteWidth())&&e.getY() > TutorialButton.getY() && e.getY() < (TutorialButton.getY() + TutorialButton.getSpriteHeight())) {
                 
                    /**
                     * if the mouse is within the area a function is run within
                     * the Game class that sets the screen to the right one as
                     * this is for the options menu the player should see the
                     * options menu
                     */
                    game.SelectScreen(2);
                
            }
            if (e.getX() > CreditsButton.getX() && e.getX() < (CreditsButton.getX() + CreditsButton.getSpriteWidth())&&e.getY() > CreditsButton.getY() && e.getY() < (CreditsButton.getY() + CreditsButton.getSpriteHeight())) {
                 
                    /*
                     * if the mouse is within the area a function is run within
                     * the Game class that sets the screen to the right one as
                     * this is for the options menu the player should see the
                     * options menu
                     */
                    game.SelectScreen(6);
                
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() > StartButton.getX() && e.getX() < (StartButton.getX() + StartButton.getSpriteWidth()) && e.getY() > StartButton.getY() && e.getY() < (StartButton.getY() + StartButton.getSpriteHeight())) {
                StartButton.setImage(2);

            
        } else {
            StartButton.setImage(1);

        }
        if (e.getX() > TutorialButton.getX() && e.getX() < (TutorialButton.getX() + TutorialButton.getSpriteWidth())&&e.getY() > TutorialButton.getY() && e.getY() < (TutorialButton.getY() + TutorialButton.getSpriteHeight())) {
                TutorialButton.setImage(2);

            
        } else {
            TutorialButton.setImage(1);

        }
        if (e.getX() > CreditsButton.getX() && e.getX() < (CreditsButton.getX() + CreditsButton.getSpriteWidth())&&e.getY() > CreditsButton.getY() && e.getY() < (CreditsButton.getY() + CreditsButton.getSpriteHeight())) {
                CreditsButton.setImage(2);
            
        } else {
            CreditsButton.setImage(1);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
