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
    public boolean bursted = false;
    public List<Dijk> zandzakken = new ArrayList<Dijk>();

    public Coast() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);
        createBinnenDijk();
        createDijk();
        createSea();
        makeRandomStart();
        setPaintOrder(Bursted.class, Ship.class, Zandzak.class,Water.class, Dijk.class);
        Ship ship = new Ship();
        addObject(ship, 400, 500);
        zandzakken.add(new Dijk(true));
        addObject(zandzakken.get(0), 1, 1);
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
        for (int y = 210; y < 400; y += 20) {
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
        for (int y = 410; y < 800; y += 20) {                       
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
        startAttackLocation = rand.nextInt(40);
    }

    private void makeRandomStart(int i) {
        Random rand = new Random();
        startAttackLocation = rand.nextInt(i);
    }

    private void attack() {
        Water water = getRandomWater();
        List<Dijk> dijken = water.getRandomNearDijks();

        Random rand = new Random();
        if (dijken != null) {
            for (int i = 0; i < dijken.size(); i++) {
                int randDijk = rand.nextInt(40);
                int randWater = rand.nextInt(40);
                if (randDijk < randWater) {
                    int ry = rand.nextInt(3);
                    int rx = rand.nextInt(3);
                    if(dijken.get(i).health > 0){
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

    public void placeZandzak(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            Dijk zandzak = zandzakken.get(zandzakken.size()-1);
            if(zandzak.placed){
                Dijk newZandzak = new Dijk(true);
                addObject(newZandzak, mouse.getX(), mouse.getY());
                zandzakken.add(newZandzak);
                return;
            }
            zandzak.setLocation(mouse.getX(), mouse.getY());
            if(mouse.getButton() == 1){
                zandzak.placed = true;
            }
        }
    }
    
    public void act() {
        placeZandzak();
        if(Greenfoot.isKeyDown("r")){
            makeRandomStart(100);
        }
        attack();
        if (bursted == false) {
            bursted = checkBursting();
        } else {
            Bursted bursted = new Bursted();
            addObject(bursted, 400, 300);
            //Greenfoot.stop();
        }
    }
}
