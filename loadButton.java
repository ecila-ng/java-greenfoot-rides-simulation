import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A load button to load rides into the rides list
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class loadButton extends MyButton
{
    public loadButton()
    {
        super("Load"); //this is a load button
    }

    //Call the load method in the world whenever it's clicked.
    public void performAction()
    {
        ((MyWorld) getWorld()).load();

    }

}