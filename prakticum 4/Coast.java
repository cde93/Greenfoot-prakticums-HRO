import greenfoot.*;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;  
import javax.swing.*;  
import java.awt.Cursor;  
import java.awt.Point;  
import java.awt.Toolkit; 
import java.util.*;

/**
 * Write a description of class Coast here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coast extends World {

    /**
     * Constructor for objects of class Coast.
     */
    private List<Water> sea = new ArrayList<Water>();
    private List<Dijk> dedijk = new ArrayList<Dijk>();
    private int startAttackLocation = 0;
    private boolean nextIsY = true;
    private boolean bursted = false;
    private int randRange = 40;

    public Coast() {
        super(800, 800, 1);
        Cannon.coast = this;
        createBinnenDijk();
        createDijk();
        createSea();
        makeRandomStart();
        setPaintOrder(Cannon.class, Bursted.class, Ship.class, Zandzak.class,Water.class, Dijk.class);
        Ship ship = new Ship();
        addObject(ship, 400, 630);        
        addObject(new Cannon(), 380, 20);
        Greenfoot.setSpeed(32);
    }
    
    private void createBinnenDijk(){
        Random rand = new Random();
        for (int y = 10; y < 100; y += 20) {
            for (int x = 10; x < 800; x += 20) {
                int ry = rand.nextInt(3);
                int rx = rand.nextInt(3);
                BinnenDijk dijk = new BinnenDijk();
                addObject(dijk, x+rx, y+ry);
            }
        }
    }

    private void createDijk() {
        Random rand = new Random();
        for (int y = 210; y < 500; y += 20) {
            for (int x = 10; x < 800; x += 20) {
                int ry = rand.nextInt(3);
                int rx = rand.nextInt(3);
                Dijk dijk = new Dijk();
                addObject(dijk, x+rx, y+ry);
                dedijk.add(dijk);
            }
        }
    }

    private void createSea() {
        Random rand = new Random();        
        for (int y = 510; y < 800; y += 20) {                       
            for (int x = 10; x < 800; x += 20) {
                int ry = rand.nextInt(3);
                int rx = rand.nextInt(3);
                Water water = new Water();
                addObject(water, x+rx, y+ry);
                sea.add(water);
            }
        }
    }

    private void makeRandomStart() {
        Random rand = new Random();
        startAttackLocation = rand.nextInt(randRange);
        if(randRange < 100){
            randRange += 5;
        }
    }

    private void attack() {
        Water water = getRandomWater();
        List<DefensiveObject> dijken = water.getRandomNearDijks();

        Random rand = new Random();
        if (dijken != null) {
            for (int i = 0; i < dijken.size(); i++) {
                int randDijk = rand.nextInt(40);
                int randWater = rand.nextInt(30);
                if (randDijk < randWater) {
                    int ry = rand.nextInt(3);
                    int rx = rand.nextInt(3);
                    if(dijken.get(i).getHealth() > 0 || dijken.get(i).hasZandzak()){
                        makeRandomStart();
                        return;
                    }
                    Water newWater = new Water();
                    addObject(newWater, dijken.get(i).getX()+rx, dijken.get(i).getY()+ry);
                    removeObject(dijken.get(i));
                    sea.add(0, newWater);
                }
            }
        } else {
            makeRandomStart();
        }        
    }

    private Water getRandomWater() {
        Water water;
        if (startAttackLocation != 0) {
            water = sea.get(startAttackLocation);
            startAttackLocation = 0;
        } else {
            water = sea.get(0);
        }
        return water;
    }

    public boolean checkBursting() {
        for (int xc = 0; xc < 800; xc += 20) {
            List bursted = getObjectsAt(xc, 200, Water.class);
            if (bursted.size() > 0) {
                Random rand = new Random();        
                for (int y = 110; y < 200; y += 20) {                       
                    for (int x = 10; x < 800; x += 20) {
                        int ry = rand.nextInt(3);
                        int rx = rand.nextInt(3);
                        Water water = new Water();
                        addObject(water, x+rx, y+ry);
                        sea.add(water);
                    }
                }
                return true;
            }
        }
        return false;
    }


    
    public void act() {        
        if (bursted == false) {
            bursted = checkBursting();
        } else {
            Bursted bursted = new Bursted();
            addObject(bursted, 400, 300);
            //Greenfoot.stop();
        }
        attack();
    }
    
    
}
