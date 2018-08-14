import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Contains information about every rides  
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class MyRides extends Actor
{
    private String rideName; //ride's name
    private int currentSize = 0; //current size of the line

    Person thisLine[]; //an array that contains people in one line 
    MyRides thisRides[]; //an array that contains all the rides

    private addButton a; //an add button
    private releaseButton r; //a release button

    private boolean isWaiting; //is this person waiting in line?

    /**
     * Constructor for class MyRides
     */
    public MyRides(String rideName)
    {
        thisRides = new MyRides[14]; //to make sure it can only have 14 rides
        thisLine = new Person[20]; //to make sure there can only be 20 people in one line
        this.rideName = rideName; //the ride's name is the input ride's name

    }

    //Return the ride's name
    public String getName()
    {
        return rideName;
    }

    /**
     * Add a person to the line 
     */
    public void add()
    {
        Person newPerson = new Person(); //create a person
        thisLine[currentSize] = newPerson; //put that person into the line
        ((MyWorld) getWorld()).addObject(thisLine[currentSize], 300 + 30 * currentSize, getY()); //then add him to the world

        currentSize ++; //size of the line increases by one
        newPerson.add(); //add some "characteristics" for that newly created human

    }

    /**
     * Remove one person off the line 
     */
    public void release()
    {
        if (currentSize >1) //To make sure the last person in the line won't be removed
        {
            ((MyWorld) getWorld()).removeObject(thisLine[0]); //remove the first person in the line
            currentSize --; //size of the line now decreases by one
            
            //For each person left in the line...
            for (int i = 0; i < currentSize; i ++)
            {
                thisLine[i+1].setLocation(300 + 30 * i, getY()); //move up one space
                thisLine[i] = thisLine[i+1];
            }
        }
    }

    protected void addedToWorld(World w) //add and release buttons are guaranteed to be added with every rides
    {
        //Both unique add and release buttons are connected to one single ride
        a = new addButton(this); 
        r = new releaseButton(this);

        getWorld().addObject(a, 200, getY()); 
        getWorld().addObject(r, 250, getY()); 
    }

    //Return the current size of the line
    public int getCurrentSize()
    {
        return currentSize;
    }

    //Return the add button connected
    public addButton getAddButton()
    {
        return a;
    }

    //Return the release button connected
    public releaseButton getReleaseButton()
    {
        return r;
    }

    //Return the ride's name
    public String getRidesName()
    {
        return rideName;
    }

    /**
     * Move objects to new location
     */
    public void moveTo(int newX, int newY)
    {
        setLocation(newX, newY); //move the ride's name to new location

        a.setLocation(200, newY); //also move its connected add button
        r.setLocation(250, newY); //and its connected release button too  
        
        //Move all the people in that line to new location
        for (int i = 0; i < currentSize; i++)
        {
            thisLine[i].setLocation(300 + 30*i, newY);
        }
    }
}