import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A sort button to sort the rides by its length
 * 
 * @author Tran Nguyen
 * @version 0.429
 * 
 */
public class sortByQueueLength extends MyButton
{
    public sortByQueueLength()
    {
        super("Sort by queue length"); //hi i'm a "sort by queue length" button
    }
    
    //Call the sortByQueueLength method in the world whenever it's clicked.
    public void performAction()
    {
        ((MyWorld) getWorld()).sortByQueueLength();
    }   
}
