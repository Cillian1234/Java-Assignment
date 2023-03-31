/*
    * WORKING - COMPLETE *
*/

import java.io.*;
import java.util.*;

public class AssignItems {
    ArrayList<Double> prices = new ArrayList<>(); // Arraylists for prices and names of items which we will return to the main class
    ArrayList<String> items = new ArrayList<>();

    AssignItems() /* Reads inventory file and assigns each line to items arraylist, currently does not handle prices separately */
    {
        Validation validator = new Validation(); // Creates validator object
        ArrayList<String> tempList = new ArrayList<>(); // Temporary arraylist to load the initial strings from inventory file into, these strings are entire lines from the file
        try {
            File inventory = new File("inventory.csv"); // Create file object called inventory with path to inventory text file
            Scanner reader = new Scanner(inventory); // Scanner object to read contents of inventory file
            while (reader.hasNextLine()) /* While there is a next line in inventory file this will continue to add them to the temporary arraylist */
            {
                tempList.add(reader.nextLine());
            }
            reader.close(); // Close reader object as we are done with it
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("!! Error in inventory file, check filepath is correct !!");
            e.printStackTrace();
        }

        Collections.sort(tempList);

        String[] splitArray; // Temporary array used by .split method to store strings created after split
        for (String wholeItem : tempList) { // Loop splits each string in tempList
            splitArray = wholeItem.split(","); // Splits strings at the commas
            for (String splitString : splitArray) { // Nested loop checks if splitString could be a double, if true then parse to a double and add to prices ArrL
                if (validator.validateDoubles(splitString)) {
                    prices.add(Double.parseDouble(splitString));
                } else { // Else the string must be the item name, add it to the items array as is
                    items.add(splitString);
                }
            }
        }
    }

    public ArrayList<String> getItems() /* Returns items arraylist */
    {
        return items;
    }
    public ArrayList<Double> getPrices() /* Returns prices arraylist */
    {
        return prices;
    }
}