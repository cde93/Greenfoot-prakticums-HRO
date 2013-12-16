import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Dijk here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dijk extends DefensiveObject
{
    protected int health = 0;
    
    public void act(){
        if(getObjectsInRange(27, Water.class).size() > 2){
            getWorld().addObject(new Water(), getX(), getY());
            getWorld().removeObject(this);            
        }
    }
}
