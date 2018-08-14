import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A merge button to add more rides to the rides list
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class mergeButton extends MyButton
{
    public mergeButton()
    {
        super("Merge"); //it's a merge button
    }

    //Call the merge method in the world whenever it's clicked.
    public void performAction()
    {
        ((MyWorld) getWorld()).merge(); 
    }
}