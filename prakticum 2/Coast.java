import greenfoot.*;
import greenfoot.Greenfoot;

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
    public List<Water> sea = new ArrayList<Water>();
    public List<Dijk> dedijk = new ArrayList<Dijk>();
    public int startAttackLocation = 0;
    public boolean nextIsY = true;

    public Coast() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);
        createDijk();
        createSea();
        makeRandomStart();
    }

    private void createDijk() {
        for (int y = 10; y < 300; y += 20) {
            for (int x = 10; x < 800; x += 20) {
                Dijk dijk = new Dijk();
                addObject(dijk, x, y);
                dedijk.add(dijk);
            }
        }
    }

    private void createSea() {
        for (int y = 310; y < 800; y += 20) {
            for (int x = 10; x < 800; x += 20) {
                Water water = new Water();
                addObject(water, x, y);
                sea.add(water);
            }
        }
    }

    private void makeRandomStart() {
        Random rand = new Random();
        startAttackLocation = rand.nextInt(40);
    }

    private void makeRandomStart(int i) {
        Random rand = new Random();
        startAttackLocation = rand.nextInt(i);
    }

    private void attack() {
        Water water = getRandomWater();

        // Dijk dijk = RandomNearDijk(water);

        List<Dijk> dijken = water.getRandomNearDijks();

        Random rand = new Random();
        if (dijken != null) {
            for (int i = 0; i < dijken.size(); i++) {
                int randDijk = rand.nextInt(40);
                int randWater = rand.nextInt(40);
                if (randDijk < randWater) {
                    Water newWater = new Water();
                    addObject(newWater, dijken.get(i).getX(), dijken.get(i).getY());
                    removeObject(dijken.get(i));
                    sea.add(0, newWater);
                }
            }
        } else {
            makeRandomStart();
        }
    }

    /*private Dijk RandomNearDijk(Water water) {
        Dijk dijk = null;
        int cntr = 0;
        while (dijk == null) {
            if (cntr > 4) {
                makeRandomStart();
                //return;
            }
            dijk = water.getRandomNearDijk();
            if (dijk != null) {
                if (dijk.getY() > water.getY()) {
                    dijk = null;
                }
                if(dijk.getY() == water.getY() && nextIsY){
                    dijk = null;
                }

            }
            cntr++;
        }
        return dijk;
    }    */

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
        for (int x = 0; x < 800; x += 20) {
            List bursted = getObjectsAt(x, 10, Water.class);
            if (bursted.size() > 0) {
                System.out.println("Doorgebroken");
                return true;
            }
        }
        return false;
    }

    public void act() {
        if(Greenfoot.isKeyDown("r")){
            makeRandomStart(100);
        }
        if (checkBursting() == false) {
            attack();
        } else {
            Bursted bursted = new Bursted();
            addObject(bursted, 400, 300);
            Greenfoot.stop();
        }
    }
}
