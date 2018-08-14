import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A person that is waiting in line.
 * 
 * @author Tran Nguyen
 * @version 0.429
 */
public class Person extends Actor
{
    private int onTime; //time this person has been in line/added to world
    public boolean isWaiting; //is this person waiting in line?

    /**
     * A Constructor for class Person
     */
    public Person()
    {
        onTime = 0; //initially, this person's waiting time is 0
        isWaiting = false; //initially, this person is not waiting
    }
    
    //Return the time this person has been in line/added to world
    public int timeOn()
    {
        return onTime;
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //If this person is clicked, a yellow box will popup to show how long he has been waiting in line
        if (Greenfoot.mouseClicked(this))
        {
            ToolTip tt = new ToolTip("" + (timeOn()/100));
            getWorld().addObject(tt, getX(), getY());
        }
        
        // update his waiting time if necessary
        if (isWaiting)
            onTime++;
    }   
    
    //Whenever this person is added...
    public void add()
    {
        isWaiting = true; //now he's waiting in line
        onTime ++; //start couting his waiting time
    }
}