/*
    * WORKING - COMPLETE
*/

public class Validation {

    boolean validateInts(String input, int length) /* Checks if inputs are 1. Integers 2. Within bounds of choices */
    {
        try {
            int validInput = Integer.parseInt(input);
            return (validInput >= 1) && (validInput <= length+1);
        } catch (NumberFormatException e) {
         return false;
        }
    }
//github
    boolean validateDoubles(String input) /* Checks if inputs are doubles */
    {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
