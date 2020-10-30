/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import static java.awt.Color.cyan;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ashley ddb1062
 */
public class ClientGUI extends JFrame {

    String screen = "Main menu";

    public ClientGUI(String title) {
        super(title);

        getContentPane().setLayout(null);
        setSize(684, 701);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JLabel image1 = new JLabel(new ImageIcon("src/GUI/capture.PNG"));
        //Art by JOOJAEBUM https://www.behance.net/gallery/87142777/Cat-on-the-Rooftop-Pixel-Art
        setContentPane(image1);

        JLabel welcome = new JLabel("Welcome to Cat Manager!", SwingConstants.CENTER);
        welcome.setSize(684, 100);
        welcome.setLocation(0, 25);
        welcome.setForeground(white);
//        welcome.setEditable(false);
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("thisOne.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("thisOne.ttf")));

        } catch (IOException | FontFormatException e) {

        }
        welcome.setFont(font);
        getContentPane().add(welcome);
        welcome.setVisible(true);

//        DefaultListModel model = new DefaultListModel();
//        JList menu = new JList(model);
//        ListSelectionListener listener = null;
//        menu.addListSelectionListener(listener);
//        menu.setLocation(200, 200);
//        menu.setSize(200, 200);
//        getContentPane().add(menu);
//        menu.setVisible(true);
        String[] mainMenu = {"My Cat/s", "Visit Shelter"};
        String[] shelterMenu = {"Adopt a cat", "Main menu"};
        String[] adoptCat = {"Adopt this cat", "Look for another cat", "Main menu"};
        // run through cat list String [] myCats = { cat names... "Main menu"];
        String[] myCatsMenu = {"Play", "Eat", "Sleep", "Save", "Main menu"};

        JComboBox<String> menu;
        menu = new JComboBox<String>(mainMenu);
        menu.setSize(100, 25);
        menu.setLocation(250, 200);
        menu.setVisible(true);
        getContentPane().add(menu);

        JComboBox<String> shelter;
        shelter = new JComboBox<String>(shelterMenu);
        shelter.setSize(100, 25);
        shelter.setLocation(250, 200);
        shelter.setVisible(false);
        getContentPane().add(shelter);

        JComboBox<String> myCats;
        myCats = new JComboBox<String>(myCatsMenu);
        myCats.setSize(100, 25);
        myCats.setLocation(250, 200);
        myCats.setVisible(false);
        getContentPane().add(myCats);

        JComboBox<String> adopt;
        adopt = new JComboBox<String>(adoptCat);
        adopt.setSize(100, 25);
        adopt.setLocation(250, 200);
        adopt.setVisible(false);
        getContentPane().add(adopt);

        JButton button = new JButton("Select");
        button.setLocation(350, 200);
        button.setSize(100, 25);
        button.setVisible(true);
        getContentPane().add(button);

        JTextField stats = new JTextField();
        stats.setSize(200, 150);
        stats.setLocation(275, 150);
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        stats.setBorder(border);
        stats.setEditable(false);
        stats.setVisible(false);
        getContentPane().add(stats);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected;
                if (screen.equals("Main menu")) {
                    selected = menu.getSelectedIndex();

                    if (selected == 0) {
                        //go to my cats
                        image1.setIcon(new ImageIcon("src/GUI/catBackground.jpg"));
                        // https://i.pinimg.com/originals/db/6f/e1/db6fe1a6cb7827e6685f6d5eb46e6a83.jpg artwork from.
                        screen = "cats";
                        welcome.setText("My Cats");
                        myCats.setVisible(true);
                        menu.setVisible(false);
                        button.setLocation(375, 300);
                        myCats.setLocation(275, 300);
                        System.out.println(screen);

                    } else if (selected == 1) {
                        //go to shelter
                        image1.setIcon(new ImageIcon("src/GUI/shelter.png"));
                        screen = "shelter";
                        welcome.setText("The Cat Shelter");
                        shelter.setVisible(true);
                        menu.setVisible(false);
                        System.out.println(screen);

                    }

                } else if (screen.equals("shelter")) {
                    selected = shelter.getSelectedIndex();

                    if (selected == 0) {
                        //go to adopt
                        System.out.println("here");
                        welcome.setText("Adopt a Cat");
                        shelter.setVisible(false);
                        adopt.setVisible(true);
                        adopt.setLocation(275, 320);
                        adopt.setSize(150, 25);
                        button.setLocation(425, 320);
                        
                        screen = "adopt";

                        stats.setVisible(true);

                    } else if (selected == 1) {
                        //go to home screen
                        image1.setIcon(new ImageIcon("src/GUI/capture.PNG"));
                        menu.setVisible(true);
                        shelter.setVisible(false);
                        welcome.setText("Welcome to Cat Manager!");
                        screen = "Main menu";

                    }
                } else if (screen.equals("adopt")) {
                    selected = adopt.getSelectedIndex();
                    // show info in stats JTextField
                    if (selected == 0) {
                        //add cat to collection

                    } else if (selected == 1) {
                        //present another cat's info in stats JTextField

                    } else if (selected == 2) {
                        //go to home screen
                        image1.setIcon(new ImageIcon("src/GUI/capture.PNG"));
                        menu.setVisible(true);
                        stats.setVisible(false);
                        shelter.setVisible(false);
                        adopt.setVisible(false);
                        button.setLocation(350, 200);
                        welcome.setText("Welcome to Cat Manager!");
                        screen = "Main menu";

                    }
                } else if (screen.equals("cats")){
                    selected = myCats.getSelectedIndex();
                    stats.setVisible(true);
                    
                    if (selected ==0){
                        //play
                        //update stats
                    } else if(selected == 1){
                        //eat
                        //update stats
                    } else if (selected == 2){
                        //sleep
                        //update stats
                    } else if (selected == 3){
                        //save
                        
                    } else if (selected == 4){
                        //go to home screen
                        image1.setIcon(new ImageIcon("src/GUI/capture.PNG"));
                        menu.setVisible(true);
                        stats.setVisible(false);
                        shelter.setVisible(false);
                        welcome.setText("Welcome to Cat Manager!");
                        screen = "Main menu";
                    }
                }
            }

        }
        );
    }

    public static void main(String args[]) {
        JFrame frame = new ClientGUI("Virtual Cat");
        frame.setVisible(true);
    }

}