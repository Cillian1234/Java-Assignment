/*
    * WORKING - COMPLETE
*/

public class Validation {

    String removeSpaces(String input)
    {
        return input.replaceAll(" ", "");
    }

    boolean validateInts(String input, int length) /* Checks if inputs are 1. Integers 2. Within bounds of choices */
    {
        try {
            int validInput = Integer.parseInt(input);
            return (validInput >= 1) && (validInput <= length+1);
        } catch (NumberFormatException e) {
         return false;
        }
    }

    boolean validateDoubles(String input) /* Checks if inputs are doubles */
    {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validateCash(String input) /* Used to check if cash entered during transaction is more than 0 (prevents negative numbers) */
    {
        try {
            return Double.parseDouble(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
