import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Water here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Water extends Actor
{
    /**
     * Act - do whatever the Water wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public Dijk getRandomNearDijk(){
        List<Dijk> nearDijk = getObjectsInRange(22, Dijk.class);
        Random rand = new Random(); 
        if(nearDijk.size() > 0){
            System.out.println(nearDijk.size());
            int random = rand.nextInt(nearDijk.size());
            return nearDijk.get(random);
        }
        else {
            return null;
        }
    }

    public List<Dijk> getRandomNearDijks(){
        List<Dijk> nearDijk = getObjectsInRange(20, Dijk.class);
        if(nearDijk.size() > 0){
            //System.out.println(nearDijk.size());
            //int random = rand.nextInt(nearDijk.size());
            return nearDijk;
        }
        else {
            return null;
        }
    }
}
