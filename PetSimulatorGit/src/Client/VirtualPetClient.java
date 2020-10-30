/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import cats.*;
import interactions.CatManager;

/**
 *
 * @author Oscar
 *
 * the client that is run to use the program.
 */
public class VirtualPetClient implements Runnable{

    private VirtualPetDatabase database;
    private CatManager manager;
    ClientGUI gui;

    public VirtualPetClient() {
        database = VirtualPetDatabase.getInstance();
        manager = new CatManager();
        gui = new ClientGUI("Pet Simulator", this);
    }

    public static void main(String[] args) {
        VirtualPetClient client = new VirtualPetClient();
        client.run();
    }

    public void play(){
        manager.play();
    }
    
    public void eat(){
        manager.eat();
    }
    
    public void sleep(){
        manager.sleep();
    }
    
    public void run(){
        gui.setVisible(true);
    }
    
    /*
    return a new cat from the cat factory
     */
    public Cat adoptNewCat() {
        return CatFactory.getCat();
    }

    /*
    interacts with database for the gui
     */
    public Cat loadCat(int ID) {
        Cat temp = database.getPetByID(ID);
        manager.setCat(temp);
        return temp;
    }

    public boolean saveCat(){
        return manager.saveCat();
    }
    
    public CatManager getManager(){
        return manager;
    }
    
    //method for gui to get cats saved in database.
    public String[] getMyCats() {
        return database.getPets();
    }
    
    public Cat getCat(){
        return manager.getCat();
    }
}
