import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A sort button to sort the rides by its name alphabetically
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class sortByNameButton extends MyButton
{
    public sortByNameButton()
    {
        super("Sort by name"); //this is a "sort by name" button
    }
    
    //Call the sortByName method in the world whenever it's clicked.
    public void performAction()
    {
        ((MyWorld) getWorld()).sortByName();
    }   
}
