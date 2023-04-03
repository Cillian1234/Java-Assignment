/*
    * WORKING - COMPLETE
*/

public class Validation {

    String removeSpaces(String input) /* Removes any spaces that may be entered by user as they caused crashes when trying to parse them later on */
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

    boolean validateDoubles(String input) /* Checks if inputs are positive doubles */
    {
        try {
            double validInput = Double.parseDouble(input);
            return (validInput>0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
