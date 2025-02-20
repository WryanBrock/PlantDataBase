package org.self.utils;

import org.self.dao.DBConnect;
import org.self.dao.impl.PlantImpl;
import org.self.model.Plant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operation {

    private static final Logger LOGGER = Logger.getLogger(Operation.class.getName());

    public static void menu(){
        System.out.println("================================");
        System.out.println("__Please select a option__.");
        System.out.println("__Press 1 to Add a Plant__");
        System.out.println("__Press 2 to View a Plant__");
        System.out.println("__Press 3 to Update a Plant__");
        System.out.println("__Press 4 to Delete a Plant__");
        System.out.println("__Press 5 to look up Plant id__");
        System.out.println("__Press 6 to Exit__");
        System.out.println("================================");

    }
    public static void addPlant(){
        String name = "";
        int height_cm = 0;
        boolean is_herb = false;
        boolean is_pern = false;
        String flower_time = "";
        String sprout_time= "";
        int minSum_hr = 0;
        String flower_color = "";

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name:");
        name = scan.nextLine();
        System.out.println("Please enter the height in cm:");
        height_cm = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter if the plant is a herb (true/false):");
        is_herb = scan.nextBoolean();
        scan.nextLine();
        System.out.println("Please enter if the plant is a Perennial (true/false):");
        is_pern = scan.nextBoolean();
        scan.nextLine();
        System.out.println("Please enter the flowering time in months (e.g., Apr - Sept):");
        flower_time = scan.nextLine();
        System.out.println("Please enter the sprout time in months (e.g., Jan - Mar):");
        sprout_time = scan.nextLine();
        System.out.println("Please enter the minimum hours in full sun:");
        minSum_hr = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter the color of the flower:");
        flower_color = scan.nextLine();

        Plant plant =  new Plant(name, height_cm, is_herb, is_pern, flower_time, sprout_time, minSum_hr, flower_color);
        try (Connection connection = DBConnect.getConnection()) {
            new PlantImpl(connection).create(plant);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding plant: " + e);
        }
    }
    public static void whichView() {
        System.out.println("================================");
        System.out.println("__Please select a option__.");
        System.out.println("__Press 1 to View All__");
        System.out.println("__Press 2 to View Perennials__");
        System.out.println("__Press 3 to Sort by Color__");
        System.out.println("__Press 4 to Sort by Height__");
        System.out.println("__Press 5 to View Sprout times__");
        System.out.println("__Press 6 to View Flower times__");
        System.out.println("__Press 7 to go back__");
        System.out.println("================================");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                viewAll();
                break;
            case 2:
                viewPeren();
                break;
            case 3:
                sortByColor();
                break;
            case 4:
                sortByHeight();
                break;
            case 5:
                sproutTimes();
                break;
            case 6:
                flowerTime();
                break;
            case 7:
                break;
            default:
        }
    }

    private static void viewAll() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).findAll();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    private static void viewPeren() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).allPere();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    private static void sortByColor() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).sortByColor();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    private static void sortByHeight() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).sortByHeight();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    private static void sproutTimes() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).sproutTimes();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    private static void flowerTime() {
        try (Connection connection = DBConnect.getConnection()) {
            ArrayList<Plant> plants = new PlantImpl(connection).FlowerTimes();
            for(Plant plant : plants){
                System.out.println(plant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    public static void updatePlant() {
        String name = "";
        int height_cm = 0;
        boolean is_herb = false;
        boolean is_pern = false;
        String flower_time = "";
        String sprout_time= "";
        int minSum_hr = 0;
        String flower_color = "";
        boolean hasId = true;
        String ans = "";

        Scanner scan = new Scanner(System.in);

        System.out.println("Do you have the plant id?");
        ans = scan.nextLine();

        if(ans.equals("no") || ans.equals("No")){
            hasId = false;
        }

        if (hasId){
        System.out.println("Please enter the name:");
        name = scan.nextLine();
        System.out.println("Please enter the height in cm:");
        height_cm = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter if the plant is a herb:");
        is_herb = scan.nextBoolean();
        scan.nextLine();
        System.out.println("Please enter if the plant is a Perennial:");
        is_pern = scan.nextBoolean();
        scan.nextLine();
        System.out.println("Please enter the flowering time in months:");
        flower_time = scan.nextLine();
        System.out.println("Please enter the sprout time in months:");
        sprout_time = scan.nextLine();
        System.out.println("Please enter the minimum hours in full sun:");
        minSum_hr = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter the color of the flower:");
        flower_color = scan.nextLine();
        System.out.println("Please enter the id of the plant being updated:");
        int plant_id = scan.nextInt();

        Plant plant =  new Plant(plant_id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, minSum_hr, flower_color);

        try (Connection connection = DBConnect.getConnection()) {
            new PlantImpl(connection).update(plant);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
        }
    }
    public static void deletePlant() {
        System.out.println("Please enter the ID of the plant");
        Scanner scanner =  new Scanner(System.in);
        int plant_id = scanner.nextInt();
        try (Connection connection = DBConnect.getConnection()) {
            new PlantImpl(connection).delete(plant_id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
    }
    public static void lookUpPlant() {
        int ans = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the plant?");
        String name = scanner.nextLine();
        try (Connection connection = DBConnect.getConnection()) {
            ans = new PlantImpl(connection).lookUp(name);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plants: ", e);
        }
        System.out.println(ans);
    }
}
