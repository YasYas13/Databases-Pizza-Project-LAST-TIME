package com.example.databases;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MenuScene {

    static JFrame frame;
    static JPanel panel;
    static JButton pizza,drink,dessert;
    static JButton returnMenu;

    static JFrame pizzaFrame;

    static DataBase dataBase;


    public MenuScene() throws SQLException, ClassNotFoundException {
        dataBase = new DataBase();
        createWindow();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MenuScene menuScene = new MenuScene();

    }

    public static void createWindow(){
        frame=new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pizza=new JButton("Pizza");
        drink=new JButton("Drink");
        dessert=new JButton("Dessert");
        pizza.setSize(40,80);
        drink.setSize(40,80);
        dessert.setSize(40,80);
        panel=new JPanel();

        panel.add(pizza);
        panel.add(drink);
        panel.add(dessert);
        frame.add(panel);

        frame.setSize(640,200);
        frame.setTitle("Welcome Yas&Haomin Pizza");
        frame.setBackground(Color.GRAY);
        frame.setResizable(false);
        frame.setVisible(true);

        pizza.addActionListener(e -> choosePizza());

        chooseDrink(drink,frame);
        chooseDessert(dessert,frame);

    }
    public  static void choosePizza(){
        JFrame pizzaFrame =new JFrame("Please order what kind of pizza you want !!");
        JPanel pizzaPanel=new JPanel();
        pizzaFrame.setLayout(new GridLayout(2,1));
        pizzaFrame.setSize(640,400);

        //merge with db
        JComboBox<String> pizzaMenu = null;
        try { pizzaMenu = new JComboBox<>(dataBase.getPizzas()); }
        catch (SQLException ex) { ex.printStackTrace(); }
        pizzaPanel.add(pizzaMenu);
        pizzaFrame.getContentPane().add(pizzaPanel);

        //the second panel on the "choose pizza" window,then we divided the second panel again into 4 parts.
        JPanel showPizza = new JPanel(new GridLayout(4,1));
        showPizza.setLocation(200,200);
        JLabel pizzaName=new JLabel("Pizza");
        pizzaName.setHorizontalAlignment(JLabel.CENTER);
        showPizza.add(pizzaName);
        JLabel pizzaPrice=new JLabel("Price");
        pizzaPrice.setHorizontalAlignment(JLabel.CENTER);
        showPizza.add(pizzaPrice);
        JLabel pizzaIngredients=new JLabel("Ingredients");
        pizzaIngredients.setHorizontalAlignment(JLabel.CENTER);
        showPizza.add(pizzaIngredients);
        //the return button
        JButton back=new JButton("Back");
        back.addActionListener(e2 -> {
            pizzaFrame.dispose();
            createWindow();
        });
        showPizza.add(back);

        pizzaFrame.add(pizzaPanel);
        pizzaFrame.add(showPizza);
        pizzaFrame.setVisible(true);
    }



    public  static void chooseDrink(JButton drink,JFrame frame){

        drink.addActionListener(e -> {
            JFrame drinkFrame=new JFrame("Please order what kind of drink you want !!");
            JPanel drinkPanel=new JPanel();
            drinkFrame.setLayout(new GridLayout(2,1));

            drinkFrame.setSize(640,400);
            drinkPanel.setSize(640,200);
            //merge with db
            JComboBox<String> drinkMenu= null;
            try {
                drinkMenu = new JComboBox<>(dataBase.getDrinks());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            drinkMenu.setSelectedIndex(2);
            drinkMenu.addActionListener(e1 -> {
                JComboBox comboBox=(JComboBox) e1.getSource();
                JOptionPane.showMessageDialog(drinkFrame,comboBox.getSelectedItem());
            });
            drinkPanel.add(drinkMenu);
            drinkFrame.getContentPane().add(drinkPanel);

            //Also need to merge with database.
            JPanel showPrice=new JPanel(new GridLayout(2,1));
            showPrice.setLocation(200,300);
            showPrice.setSize(500,200);
            //the first panel
            JPanel drinkNamePanel=new JPanel();
            drinkNamePanel.setSize(500,50);
            JLabel pizzaName=new JLabel("Price");
            drinkNamePanel.add(pizzaName);

            //add the return button
            JButton back=new JButton("Back");
            JPanel returnMenu=new JPanel();
            back.addActionListener(e2 -> {
                drinkFrame.dispose();
                createWindow();

            });


            showPrice.add(drinkNamePanel);
            returnMenu.add(back);
            showPrice.add(returnMenu);
            drinkFrame.add(drinkPanel);
            drinkFrame.add(showPrice);

            drinkFrame.setVisible(true);

        });

    }

    public  static void chooseDessert(JButton dessert,JFrame frame){

        dessert.addActionListener(e -> {
            JFrame dessertFrame=new JFrame("Please order what kind of dessert you want !!");
            JPanel dessertPanel=new JPanel();
            dessertFrame.setLayout(new GridLayout(2,1));

            dessertFrame.setSize(640,400);
            dessertPanel.setSize(640,200);
            //merge with db
            JComboBox<String > dessertMenu= null;
            try {
                dessertMenu = new JComboBox<>(dataBase.getDesserts());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dessertMenu.setSelectedIndex(2);
            dessertMenu.addActionListener(e1 -> {
                JComboBox comboBox=(JComboBox) e1.getSource();
                JOptionPane.showMessageDialog(dessertFrame,comboBox.getSelectedItem());
            });
            dessertPanel.add(dessertMenu);
            dessertFrame.getContentPane().add(dessertPanel);

            //Also need to merge with database.
            JPanel showPrice=new JPanel(new GridLayout(2,1));
            showPrice.setLocation(200,300);
            showPrice.setSize(500,200);
            //the first panel
            JPanel dessertNamePanel=new JPanel();
            dessertNamePanel.setSize(500,50);
            JLabel pizzaName=new JLabel("Price");
            dessertNamePanel.add(pizzaName);
            //add the return button
            JButton back=new JButton("Back");
            JPanel returnMenu=new JPanel();
            back.addActionListener(e2 -> {
               dessertFrame.dispose();
               createWindow();
            });
            returnMenu.add(back);

            showPrice.add(dessertNamePanel);
            showPrice.add(returnMenu);
            dessertFrame.add(dessertPanel);
            dessertFrame.add(showPrice);
            dessertFrame.setVisible(true);

        });

    }








}














