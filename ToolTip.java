import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Graphics2D;
import java.awt.FontMetrics;
/**
 * A ToolTip is a yellow box with black text that disappears after 
 * a certain number of calls to act()
 * 
 * Although you are welcome to modify this code, modifying this
 * code is HIGHLY INADVISABLE. 
 * 
 * @author Stephen Blythe
 * @version 2/2018
 */
public class ToolTip extends Actor
{
    private String text; // text to display in ToolTip
    private int delay;   // calls to act until this disappears
    
    private static Graphics2D gfx; // grpahic context for drawing
    
    /**
     * contructor to display a tooltip for 100 act calls
     * 
     * @param text the text displayed in the tooltip
     */
    public ToolTip(String text)
    {
        build(text, 100); // build the tooltip with delay 100
    }
    
    /**
     * contructor to display a tooltip for specified # of act calls
     * 
     * @param text the text displayed in the tooltip
     * @param life the number of calls to display this for
     */
    public ToolTip(String text, int life)
    {
        build(text, life); // build the tooltip with requested delay
    }
    
    // build a tooltip with specified text and provided delay
    private void build(String text, int delay)
    {
        // set up instance variables for the tooltip
        this.text = text;
        this.delay = delay;
        
        redraw(); // build the image for this tooltip
    }
    
    // builds the tooltip image based on instance variable for text
    private void redraw()
    { 
        // if we do not yet have a graphics context, get one
        if (gfx==null)
        {
            // build dummy image to pull context from
            GreenfootImage dummy = new GreenfootImage(1,1);
            
            // get the grpahics context
            gfx = dummy.getAwtImage().createGraphics();
        }
        
        // pull width of text from graphics context
        int textWidth = gfx.getFontMetrics().stringWidth(text);
        
        // pull height of font from graphics context
        int textHeight = gfx.getFontMetrics().getHeight();
        
        // build an appropriately sized image
        GreenfootImage img = new GreenfootImage(textWidth+4, textHeight+4);

        // fill background with yellow
        img.setColor(Color.YELLOW);
        img.fill();
        
        // draw the text in the image
        img.setColor(Color.BLACK);
        img.drawString(text, 2, textHeight-1);
        
        // use the image we have built for this tooltip       
        setImage(img);
    }
    
    /**
     * pretty easy - just do noting until you are to be removed. 
     */
    public void act() 
    {
        if (delay-- > 0)
            return;
       
        getWorld().removeObject(this);
    }    
}
