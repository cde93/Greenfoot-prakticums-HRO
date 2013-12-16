import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Zandzak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zandzak extends DefensiveObject
{
    private boolean placed = false;
    protected int health = 9;
    
    public void act() 
    {
        List<Water> water = getObjectsInRange(30, Water.class);
        if(water.size() > 0){
            health--;
        }
        if(health <= 0){
            getWorld().removeObject(this);
        }
        
    }
    
    public boolean getPlaced(){
        return placed;
    }
    
    public void setPlaced(boolean placed){
        this.placed = placed;
    }
}
