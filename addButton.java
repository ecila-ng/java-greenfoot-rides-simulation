import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An add button to add a person to the rides line.
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class addButton extends MyButton
{
    private MyRides thisRide; //get a reference to the rides
    
    public addButton(MyRides connectedRide) 
    {
        super("Add"); //show that it's an add button
        thisRide = connectedRide; //this add button connects to the input connectedRide
    }

    //Call the add method in the MyRides class whenever it's clicked.
    public void performAction()
    {
        thisRide.add();
    }    
}
