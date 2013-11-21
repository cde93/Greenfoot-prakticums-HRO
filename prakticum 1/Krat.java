import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Krat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Krat extends Actor
{
    public boolean first = true;
    /**
     * Act - do whatever the Krat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }   
    
    public void go_to_train()
    {
        if(first)
        {
            setLocation(4, 3);
        } 
        else 
        {
            if(getY() != 6)
            {
                setLocation(getX(), getY()+1);
            }
        }
        first = false;
    }
}
