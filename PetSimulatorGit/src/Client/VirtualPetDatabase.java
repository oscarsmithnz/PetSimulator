/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import cats.Cat;
import cats.CatFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

/**
 *
 * @author Oscar //LOGIN: OscarAshley AshleyOscar
 *
 * This class is a singleton, with private constructor, reference to self, and
 * getInstance method. Contains public methods to get pet by id, get list of
 * pets in database (id and name, not Cat objects), save pet to database,
 *
 */
public class VirtualPetDatabase {

    private static VirtualPetDatabase instance;
    private final static String URL = "jdbc:derby:PetDB; create=true";
    private final static String username = "OscarAshley";
    private final static String password = "AshleyOscar";
    private Connection conn;

    private VirtualPetDatabase() {
        establishConnection();
        createPetTable();
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

    //save order: id, name, age, serializable
    //creates the table if it doesn't exist with values for id (identity), name, age, serial (stored as blob)
    private void createPetTable() {
        try {
            Statement statement = conn.createStatement();
            String newTable = "PETS";

//            statement.executeUpdate("drop table " + newTable);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, newTable, null);
            if (!rs.next()) {
                String sqlCreateTable = "CREATE TABLE " + newTable + " (ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Name VARCHAR(50),"
                        + " Age INT, Serial BLOB(512))";
                statement.executeUpdate(sqlCreateTable);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    //return a list of pet id's + names + ages of all pets in the database.
    public String[] getPets() {
        ResultSet rs = null;
        ArrayList<String> savedCats = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String sqlQuery = "select * from PETS";
            rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                do {
                    String temp = "id: " + rs.getInt("ID") + ", name: " + rs.getString("Name") + ", age: " + rs.getInt("Age");
                    savedCats.add(temp);
                } while (rs.next());
            } else {
                System.out.println("result set null, no pets saved.");
                return null;
            }
            return savedCats.toArray(new String[0]);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return null;
        }
    }

    //return null if id did not return anything
    //gets cat object by id in database, using serial value and object reading/writing
    public Cat getPetByID(int ID) {
        ResultSet rs = null;
        Cat result = null;
        try {
            Statement statement = conn.createStatement();
            String sqlQuery = "select serial from PETS where ID = " + ID;
            rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                Blob temp = rs.getBlob("Serial");
                try {
                    ObjectInputStream ois = new ObjectInputStream(temp.getBinaryStream());
                    result = (Cat) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.err.println("Exception in getPetByID: " + ex.getMessage());
                }

            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return result;
    }

    //insert cat object into database. serialises the object as last value to easily retrieve.
    public boolean insertPet(Cat c) {
        String query = "INSERT INTO PETS VALUES(DEFAULT, ?, ?, ?)";
        byte[] data = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(c);
            oos.flush();
            oos.close();
            baos.close();
            data = baos.toByteArray();
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
            return false;
        }

        try { //NAME AGE SERIAL
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, c.getName());
            statement.setInt(2, c.getAge());
            statement.setObject(3, data);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    private void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, username, password);
            System.out.println(URL + " connected");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("Next error: " + ex.getNextException().getMessage());
        }
    }

    //return true if connection closed otherwise false
    public boolean closeConnection() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    //for testing/debugging purposes.
    public static void main(String[] args) {
        VirtualPetDatabase vpdb = VirtualPetDatabase.getInstance();

        System.out.println("Insert pet: " + vpdb.insertPet(CatFactory.getCat()));
        System.out.println("getting pets...");
        String[] pets = vpdb.getPets();
        for (String p : pets) {
            System.out.println(p);
        }

        Cat temp = vpdb.getPetByID(101);
        System.out.println(temp);
        try {
            vpdb.conn.close();
        } catch (SQLException ex) {
            System.err.println("SQLException in main: " + ex.getMessage());
        }
    }
}
