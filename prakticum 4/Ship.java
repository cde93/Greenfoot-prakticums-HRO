import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Greenfoot;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Ship(){
        
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("up") && getObjectsAtOffset(0, -10, Water.class).size() != 0){
            setLocation(getX(), getY() - 10);
            setRotation(180);
        }
        if(Greenfoot.isKeyDown("down") && getObjectsAtOffset(0, 10, Water.class).size() != 0){
            setLocation(getX(), getY() + 10);
            setRotation(0);
        }
        if(Greenfoot.isKeyDown("left") && getObjectsAtOffset(-10, 0, Water.class).size() != 0){
            setLocation(getX() - 10, getY());
            setRotation(90);
        }
        if(Greenfoot.isKeyDown("right") && getObjectsAtOffset(10, -10, Water.class).size() != 0){
            setLocation(getX() + 10, getY());
            setRotation(270);
        }
    }    
}
