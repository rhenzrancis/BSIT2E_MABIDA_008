package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao dao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        int choice;
        do {
            System.out.println("==================================================");
            System.out.println("           COFFEE SHOP ORDERS SYSTEM              ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Order");
            System.out.println("[2] View Active Orders");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter choice: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> handleAddOrder();
                case 2 -> handleViewOrders();
                case 3 -> System.out.println("\nExiting system... Thank you!");
                default -> System.out.println("\nInvalid choice! Please select between 1 and 3.\n");
            }
        } while (choice != 3);
    }

    private void handleAddOrder() {
        System.out.println("\n--- ADD NEW ORDER ---");
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Drink Item: ");
        String drinkItem = scanner.nextLine();

        System.out.print("Enter Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid price. Enter Price: ");
            scanner.next();
        }
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Status (PENDING / PREPARING / COMPLETED): ");
        String status = scanner.nextLine().toUpperCase();

        dao.addOrder(customerName, drinkItem, price, status);
        System.out.println("\nSUCCESS: Order added to queue!\n");
    }

    private void handleViewOrders() {
        List<ScenarioEntity> orders = dao.getAllOrders();

        System.out.println("\n=================================================================================");
        System.out.println("                             ACTIVE COFFEE ORDERS                                ");
        System.out.println("=================================================================================");
        System.out.println("+-----+---------------------+---------------------+-------------+-------------+");
        System.out.println("| ID  | CUSTOMER NAME       | DRINK ITEM          | PRICE       | STATUS      |");
        System.out.println("+-----+---------------------+---------------------+-------------+-------------+");

        for (ScenarioEntity o : orders) {
            System.out.printf("| %-3d | %-19s | %-19s | %-11.2f | %-11s |%n",
                    o.id(),
                    o.customerName(),
                    o.drinkItem(),
                    o.price(),
                    o.status());
        }

        System.out.println("+-----+---------------------+---------------------+-------------+-------------+");
        System.out.println("Total Records Displayed: " + orders.size());
        System.out.println("=================================================================================\n");
    }
}