import greenfoot.*; // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The humble university student studying from their bedroom, gets distracted easily by thoughts of
 * binging Anime, playing video games and not touching grass. Has a collection of ugly socks that
 * radiate boredom. Name: @N Y 1/08/2022
 * 
 * Spites - Cute Girl (https://www.gameart2d.com/cute-girl-free-sprites.html)
 * 
 * Sounds - pickup.mp3 - N Y
 */

public class Student extends Actor {
    // Walking Animation
    private GreenfootImage walk1; // Declare walk1 of type GreenfootImage
    private GreenfootImage walk2; // Declare walk2 of type GreenfootImage

    /**
     * Constructor Student - Contains initial values for Student class object
     */
    public Student() {
        walk1 = new GreenfootImage("walk1.png"); // Initialize walk1 as new GreenfootImage
                                                 // "walk1.png"
        walk2 = new GreenfootImage("walk2.png"); // Initialize walk1 as new GreenfootImage
                                                 // "walk2.png"
    }

    /** ACT CYCLE */
    /**
     * Method act - Methods & commands to be initiated per act cycle
     */
    public void act()
    {
        checkKeyPress(5); // Moves the student using "left", "right", "up" and "down", speed
                          // parameter dictates how fast the movement speed will be
        runAnimation(); // Run animation, cycles between walk1 and walk2
        checkSock(); // Checks for socks, collects them if intersecting occurs
    }

    /** MOVEMENT */
    /**
     * Method checkKeyPress - Moves the player up down left or right when the respective keys are
     * pressed Code Learned from Module 4-2: Moving and Removing Objects
     */
    public void checkKeyPress(int speed)
    {
        if (Greenfoot.isKeyDown("up"))// Holding 'Up' Key
        {
            setLocation(getX(), getY() - speed); // Subtract 5 from Y
        }
        if (Greenfoot.isKeyDown("down"))// Holding 'Down' Key
        {
            setLocation(getX(), getY() + speed); // Add 5 to Y
        }
        if (Greenfoot.isKeyDown("left"))// Holding 'right' Key
        {
            setLocation(getX() - speed, getY()); // Add 5 to X
        }
        if (Greenfoot.isKeyDown("right"))// Holding 'right' Key
        {
            setLocation(getX() + speed, getY()); // Add 5 to X
        }
    }

    /** GAME MECHANICS */
    /**
     * Method checkSocks - causes socks to be "collected" when touched by the student, increasing
     * score by points value specified in Sock object class (Learned from Module 4-3: Abtraction and
     * encapsulation)
     */
    private void checkSock()
    {
        Sock sock = (Sock) getOneIntersectingObject(Sock.class); // Declare sock as object of type
                                                                 // Sock with parameter of method
                                                                 // getOneIntersectingObject(Sock.class)
        if (sock != null) // if sock is NOT null (If sock is intersecting with the object containing
                          // this code)
        {
            BedroomWorld bedroomWorld = (BedroomWorld) getWorld(); // Declare bedroomWorld of type
                                                                   // BedroomWorld and intialize as
                                                                   // BedroomWorld with command
                                                                   // getWorld
            bedroomWorld.addScore(sock.getPoints()); // Calls bedroomWorld method addscore with sock
                                                     // method getpoints as its parameter to add
                                                     // point value of sock current score
            bedroomWorld.removeObject(sock); // Removes sock object if intersected
            Greenfoot.playSound("pickup.mp3"); // Plays pickup sound
        }
    }

    /** ANIMATION */
    /**
     * Method runAnimation - Cycles between walk1 and walk2 sprites (Learned from SCU Explorer 5)
     */
    public void runAnimation()
    {
        if (getImage() == walk1) // If image is walk1
        {
            setImage(walk2); // Set image to walk2
        }
        else // Else
        {
            setImage(walk1); // Set image to walk1
        }
    }
}


