import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Dijk here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dijk extends Actor
{
    public boolean placed = false;
    public boolean zandzak = false;
    public int health = 0;
    /**
     * Act - do whatever the Dijk wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Dijk(){
        
    }
    
    public Dijk(boolean zandzak){
        this.zandzak = zandzak;
        setImage("images/Zandzak.png");
        health = 9;
    }
    
    public void act() 
    {
        if(zandzak){
            if(isTouching(Water.class)){
                health--;
            }
            if(health <= 0){
                getWorld().removeObject(this);
            }
        }
    }    
    
    
}
