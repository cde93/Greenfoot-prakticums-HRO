import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trein here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trein extends Actor
{
    private boolean first = true;
    /**
     * Act - do whatever the Trein wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor krat = getOneObjectAtOffset(0, 0, Krat.class);
        Actor notrn = getOneObjectAtOffset(1, 0, Trein.class);
        if(!first && (krat != null || getX() != 4) && notrn == null)
        {
            setLocation(getX() + 1, getY());
        }
        if(krat != null)
        {
            getWorld().removeObject(krat);
            System.out.println("Trein geladen");
        }
        first = false;
    }
}
