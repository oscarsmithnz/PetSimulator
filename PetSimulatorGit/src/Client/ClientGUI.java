/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
//test
import cats.Cat;
import interactions.CatManager;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author Ashley ddb1062
 */
public class ClientGUI extends JFrame {
    VirtualPetClient client;
    String screen = "Main menu";

    public ClientGUI(String title, VirtualPetClient vpc) {
        super(title);
        client = vpc;

        getContentPane().setLayout(null);
        setSize(684, 701);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JLabel image1 = new JLabel(new ImageIcon("images/catOnRoof.JPEG"));
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

        JTextField info = new JTextField();
        info.setEditable(false);

        JScrollPane stats = new JScrollPane(info,
                                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        stats.setLocation(275, 100);
                        stats.setSize(200, 200);
                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
                        stats.setBorder(border);
                        stats.setVisible(false);
                        getContentPane().add(stats);

        JTextField IDSelect = new JTextField("Type cat ID");
        IDSelect.setSize(100, 25);
        IDSelect.setLocation(275, 300);
        IDSelect.setVisible(false);
        getContentPane().add(IDSelect);
        
        IDSelect.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                IDSelect.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(IDSelect.getText())) {
                    IDSelect.setText("User Name");
                }
            }
        });
        
        JTextArea adoptedCats = new JTextArea();
        adoptedCats.setEditable(false);
        
        JScrollPane scroll = new JScrollPane(adoptedCats,
                                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scroll.setLocation(275, 100);
                        scroll.setSize(200, 200);
                        scroll.setVisible(false);
                        getContentPane().add(scroll);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected;
                if (screen.equals("Main menu")) {
                    selected = menu.getSelectedIndex();

                    if (selected == 0) {
                        //go to my cats
                        image1.setIcon(new ImageIcon("images/catBackground.jpg"));
                        // https://i.pinimg.com/originals/db/6f/e1/db6fe1a6cb7827e6685f6d5eb46e6a83.jpg artwork from.
                        screen = "cats";
                        welcome.setText("My Cats");
                        IDSelect.setVisible(true);
                        menu.setVisible(false);
                        button.setLocation(375, 300);
                        //client.getMyCats();
                        //TODO: implement showing cats in gui, select cat from menu
                        scroll.setVisible(true);
                        
                        
                        for (String myCat : client.getMyCats()) {
                            adoptedCats.append(myCat);
                            adoptedCats.append("\n");
                        }
                        
                        
                        

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
                        //TODO 
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
                        //TODO
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
                        //TODO
                        //add cat to collection

                    } else if (selected == 1) {
                        //TODO
                        //present another cat's info in stats JTextField

                    } else if (selected == 2) {
                        //TODO
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
                } else if (screen.equals("cats")) {
                   selected = myCats.getSelectedIndex();
                   info.setText(catLoader(Integer.parseInt(IDSelect.getText()))); 
                   stats.setVisible(true);
                   IDSelect.setVisible(false);
                   myCats.setVisible(true);
                   myCats.setLocation(275,300);
                   adoptedCats.setVisible(false);
                   scroll.setVisible(false);

                    if (selected == 0) {
                        //play
                        //update stats
                    } else if (selected == 1) {
                        //eat
                        //update stats
                    } else if (selected == 2) {
                        //sleep
                        //update stats
                    } else if (selected == 3) {
                        //save

                    } else if (selected == 4) {
                        //go to home screen
                        image1.setIcon(new ImageIcon("petsimulator/petsimulatorgit/images/capture.PNG"));
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

    public void updateCatStats() {
        Cat current = client.getCat();
        //TODO: print cat elements onto gui
    }
    
    public String catLoader(int id){
        Cat cat = client.loadCat(id);
        
       String text = cat.toString();
       return text;
                
    }
}
