import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class DefensiveObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class DefensiveObject extends Actor
{    
    protected int health;
    
    public boolean hasZandzak(){
        List<Zandzak> zandzakken = getObjectsInRange(20, Zandzak.class);
        zandzakken.remove(this);
        Object obj = getClass();
        if( obj instanceof Zandzak){
            System.out.println("zandzak");
            return false;
        }
        else if(zandzakken.size() > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public int getHealth(){
        return health;
    }
     
}
