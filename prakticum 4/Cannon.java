import greenfoot.*;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;  
import javax.swing.*;  
import java.awt.Cursor;  
import java.awt.Point;  
import java.awt.Toolkit; 
import java.util.*;
/**
 * Write a description of class Cannon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cannon extends Actor
{
    public static Coast coast;
    private List<Zandzak> zandzakken = new ArrayList<Zandzak>();
    
    public Cannon(){
        zandzakken.add(new Zandzak());
        coast.addObject(zandzakken.get(0), 1, 1);
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            turnTowards(mouse.getX(), mouse.getY());
        }
        if(Greenfoot.isKeyDown("a")){
            setLocation(getX() - 10, getY());
        }
        if(Greenfoot.isKeyDown("d")){
            setLocation(getX() + 10, getY());
        }
        placeZandzak();
    }    
    
        public void placeZandzak(){
        ChangeMouseImage(new GreenfootImage("images/mouse.png"), 16, 16); 
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            Zandzak zandzak = zandzakken.get(zandzakken.size()-1);
            if(zandzak.getPlaced()){
                Zandzak newZandzak = new Zandzak();
                getWorld().addObject(newZandzak, 400, 50);
                zandzakken.add(newZandzak);
                return;
            }
            
            if(mouse.getButton() == 1 || Greenfoot.isKeyDown("space")){
                zandzak.turnTowards(mouse.getX(), mouse.getY());                
                double moveX = zandzak.getX() - mouse.getX();
                double moveY = zandzak.getY() - mouse.getY();                     
                double edge = Math.sqrt(Math.pow((double)moveX, 2) + (double)Math.pow(moveY, 2));                
                zandzak.move((int)edge);
                zandzak.setPlaced(true);              
            }
        }
    }
    
    public void ChangeMouseImage(GreenfootImage image, int XClick, int YClick)  
    {  
        JPanel Panel = WorldHandler.getInstance().getWorldCanvas();  
        Cursor Cursor;  
        Toolkit Tk = Toolkit.getDefaultToolkit();  
        Point CursorPoint= new Point(XClick,YClick);  
        Cursor = Tk.createCustomCursor(image.getAwtImage(),CursorPoint,"Somehow");  
        Panel.setCursor(Cursor);  
    } 
}
