import greenfoot.*; // imports Actor, World, Greenfoot, GreenfootImage

/**
 * Synopsis: The University student finds that they have procrastinated too much and are now on a
 * time crunch for their assignments. Luckily for them time seems to move slower when they're bored!
 * The student must collect the boring socks around their room so they have more time to reflect on
 * their assignment and get a high distinction! Be careful though, the physical embodiment of fun
 * seeks to distract the university student. If this embodiment catches the student, they will lose
 * 1 focus point! If they lose all 3, time will fly by and they will fail their assignment.
 *
 * Name: @N Y @1/08/2022
 * 
 * Win Conditions: Standard Mode: Collect 100 socks (Game will end once 100 socks are collected)
 * Endless Mode: Collect at least 100 socks before losing all focus points
 * 
 * Lose Conditions: Lose all 3 focus points before collecting the amount of socks specified in the
 * win criteria
 * 
 * Sounds: deathsound.mp3 - Created by N Y gamelost.mp3 - Created by N Y gamewon.mp3 - Created by N
 * Y
 * 
 * Notes - All get/set/show methods were learned from Module 4 - Most methods used were a mixture of
 * self-experimentation using methods learned in modules throughout the course
 */

public class BedroomWorld extends World {
    // HUD Element Declaration
    private int score; // Declare score int variable
    private int time; // Declare time int variable
    private int level; // Declare level int variable
    private int lives; // Declare lives int variable
    private int winCriteria;// Declare winCritera int variable
    private int goal; // Declare score goal (Randomized depending on the level)

    // Object Declaration
    public Student player = new Student(); // Declare player Student variable and initialize as new
                                           // Student object type
    private Fun fun = new Fun(); // Declare enemy fun and initialize as new Fun object type
    private Fun fun1 = new Fun(); // Declare enemy fun2 and initialize as new Fun object type
    private Fun fun2 = new Fun(); // Declare enemy fun3 and initialize as new Fun object type
    private Fun fun3 = new Fun(); // Declare enemy fun4 and initialize as new Fun object type
    private Fun fun4 = new Fun(); // Declare enemy fun5 and initialize as new Fun object type

    // Endless Mode (Change to true to activate endless mode)
    private boolean endlessMode = false;

    /**
     * Constructor BedRoomWorld - Contains initial values for BedRoomWorld class object World is
     * 800x600 cells, where every cell is just 1 pixel. Background created by Natalie Young (Gimp
     * 2.10.18)
     */
    public BedroomWorld() {
        // Initialization of World
        super(800, 600, 1); // Background set to 800 by 600 with each tile being 1 pixel
        setBackground("background.png"); // Sets background image
        setPaintOrder(Student.class, Fun.class, Sock.class); // Sets appearance order of each object
                                                             // Student > Fun > Sock

        // Initialization of HUD Elements
        setLevel(1); // Sets Level (Parameters: X (Level)
        setLives(3); // Sets Lives (Focus) (Parameters: X (Lives)
        setScore(0); // Sets Score (Learned in SCU Module 4) (Parameters: X (Score)
        setWinCriteria(100); // Sets Win Criteria (Parameters: X (winCriteria)

        // Preparation of world
        prepare(); // Adds player object, centers player and runs nextLevel command
    }

    /** ACT CYCLE */
    /**
     * Method act - Methods & commands to be initiated per act cycle
     */
    public void act()
    {
        showHud(); // Shows HUD elements (Score, Time, Level, Goal)
        countTime(); // Counts down the timer by 1 every act cycle
        checkGameOver();// Ends the game if criteria met (Win or Lose)
        randomSocks(1); // Randomly generates socks, parameter indicates chance per act cycle (3 =
                        // 3%)
    }

    /** PREPARATION */
    /**
     * Method prepare - Command is run once at the start, adds player object to game at coordinates
     * 400,300. Runs nextLevel() to prepare scoring elements
     */
    public void prepare()
    {
        addObject(player, 400, 300); // Adds the player to the world
        nextLevel(); // Resets the level, increasing the level counter
    }

    /**
     * nextLevel - Prepares the level once goal from previous level is met
     */
    public void nextLevel()
    {
        removeEnemy(); // Removes enemies from previous level
        player.setLocation(400, 300); // Sets player to centre of the level
        setTime(1000); // Resets Time to parameter value (Learned in SCU module 4)
        prepareGoal(); // Prepares goal for the level
        prepareSocks(); // Randomly places socks at the beginning of the level
        prepareEnemy(); // Removes previous enemies and randomly places enemies at the beginning of
                        // the level
    }

    /**
     * Method prepareGoal - Prepares the goal for the level (Goal is how many socks are required to
     * progress to next level) Goal will be: A random number between 1 and the current level + A
     * random number between 1 and 5
     */
    private void prepareGoal()
    {
        goal = Greenfoot.getRandomNumber(getLevel()) + Greenfoot.getRandomNumber(6) + 1; // Sets
                                                                                         // goal to
                                                                                         // a random
                                                                                         // number
                                                                                         // between
                                                                                         // 0 and
                                                                                         // the
                                                                                         // current
                                                                                         // level +
                                                                                         // a
                                                                                         // minimum
                                                                                         // of 1, up
                                                                                         // to 5
    }

    /**
     * Method prepareSocks - Prepares the socks randomly at the beginning of the level (between 4
     * and 11)
     */
    private void prepareSocks()
    {
        for (int i = 0; i < Greenfoot.getRandomNumber(11) + 4; i++) // For i = 0 until i is a random
                                                                    // number between 0 and 11 + 4,
                                                                    // add a counter to i (Learned
                                                                    // in previous programming
                                                                    // studies in C)
        {
            addObject(new Sock(), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600)); // Add
                                                                                                   // new
                                                                                                   // sock
                                                                                                   // Object
                                                                                                   // at
                                                                                                   // random
                                                                                                   // location
        }
    }

    /**
     * Method prepareEnemy - Prepares enemies randomly for the level randomly between 0 and current
     * level / 2 (Will not produce more than 5 enemies) Enemies will spawn away from the centre of
     * the map
     */
    private void prepareEnemy()
    {
        // num is randomized based on Level Variable
        int num = Greenfoot.getRandomNumber(getLevel());

        // If statements control what enemies spawn
        if (num >= 2) // If num is equal or greater than 2
        {
            addObject(fun, Greenfoot.getRandomNumber(800) + 200,
                    Greenfoot.getRandomNumber(600 + 100)); // Spawn enemy randomly (200 pixels away
                                                           // from center)
        }
        if (num >= 4) // If num is equal or greater than 4
        {
            addObject(fun1, Greenfoot.getRandomNumber(800) + 200,
                    Greenfoot.getRandomNumber(600 + 200)); // Spawn enemy randomly (200 pixels away
                                                           // from center)
        }
        if (num >= 6) // If num is equal or greater than 6
        {
            addObject(fun2, Greenfoot.getRandomNumber(800) + 200,
                    Greenfoot.getRandomNumber(600 + 200)); // Spawn enemy randomly (200 pixels away
                                                           // from center)
        }
        if (num >= 8) // If num is equal or greater than 8
        {
            addObject(fun3, Greenfoot.getRandomNumber(800) + 200,
                    Greenfoot.getRandomNumber(600 + 200)); // Spawn enemy randomly (200 pixels away
                                                           // from center)
        }
        if (num >= 10) // If num is equal or greater than 10
        {
            addObject(fun4, Greenfoot.getRandomNumber(800) + 200,
                    Greenfoot.getRandomNumber(600 + 200)); // Spawn enemy randomly (200 pixels away
                                                           // from center)
        }
    }

    /**
     * Method removeEnemy - Removes enemies
     */
    private void removeEnemy()
    {
        // Removes all enemy objects from level
        removeObject(fun);
        removeObject(fun1);
        removeObject(fun2);
        removeObject(fun3);
        removeObject(fun4);
    }

    /** HUD ELEMENTS */
    /**
     * Method showHud - Shows all HUD elements - Score/Level/Goal/Lives/Timer/winCriteria (Learned
     * in SCU Module 4))
     */
    private void showHud()
    {
        showScore(); // Shows total score at the top left of the screen
        showLevel(); // Shows level at the bottom left of the screen
        showLives(); // Shows lives at the bottom right of the screen
        showTime(); // Shows timer at the top right of the screen
        showGoal(); // Shows socks remaining to collect
        showWinCriteria(); // Shows criteria to win
    }

    /** SCORE */
    /**
     * Method showScore - Displays Total Score (Top Left), uses method getScore to get current score
     */
    private void showScore()
    {
        showText("Total Socks: " + getScore(), 75, 25); // Displays Score: (Value of getScore)
    }

    /**
     * Method getScore - Gets current value of int score and returns it
     */
    public int getScore()
    {
        return score; // Returns score int variable
    }

    /**
     * Method setScore - Takes parameter newScore and applies to score variable
     */
    public void setScore(int newScore)
    {
        score = newScore; // Applies value of newScore to score int variable
    }

    /** LEVEL */
    /**
     * Method showLevel - Shows current level (bottom-right), uses method getLevel to get current
     * level
     */
    private void showLevel()
    {
        showText("Level: " + getLevel(), 740, 570); // Displays Level: (Value of getLevel)
    }

    /**
     * Method getLevel - Gets current value of int level and returns it
     */
    public int getLevel()
    {
        return level; // Returns level int variable
    }

    /**
     * Method setLevel - Takes parameter newLevel and applies to level variable
     */
    public void setLevel(int newLevel)
    {
        level = newLevel; // Applies value of newLevel to level int variable
    }

    /** GOAL */
    /**
     * Method showGoal - Shows Goal (top-middle), uses method getGoal to get current goal
     */
    private void showGoal()
    {
        showText("Socks to collect: " + getGoal(), 400, 25); // Displays Goal: (Value of getGoal)
    }

    /**
     * Method getGoal - Gets current value of int goal and returns it
     */
    public int getGoal()
    {
        return goal; // Returns goal int variable
    }

    /**
     * Method setGoal - Takes parameter newGoal and applies to goal variable
     */
    public void setGoal(int newGoal)
    {
        goal = newGoal; // Applies value of newGoal to goal int variable
    }

    /** WIN CRITERIA */
    /**
     * Method showWinCriteria - Displays win criteria (Bottom Middle), uses method getWinCriteria to
     * get current goal
     */
    private void showWinCriteria()
    {
        showText("Collect " + getWinCriteria() + " socks to win!", 400, 570); // Displays criteria
                                                                              // to win game
    }

    /**
     * Method getWinCriteria - Gets current value of int winCriteria and returns it
     */
    public int getWinCriteria()
    {
        return winCriteria; // Returns winCriteria int variable
    }

    /**
     * Method setWinCriteria - Takes parameter newWinCriteria and applies to winCriteria variable
     */
    public void setWinCriteria(int newWinCriteria)
    {
        winCriteria = newWinCriteria; // Applies value of newWinCriteria to winCriteria int variable
    }

    /** LIVES */
    /**
     * Method showLives - Shows lives (bottom-right), uses getLives to get current life count
     */
    private void showLives()
    {
        showText("Focus: " + getLives(), 50, 570); // Displays Lives: (Value of getLives)
    }

    /**
     * Method getLives - returns current value of lives int variable
     */
    public int getLives()
    {
        return lives; // Returns lives int variable
    }

    /**
     * Method setLives - Takes parameter newLives and applies to lives variable
     */
    public void setLives(int newLives)
    {
        lives = newLives; // Applies value of newLives to lives int variable
    }

    /** TIME */
    /**
     * Method showTime - Shows timer (top-right), uses method getTime to get current time
     */
    private void showTime()
    {
        showText("Time: " + getTime(), 740, 25); // Displays Time: (Value of getTime)
    }

    /**
     * Method getTime - returns current value of time int variable
     */
    public int getTime()
    {
        return time; // Returns time int variable
    }

    /**
     * Method setTime - Takes parameter newTime and applies to time variable
     */
    public void setTime(int newTime)
    {
        time = newTime; // Applies value of newTime to time int variable
    }

    /** TIMER */
    /**
     * countTime - Countdown timer that ends at 0, if time is less or equal to 0, a life is lost
     */
    private void countTime()
    {
        setTime(getTime() - 1); // Calls getTime method to get current time, subtracts by 1
        showTime(); // Shows current time
        if (time <= 0) // If time is equal or less than 0
        {
            setLives(getLives() - 1); // Remove 1 from current lives
            nextLevel(); // Proceed to next level
        }
    }

    /** GAME OVER */
    /**
     * Method checkGameOver - Calls gameWon or gameLost methods depending on which criteria is met
     * and ends the game Added endless mode so game continues after collecting 100 socks
     */
    private void checkGameOver()
    {
        if (getGoal() == 0) // If socks have been collected
        {
            setLevel(getLevel() + 1); // Add +1 to level counter
            nextLevel(); // Proceed to next level
        }
        if (endlessMode == false) // If endless mode is false
        {
            if (getScore() >= 100) // If Score is 100 or greater
            {
                gameWon(); // Call gameWon
            }
            if (getLives() <= 0) // if Lives are 0 or lesser
            {
                gameLost(); // Call gameLost
            }
        }
        else // Else if Endless mode is true
        {
            if (getLives() <= 0) // If Lives equal 0
            {
                if (getScore() >= 100) // If Score is 100 or greater
                {
                    gameWon(); // Call gameWon
                }
                else // Else if score is not 100 or greater
                {
                    gameLost(); // Call gameLost
                }
            }
        }
    }

    /**
     * Method gameLost - Displays loss message and total score
     */
    public void gameLost()
    {
        showText("You Lose!", 400, 300); // Displays you lose message in the middle of the screen
        showText("Total socks: " + getScore(), 400, 330); // Displays score in the middle of the
                                                          // screen
        Greenfoot.playSound("gamelost.mp3"); // Plays game lost sound
        Greenfoot.stop(); // Stops game
    }

    /**
     * Method gameWon - Displays win message and total score
     */
    private void gameWon()
    {
        showText("You Win!", 400, 300); // Displays you win message in the middle of the screen
        showText("Total socks: " + getScore(), 400, 330); // Displays score in the middle of the
                                                          // screen
        Greenfoot.playSound("gamewon.mp3"); // Plays game lost sound
        Greenfoot.stop(); // Plays game
    }

    /** RANDOMIZED ITEM DROPS */
    /**
     * Method randomSocks - Socks will appear every (p) out of 100 act cycles Socks will appear in
     * random locations
     */
    private void randomSocks(int p)
    {
        if (Greenfoot.getRandomNumber(101) < p) // if randomized number is less than p
        {
            addObject(new Sock(), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600)); // Add
                                                                                                   // a
                                                                                                   // new
                                                                                                   // sock
                                                                                                   // at
                                                                                                   // a
                                                                                                   // random
                                                                                                   // location
        }
    }

    /** VALUE ADJUSTMENT */
    /**
     * Method addScore - Adds parameter points to score variable (Also subtracts from goal)
     */
    public void addScore(int points)
    {
        setScore(getScore() + points); // Adds points to score variable
        setGoal(getGoal() - points); // Subtracts points from goal variable
    }
}
