import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A living, breathing embodiment of fun. Contains many distracting goodies inside. Vaguely
 * reminiscant of the monster from Ski-Free in its lazily drawn appearance. Threateningly brandishes
 * an original 1991 era Super Pretendo controller for some reason. Chases after the student to
 * distract them from their important university obligations
 *
 * Name: @N Y @1/08/2022
 * 
 * Sprites: fun.png - created by N Y
 * 
 * Sounds: deathsound.mp3 - Created by N Y
 *
 * Notes: Command used to pass coordinates for player to enemy learned from:
 * https://www.greenfoot.org/topics/8561/0
 *
 */
public class Fun extends Actor {
    private GreenfootImage fun; // Declare fun variable of GreenfootImage type

    /**
     * Constructor Student - Contains initial values for Fun class object
     */
    public Fun() {
        fun = new GreenfootImage("fun.png"); // Initialize fun as new GreenfootImage "fun.png"
        setImage(fun); // Set image to fun.png
    }

    /** ACT CYCLE */
    /**
     * Method act - Methods & commands to be initiated per act cycle
     */
    public void act()
    {
        movement(5, 1); // Enemy will alternate between maximum and minimum movement speeds
                        // (Parameters: X (Maximum Speed), Y Minimum Speed)
        checkEdge(10); // Enemy will bounce off edge of screen toward student (Parameters: X (Bounce
                       // Velocity)
        randomTurn(5, 91, -45); // Enemy will randomly turn a percentage of the time (Parameters: X
                                // (Percentage), Y (Maximum Angle), Z (Minimum Angle)
        dashToStudent(1, 10); // Enemy will randomly dash toward the student a percentage of the
                              // time (Parameters: X (Percentage), Y (Speed) )
        lookForStudent(); // If Enemy catches the student, level will reset and student will lose a
                          // focus point
    }

    /** MOVEMENT */
    /**
     * Method movement - Enemy will move a random number between max and min int variables
     */
    public void movement(int max, int min)
    {
        move(Greenfoot.getRandomNumber(max) + min); // Move a number between max and min
    }

    /**
     * dashToStudent - Enemy will dash in the students direction every p out of 100 act cycles (p =
     * percentage)
     */
    private void dashToStudent(int p, int speed)
    {
        Student player = (Student) getWorld().getObjects(Student.class).get(0); // Declares and
                                                                                // Initializes
                                                                                // player class, to
                                                                                // reference
                                                                                // Student,
                                                                                // encapsulates
                                                                                // command
                                                                                // getObjects of
                                                                                // student.class
                                                                                // type, first
                                                                                // instance
        if (Greenfoot.getRandomNumber(101) < p) // If random number is less than p (with a maximum
                                                // of 100)
        {
            turnTowards(player.getX(), player.getY());// Turn toward player's current X and Y
                                                      // coordinate
            move(speed); // Move pixels equivalent to speed value
        }
    }

    /**
     * randomTurn - Enemy will randomly turn every p out of 100 act cycles (p = percentage) with a
     * range of max and min
     */
    public void randomTurn(int p, int max, int min)
    {
        if (Greenfoot.getRandomNumber(101) < p) // If random number is less than pp
        {
            randomAngle(max, min); // Turn a random angle between max and min (Parameters: X
                                   // (Percentage), Y (Maximum Angle), Z (Minimum Angle)
        }
    }

    /**
     * randomAngle - Turns a random angle between max and min degrees
     */
    public void randomAngle(int max, int min)
    {
        turn(Greenfoot.getRandomNumber(max) - min); // Turns an angle between max and min values
    }

    /**
     * checkEdge - Enemy will turn away from the edge and dash toward the Student to the integer
     * value of speed
     */
    public void checkEdge(int speed)
    {
        // Declares and Initializes player class, to reference Student, encapsulates command
        // getWorld and uses dot notation to use the World class getObjects command, to set the
        // first instance of the student.class object as its parameter
        Student player = (Student) getWorld().getObjects(Student.class).get(0);
        if (isAtEdge()) // If enemy is at edge
        {
            turnTowards(player.getX(), player.getY()); // Turn toward player's current X and Y
                                                       // coordinate
            move(speed); // Move pixels equivalent to speed value
        }
    }

    /** GAME MECHANICS */
    /**
     * lookForStudent - Enemy will cause student to lose a life if collision is detected
     */
    private void lookForStudent()
    {
        if (isTouching(Student.class)) // If this object is touching an object of student.class
        {
            BedroomWorld bedroomWorld = (BedroomWorld) getWorld(); // Create bedroomWorld variable
                                                                   // of type BedroomWorld with
                                                                   // parameter method getWorld to
                                                                   // return current world as its
                                                                   // paramete
            bedroomWorld.setLives(bedroomWorld.getLives() - 1); // Use bedroomWorld command setLives
                                                                // with parameter of bedroomWorld
                                                                // method getLives to return current
                                                                // lives and subtract by 1
            bedroomWorld.nextLevel(); // Use bedroomWorld command nextLevel to reset the level
            Greenfoot.playSound("ow.mp3"); // Play deathSound
        }
    }
}


