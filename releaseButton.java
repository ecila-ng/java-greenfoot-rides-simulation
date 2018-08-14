import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A release button to add a person to the rides line.
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class releaseButton extends MyButton
{
    private MyRides thisRide; //get a reference to the rides
    
    public releaseButton(MyRides connectedRide) 
    {
        super("Release"); //show that it's an release button
        thisRide = connectedRide; //this release button connects to the input connectedRide
    }

    //Call the release method in the MyRides class whenever it's clicked.
    public void performAction()
    {
        thisRide.release();
    }     
}
