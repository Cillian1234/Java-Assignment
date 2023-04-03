/*
    ? WORKING ON GOD KNOWS WHAT ?
    ! BUILD BROKEN !
*/

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/*
* All user input will be strictly integers and doubles (except it isn't anymore there is one string input), but they will be taken in strings and checked to confirm if they are numbers. This means there is a lot of parsing to ints and doubles in here
*/

public class ProjectMain {
        static Validation validator = new Validation(); // Creates validator object
        static AssignItems assigner = new AssignItems(); // Creates arraylists containing items
        static Scanner sc = new Scanner(System.in); // Creates scanner used for user input
    public static void main(String[] args) {
        System.out.println("\nWelcome to the cafe");

        // While is set to a temporary value that is always true, this will probably be reworked later on
        while (true) {
            
            ArrayList<String> items = assigner.getItems(); // Assigns items from assigner object to arraylist to make it simpler to use
            ArrayList<Double> prices = assigner.getPrices(); // " prices "

            int drinkSelection = 0; // Stores index of drink selection in items ArrayList, this index also points to price of drink in prices ArrayList
            int transactionType = 0; // Stores transaction type returned by transactionType(), used in writeTransactions()
            String cardType = null; // Stores card type used, used in writeTransactions()
            double cashTendered = 0; // Stores cash tendered in cash transactions
            boolean stepComplete = false; // Runs while loops

            // Handles running of drinkMenu and exit from menu
            while (!stepComplete) {
                drinkSelection = drinkMenu(items, prices);
                if (drinkSelection == items.size()) { // items.size will be the same as the number for the "exit" option
                    stepComplete = exitConfirmation(); // exitConfirmation returns a boolean
                } else {
                    stepComplete = true;
                }
            }

            stepComplete = false; // Reset to false to run more whiles

            // Handles choosing transaction type, running cash calculations or choosing card type
            while (!stepComplete) {
                transactionType = transactionType(); // Transaction type used to pick between card and cash, this is used when writing transactions later
                if (transactionType == 1) {
                    cashTendered = cashMaths(prices, drinkSelection); // Returns double
                    stepComplete = true;
                } else if (transactionType == 2) {
                    cardType = cardType(prices, drinkSelection); // Returns string
                    stepComplete = true;
                } else {
                    stepComplete = exitConfirmation();
                }
            }
            writeTransactions(items, prices, drinkSelection, transactionType, cashTendered, cardType); // Writes current transaction to history !! runs after each individual transaction !!
        }
    }

    static int drinkMenu(ArrayList<String> items, ArrayList<Double> prices) /* Print drinks menu and takes input for choice + validates it, returns position of choice in arraylist */
    {
        boolean valid = false; // While loops
        String selection = null; // Holds index of selection

        System.out.println("\nPlease choose an item:");
        for (int i = 1; i <= items.size(); i++) {
            System.out.printf("%-3d| %-20s %.2f\n", i, items.get(i-1), prices.get(i-1)); // Prints out numbered menu with drink name and price
        }

        System.out.printf("%-3d| Exit \n",items.size()+1); // Prints out numbered exit option

        while (!valid) {
            System.out.println("Please make a selection by entering the number of item: ");
            selection = sc.nextLine();
            selection = validator.removeSpaces(selection); // Removes spaces in input which caused errors
            valid = validator.validateInts(selection, items.size()); // Checks input is an integer and within acceptable range, returns boolean
        }
        return Integer.parseInt(selection)-1; // Returns index of selection in ArrayList, also corresponds to price in prices ArrayList
    }

    static int transactionType() /* Menu for choosing transaction type, returns string containing the transaction type. String returned is printed into transaction history */
    {
        boolean valid = false; // Used to run while loops
        String transactionChoice = null; // Used to pick between card and cash

        while (!valid) {
            System.out.println("Choose transaction type:");
            System.out.println("1. Cash \n2. Card \n3. Exit");
            System.out.println("Please make a selection by entering the number of item: ");
            transactionChoice = sc.nextLine();
            transactionChoice = validator.removeSpaces(transactionChoice); // Removes spaces in input which caused errors
            valid = validator.validateInts(transactionChoice, 2); // Checks input is an integer and within acceptable range, returns boolean
        }

        switch (Integer.parseInt(transactionChoice)) {
            case 1: return 1; // Return 1 to choose Cash
            case 2: return 2; // Return 2 to choose Card
            case 3: return 3; // Return 3 to run exit menu
        }
        return 0; // Redundant return, program will never make it to here, but it won't compile without this
    }

    static double cashMaths(ArrayList<Double> prices, int priceIndex) /* Allows you to enter cash given and calculates change due */
    {
        boolean valid = false; // While loops
        boolean cashGiven = false; // Use to check cash given is enough to pay
        double cashTenderedDouble = 0; // Initialise variable used when user string input is parsed to double
        String cashTendered; // String input taken from user

        while (!valid) {
            System.out.printf("Cash transaction: \nPrice €%.2f \nEnter amount tendered: \n", prices.get(priceIndex));
            cashTendered = sc.nextLine(); // Removing spaces not necessary
            valid = validator.validateDoubles(cashTendered); // Checks input is a positive double, returns boolean
            if (valid) {
                cashTenderedDouble = Double.parseDouble(cashTendered); // Used in maths so parse string to double once here instead of several times later on
            }
        }

        if (cashTenderedDouble >= prices.get(priceIndex)) { // Checks if amount entered is sufficient to pay for item
            System.out.printf("\nChange due = €%.2f \n", (cashTenderedDouble - prices.get(priceIndex))); // Calculates and prints change due
        } else if (cashTenderedDouble <= prices.get(priceIndex)) { // if amount entered is not sufficient to pay for item
            while (!cashGiven) { // Reruns code while total amount of money given is not enough to pay for item

                System.out.printf("You are €%.2f short \n", (prices.get(priceIndex) - cashTenderedDouble)); // Calculates and prints how much money you are short of price
                System.out.println("Please enter extra cash given or type CANCEL to cancel transaction: ");
                String extraCash = sc.nextLine(); // Removing spaces not necessary

                if (validator.validateDoubles(extraCash)) { // Checks input is a positive double, returns boolean
                    double extraCashDouble = Double.parseDouble(extraCash); // Also used in maths so parsed to double
                    cashTenderedDouble = cashTenderedDouble + extraCashDouble; // Add extra cash to total given
                    if (cashTenderedDouble >= prices.get(priceIndex)) { // If total is more than price of item, break loop
                        System.out.printf("\nChange due = €%.2f \n", (cashTenderedDouble - prices.get(priceIndex))); // Calculates and prints change due
                        cashGiven = true;
                    }
                } else if (extraCash.equalsIgnoreCase("CANCEL")) { // If customer can't afford item, typing cancel will abort the transaction
                    return -1; // -1 is impossible to return unless the code runs through this branch, used to save transaction as CANCELLED
                }
            }
        }

        System.out.println("Thank you for your purchase!");
        return cashTenderedDouble; // Return double of total cash tendered
    }

    static String cardType(ArrayList<Double> prices, int priceIndex) /* Allows you to choose which type of card payment is made with */
    {
        boolean valid = false; // Runs while loops
        String cardType = null; // Used to pick between visa and mastercard

            while (!valid) {
                System.out.printf("Card transaction: \nPrice €%.2f \nChoose card type: \n1. Visa\n2. Mastercard\n", prices.get(priceIndex));
                System.out.println("Please make a selection by entering the number of item: ");
                cardType = sc.nextLine();
                cardType = validator.removeSpaces(cardType); // Removes spaces in input which caused errors
                valid = validator.validateInts(cardType, 1); // Checks input is an integer and within acceptable range, returns boolean
            }
            switch (Integer.parseInt(cardType)) {
                case 1: return "Visa";
                case 2: return "MasterCard";
            }
        return ""; // Redundant return, program will never make it to here, but it won't compile without this
    }

    static boolean exitConfirmation() /* Menu for when exit is chosen, will either terminate program or return to process in which exit was chosen */
    {
        String selection = null; // Used to store user input
        boolean valid = false; // Used to run while loop
        
        while (!valid) {
            System.out.println("Are you sure you would like to exit? \n1. Yes\n2. No");
            System.out.println("Please make a selection by entering the number of item: ");
            selection = sc.nextLine();
            selection = validator.removeSpaces(selection); // Removes spaces in input which caused errors
            //Checks input is an integer and within acceptable range, returns boolean // Boundary of 2 hard coded because there will only ever be two options here
            valid = validator.validateInts(selection, 2);
        }
        switch (Integer.parseInt(selection)) {
            case 1: System.exit(1); // If you choose option 1, "Yes", The program will end
            case 2: return false; // If you choose option 2, "No", return false which causes while loop for whatever step of the process you are on to continue running, so you can continue with transaction
        }
        return true; // Redundant return, program will never make it to here, but it won't compile without this
    }

    static void writeTransactions(ArrayList<String> items, ArrayList<Double> prices, int drinkSelection, int transactionType, double cashTendered, String cardType) /* Writes drink name and price to history file along with date and time of sale */
    {
        if (cashTendered == -1) { // -1 is returned by cashMaths() if the order is aborted, impossible to return this number otherwise
            try {
                FileWriter transactionWrite = new FileWriter("TransactionHistory.txt", true); // Opens filewriter in transaction history file, set to append to file
                transactionWrite.write("\nItem purchased: " + items.get(drinkSelection) + "\nPrice: " + prices.get(drinkSelection) + "\nTransaction type: CANCELLED" + "\n@ " + dateTime() + "\n"); // Writes everything to file CANCELLED TRANSACTION
                transactionWrite.close(); // Closes filewriter
                System.out.println("Transaction written to history"); // Confirmation message
            } catch (IOException e) {
                System.out.println("An error occurred while writing to history");
            }
        } else {
            try {
                FileWriter transactionWrite = new FileWriter("TransactionHistory.txt", true); // Opens filewriter in transaction history file, set to append to file
                if (transactionType == 1) {
                    transactionWrite.write("\nItem purchased: " + items.get(drinkSelection) + "\nPrice: " + prices.get(drinkSelection) + "\nTransaction type: Cash: " + "Cash tendered: " + cashTendered
                            + "\n@ " + dateTime() + "\n"); // Writes everything to file
                } else if (transactionType == 2) {
                    transactionWrite.write("\nItem purchased: " + items.get(drinkSelection) + "\nPrice: " + prices.get(drinkSelection) + "\nTransaction type: Card: " + cardType
                            + "\n@ " + dateTime() + "\n"); // Writes everything to file
                }
                transactionWrite.close(); // Closes filewriter
                System.out.println("Transaction written to history"); // Confirmation message
            } catch (IOException e) {
                System.out.println("An error occurred while writing to history");
            }
        }
    }

    static String dateTime() /* Gets and formats date for transaction history write */
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // Formats date and time
        return dateTime.format(dateTimeFormatter); // Returns date and time as a String
    }
}
