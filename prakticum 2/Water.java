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
    private boolean first = true;
    private int firstX = 0;
    private int firstY = 0;
    
    /**
     * Act - do whatever the Water wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(first){
            first = false;
            firstX = getX();
            firstY = getY();
        }
        Random rand = new Random();
        if(rand.nextInt(18) < 5){
            setLocation(firstX + (rand.nextInt(2) - 3), firstY + (rand.nextInt(2) - 3));
        }
    }    
    
    public Dijk getRandomNearDijk(){
        List<Dijk> nearDijk = getObjectsInRange(27, Dijk.class);
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
        List<Dijk> nearDijk = getObjectsInRange(23, Dijk.class);
        if(nearDijk.size() > 0){
            return nearDijk;
        }
        else {
            return null;
        }
    }
}
