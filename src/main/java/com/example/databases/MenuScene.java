package com.example.databases;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuScene {

    static JFrame frame;
    static JPanel panel;
    static JButton pizza,drink,dessert;
    static JButton returnMenu;

    


    public static void main(String[] args){
        createWindow();

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


        choosePizza(pizza,frame);
        chooseDrink(drink,frame);
        chooseDessert(dessert,frame);

    }
    public  static void choosePizza(JButton pizza,JFrame frame){

        pizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame pizzaFrame=new JFrame("Please order what kind of pizza you want !!");
                JPanel pizzaPanel=new JPanel();
                pizzaFrame.setLayout(new GridLayout(2,1));

                pizzaFrame.setSize(640,400);
                pizzaPanel.setSize(640,200);
                //merge with db
                String[] pizza={"pingguo","pizaa","orange"};
                JComboBox<String > pizzaMenu=new JComboBox<>(pizza);
                pizzaMenu.setSelectedIndex(2);
                pizzaMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox comboBox=(JComboBox)e.getSource();
                        JOptionPane.showMessageDialog(pizzaFrame,comboBox.getSelectedItem());
                    }
                });
                pizzaPanel.add(pizzaMenu);
                pizzaFrame.getContentPane().add(pizzaPanel);

                //the second panel on the "choose pizza" window,then we divided the second panel again into 4 parts.
                //Also need to merge with database.
                JPanel showPizza=new JPanel(new GridLayout(4,1));
                //BoxLayout layout=new BoxLayout(showPizza,BoxLayout.X_AXIS);
                //showPizza.setBackground(Color.darkGray);
                showPizza.setLocation(200,300);
                showPizza.setSize(500,200);
                //the first panel
                JPanel pizzaNamePanel=new JPanel();
                pizzaNamePanel.setSize(500,50);
                JLabel pizzaName=new JLabel("Pizza");
                pizzaNamePanel.add(pizzaName);
                //the second panel
                JPanel pizzaPricePanel=new JPanel();
                pizzaPricePanel.setSize(500,50);
                JLabel pizzaPrice=new JLabel("Price");
                pizzaPricePanel.add(pizzaPrice);
                //the third panel
                JPanel pizzaIngredientPanel=new JPanel();
                pizzaIngredientPanel.setSize(500,50);
                JLabel pizzaIngredients=new JLabel("Ingredients");
                pizzaIngredientPanel.add(pizzaIngredients);
                //the return button
                JButton back=new JButton("Back");
                JPanel returnMenu=new JPanel();
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    pizzaFrame.dispose();
                    createWindow();
                    }
                });
                returnMenu.add(back);

                showPizza.add(pizzaNamePanel);
                showPizza.add(pizzaPricePanel);
                showPizza.add(pizzaIngredientPanel);
                showPizza.add(returnMenu);
                pizzaFrame.add(pizzaPanel);
                pizzaFrame.add(showPizza);
                pizzaFrame.setVisible(true);

            }
        });

    }



    public  static void chooseDrink(JButton drink,JFrame frame){

        drink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame drinkFrame=new JFrame("Please order what kind of drink you want !!");
                JPanel drinkPanel=new JPanel();
                drinkFrame.setLayout(new GridLayout(2,1));

                drinkFrame.setSize(640,400);
                drinkPanel.setSize(640,200);
                //merge with db
                String[] pizza={"cola","finta","juice"};
                JComboBox<String > drinkMenu=new JComboBox<>(pizza);
                drinkMenu.setSelectedIndex(2);
                drinkMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox comboBox=(JComboBox)e.getSource();
                        JOptionPane.showMessageDialog(drinkFrame,comboBox.getSelectedItem());
                    }
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
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        drinkFrame.dispose();
                        createWindow();

                    }
                });


                showPrice.add(drinkNamePanel);
                returnMenu.add(back);
                showPrice.add(returnMenu);
                drinkFrame.add(drinkPanel);
                drinkFrame.add(showPrice);

                drinkFrame.setVisible(true);

            }
        });

    }

    public  static void chooseDessert(JButton dessert,JFrame frame){

        dessert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dessertFrame=new JFrame("Please order what kind of dessert you want !!");
                JPanel dessertPanel=new JPanel();
                dessertFrame.setLayout(new GridLayout(2,1));

                dessertFrame.setSize(640,400);
                dessertPanel.setSize(640,200);
                //merge with db
                String[] dessertChoice={"cake","black cake","vantage cake"};
                JComboBox<String > dessertMenu=new JComboBox<>(dessertChoice);
                dessertMenu.setSelectedIndex(2);
                dessertMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox comboBox=(JComboBox)e.getSource();
                        JOptionPane.showMessageDialog(dessertFrame,comboBox.getSelectedItem());
                    }
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
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       dessertFrame.dispose();
                       createWindow();
                    }
                });
                returnMenu.add(back);

                showPrice.add(dessertNamePanel);
                showPrice.add(returnMenu);
                dessertFrame.add(dessertPanel);
                dessertFrame.add(showPrice);
                dessertFrame.setVisible(true);

            }
        });

    }








}














