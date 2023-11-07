import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The sock is so boring time seems to slow down when it is touched
 * 
 * Name: @N Y 1/08/2022
 * 
 * Sprite sock.png - Created by N Y
 * 
 */
public class Sock extends Actor {
    private int points; // Declare points variable of type int
    private GreenfootImage fun; // Declare fun variable of GreenfootImage type

    /**
     * Constructor Sock: Contains initial values for Sock class object
     */
    public Sock() {
        setPoints(1); // Set point value for the sock at parameter (Parameters: X (Point value))
    }

    /** ACT CYCLE */
    /**
     * Method act - Methods & commands to be initiated per act cycle
     */
    public void act()
    {
        turn(1); // Turns the sock at a speed of 1 (Parameters: X (Speed value))
    }

    /** SCORING */
    /**
     * Method setPoints - Takes parameter newPoints and applies to points variable
     */
    public void setPoints(int newPoints)
    {
        points = newPoints; // Points takes on value of newPoints
    }

    /**
     * Method getPoints - Returns value of point variable
     */
    public int getPoints()
    {
        return points; // Returns value of points
    }
}

