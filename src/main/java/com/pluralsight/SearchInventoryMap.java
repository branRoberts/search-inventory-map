package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
        } else {
            System.out.printf("We carry %s and the price is $%.2f",
                    matchedProduct.getName(), matchedProduct.getPrice());
        }
    }

    private static void loadInventory(){
        // Your code should be here
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\\|");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                Product product = new Product(id, name, price);
                inventory.put(id, product);
            }
            reader.close();
        }catch (Exception e){
            System.err.println("Error loading Inventory");
        }
    }

}
