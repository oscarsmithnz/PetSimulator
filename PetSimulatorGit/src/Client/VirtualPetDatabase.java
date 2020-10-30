/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import cats.Cat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;

/**
 *
 * @author Oscar //LOGIN: OscarAshley AshleyOscar
 *
 * This class is a singleton, with private constructor, reference to self, and
 * getInstance method.
 */
public class VirtualPetDatabase {

    private static VirtualPetDatabase instance;
    private final static String URL = "jdbc:derby:PetDB; create=true";
    private final static String username = "OscarAshley";
    private final static String password = "AshleyOscar";
    private Connection conn;

    private VirtualPetDatabase() {

    }

    //Synchronized inside the method so that threads do not get delayed as much
    public static VirtualPetDatabase getInstance() {
        if (instance == null) {
            synchronized (VirtualPetDatabase.class) {
                if (instance == null) {
                    instance = new VirtualPetDatabase();
                }
            }
        }
        return instance;
    }

    //DROPS IF EXISTS WE DO NOT WANT THIS
    //save order: id, name, age, serializable
    public void createPetTable() {
        try {
            Statement statement = conn.createStatement();
            String newTable = "pettable";

            statement.executeUpdate("drop table if exists" + newTable);

            String sqlCreateTable = "CREATE TABLE" + newTable + " (ID INT NOT NULL, NAME VHARCHAR(50),"
                    + "AGE INT, SERIAL BLOB(512), PRIMARY KEY (ID))";

            statement.executeUpdate(sqlCreateTable);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public String[] getPets() {
        ResultSet rs = null;
        ArrayList<String> savedCats = new ArrayList<>();
        
        try{
            Statement statement = conn.createStatement();
            String sqlQuery = "select * from pettable";
            rs = statement.executeQuery(sqlQuery);
            if (rs == null){
                System.out.println("result set null, no pets saved.");
                return null;
            }
            do{
                String temp = "id: " + rs.getInt("ID") + ", name: " + rs.getString("NAME") + ", age: " + rs.getInt("AGE");
                savedCats.add(temp);
            }while (rs.next());
            return (String[]) savedCats.toArray();
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
            return null;
        }
    }
    
    public boolean insertPet(Cat c){
        String query = "INSERT INTO pettable VALUES(?, ?, ?)";
        byte[] data = null;
        
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(c);
            oos.flush();
            oos.close();
            baos.close();
            data = baos.toByteArray();
        }catch(IOException ex){
            System.err.println("IOException: " + ex.getMessage());
            return false;
        }
        
        try{ //NAME AGE SERIAL
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, c.getName());
            statement.setInt(2, c.getAge());
            statement.setObject(3, data);
            statement.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, username, password);
            System.out.println(URL + " connected");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
