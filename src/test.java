
// I don't fucking know bro this shit busted

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Validation validator = new Validation(); // Creates validator object
        ArrayList<String> tempList = new ArrayList<>();

        try {
            File inventory = new File("invetory.txt");
            Scanner reader = new Scanner(inventory);
            while (reader.hasNextLine()) {
                tempList.add(reader.nextLine());
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<String> items = new ArrayList<>();

        String[] splitArray; // Temporary array used by .split method to store strings created after split
        for (int i = 0; i < tempList.size(); i++) { // Loop splits each string in tempList
            splitArray = tempList.get(i).split(",");
            for (String splitString : splitArray) { // Nested loop checks if splitString could be a double, if true then parse to a double and add to prices ArrL
                if (validator.validateDoubles(splitString)) {
                    prices.add(Double.parseDouble(splitString));
                } else { // Else the string must be the item name, add it to the items array as is
                    items.add(splitString);
                }
            }
        }

        System.out.println(items);
        System.out.println(prices);

    }
}
