import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Haven here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Haven extends World
{
    public int number_of_trains = 0;
    /**
     * Constructor for objects of class Haven.
     * 
     */
    public Haven()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(8, 8, 62); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        for(int i = 2; i < 6; i++)
        {
            Krat krat1 = new Krat();
            addObject(krat1, i, 1);
            Krat krat2 = new Krat();
            addObject(krat2, i, 2);
        }
    }
    
    public void act()
    {
        make_trein();
        krat_to_train();
        remove_train();
        check_for_end();
    }
    
    private void make_trein()
    {
        List trn = getObjectsAt(0, 6, Trein.class);
        if(trn.size() == 0 && number_of_trains < 8)
        {
             Trein trein = new Trein();
             addObject(trein, 0, 6);
             List treinenactief = getObjects(Trein.class);
             System.out.println("Niewe trein, aantal treinen nu: "+treinenactief.size());
             number_of_trains++;
        }    


    }
    
    private void krat_to_train()
    {
        List trn = getObjectsAt(4, 6, Trein.class);
        if(trn.size() != 0)
        {
            java.util.List krats = getObjects(Krat.class);
            Krat krat_move = (Krat)krats.get(0);
            krat_move.go_to_train();
        }
    }
    
    private void remove_train()
    {
        List<Trein> treinen = getObjectsAt(7, 6, Trein.class);
        if(treinen.size() > 0)
        {
            Trein trein = treinen.get(0);
            removeObject(trein);
            System.out.println("Trein rijdt uit beeld");
        }
    }
    
    private void check_for_end()
    {
        List treinenactief = getObjects(Trein.class);
        if(number_of_trains == 8 && treinenactief.size() == 0)
        {
            prepare();
            number_of_trains = 0;
            System.out.println("--- Begin opnieuw ---");
        }
    }
}
