/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teamecho.game.screens;

import com.teamecho.game.Game;
import com.teamecho.game.objects.MainMenuButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Calum Blair
 */
public class GameWinPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Game game; // this is a link back to the game controller class
    private BufferedImage backgroundImage = null;
    private final String SCREEN_TITLE = "Main Menu"; // sets screen title
    private MainMenuButton MainMenuButton;
    private int buttonX = 300;
    private int buttonY = 140;

    public GameWinPanel(Game theGame) {
        game = theGame;
        MainMenuButton = new MainMenuButton();
        initPanel();
    }

    private void initPanel() {

        //Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/Screens/WinScreen.png"));
        } catch (Exception ex) {
            System.err.println("Error Loading Win Game Image");
        }

        //Make sure the panle has GUI focus
        //So keypresses are registered to this panel
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //call the paintComponent method on the superclass to initialise drawing
        super.paintComponent(g);
        Font titleFont = new Font("Arial", Font.PLAIN, 22);

        //Start drawing -- the background goes first
        g.setFont(titleFont);
        g.drawString(SCREEN_TITLE, 20, 20);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(MainMenuButton.getSprite(), buttonX, buttonY, null);
        g.drawImage(MainMenuButton.getSprite(), buttonX, buttonY, null);
        Toolkit.getDefaultToolkit().sync(); // Ensure that the objects visual state is up to date
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //on mouse click checks to see if the mouse is within the area of the button
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getX() > buttonX && e.getX() < (buttonX + MainMenuButton.getSpriteWidth())&&e.getY() > buttonY && e.getY() < (buttonY + MainMenuButton.getSpriteHeight())) {
                 
                    
                    /**
                     * if the mouse is within the area a function is run within
                     * the Game class that sets the screen to the right one as
                     * this is for the starting the game this should send the
                     * player to level 1 
                     */
                    game.SelectScreen(1);
                    
                
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
            if (e.getX() > MainMenuButton.getX() && e.getX() < (buttonX + MainMenuButton.getSpriteWidth()) && e.getY() > buttonY && e.getY() < (buttonY + MainMenuButton.getSpriteHeight())) {
                    MainMenuButton.setImage(2);
                    
                
            }else{
                MainMenuButton.setImage(1);
                    
            }
            repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
}