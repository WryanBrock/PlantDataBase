package org.self;

import org.self.dao.DBConnect;
import org.self.dao.impl.PlantImpl;
import org.self.model.Plant;
import org.self.utils.Operation;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true){
            Operation.menu();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Operation.addPlant();
                    break;
                case 2:
                    Operation.whichView();
                    break;
                case 3:
                    Operation.updatePlant();
                    break;
                case 4:
                    Operation.deletePlant();
                    break;
                case 5:
                    Operation.lookUpPlant();
                    break;
                case 6:
                    System.exit(0);
                default:
            }
        }
    }
}