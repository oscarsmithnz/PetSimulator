/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import cats.*;
import interactions.CatManager;
import java.util.Scanner;

/**
 *
 * @author Oscar
 *
 * the client that is run to use the program.
 */
public class VirtualPetClient {

    private VirtualPetDatabase database;
    private CatManager manager;
    ClientGUI gui;

    public VirtualPetClient() {
        database = VirtualPetDatabase.getInstance();
        manager = new CatManager(this);
    }

    public static void main(String[] args) {
        VirtualPetClient client = new VirtualPetClient();
        client.gui = new ClientGUI("Pet Simulator", client);
        client.gui.setVisible(true);
    }

    public void play(){
        manager.play();
        gui.updateCatStats();
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

    //method for gui to get cats saved in database.
    public String[] getMyCats() {
        return database.getPets();
    }
    
    public Cat getCat(){
        return manager.getCat();
    }
}
