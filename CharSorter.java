import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
public class CharSorter {


    public static int printMenu(){ // This method prints a user menu with directions
        int userChoice;
        Scanner scnr = new Scanner(System.in);

        System.out.println("Please select the option you would like to see ");
        System.out.println();
        System.out.println("1. Display character frequencies alphabetically");
        System.out.println("2. Display sorted frequencies");
        System.out.println("3. Show types of character frequencies");
        System.out.println("4. Exit");

        while (true) { // checking for non integer input
            try {
                userChoice = scnr.nextInt();
                break;
            } catch (InputMismatchException e) {
                scnr.nextLine();
                System.out.println("Error, bad input, please enter a number 1-4");
                System.out.println("Choose an option: ");
            }
        }

        while ((userChoice < 0) || (userChoice > 4)) { // checking for out of bounds input
            System.out.println("Error, bad input, please enter a number 1-4");
            System.out.println("Choose an option: ");
            scnr.nextLine();
            while (true) { // checking yet again for non int input
                try {
                    userChoice = scnr.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    scnr.nextLine();
                    System.out.println("Error, bad input, please enter a number 1-4");
                    System.out.println("Choose an option: ");
                }
            }
        }


        return userChoice;
    }


    public static int[][] getFrequencies(String bufu) {
        /* this method constructs a 2d array
        [a][b] where a is the character and b the frequency */
        int[] alpha = new int[bufu.length()]; // array for integer values for characters in a string
        int[] alpha1 = new int[bufu.length()]; // [a] character * as an int (almost alpha[])
        int[] alpha2 = new int[bufu.length()]; // [b] frequency
        int tempVar; // temporary variable to rearrange characters in alphabetical order
        int f = 0; // final index, used to differentiate from m in recursion
        int[][] result; // result of this method (2d array)

        for (int i = 0; i < bufu.length(); i++) {
            alpha[i] = (int) bufu.charAt(i); // defining array as the int value of the character at a point
            for (int j = 0; j < bufu.length(); j++) { // loop inside a loop
                if (alpha[i] < alpha[j]) { // rearranges in alphabetical order
                    tempVar = alpha[j]; // tempVar is defined to perform each swap needed
                    alpha[j] = alpha[i];
                    alpha[i] = tempVar;
                }
            }
        }

            for (int m = 0; (m < (bufu.length())); m++) {
                if (m == (bufu.length() - 1)) { // if this is the last value in the string
                    alpha1[f] = alpha[m]; //recording the character (int form)
                    alpha2[f] = alpha2[f] + 1; //adding 1 to the frequency value
                }
                else if (alpha[m] != alpha[m + 1]) {//if two adjacent values are different
                    alpha1[f] = alpha[m];
                    alpha2[f] = alpha2[f] + 1; // add 1 to the frequency value
                    f++;// going to the next index for the new character
                } else if (alpha[m] == alpha[m + 1]) { // if values are the same
                    alpha2[f] = alpha2[f] + 1;
                    }
                }


        result = new int[][]{alpha1,alpha2}; /* A 2d array comprised of two 1d arrays
        [character(int value)][frequency] alphabetized
        */

        return result; // to be used by other methods


    }

    public static void alphabeticalSort(int[][] result) { /* prints out characters and
    frequencies in alphabetical order*/
        for (int k = 0; k < result[1].length; k++) { // loops for all values in the array
            if (result[1][k] != 0) { // ignoring 0 frequency characters which can not exist
                System.out.println((char) result[0][k] + " freq: " + result[1][k]);
            }
        }
        System.out.println();
    }


   public static void frequencySort(int[][] result)
    {
        int tempVar; // for the second column
        int tempVar2; // for the first column
        int tempVar3; // for first column when alphabetizing is necessary
        int tempVar4; // for second column when alphabetizing is necessary

        for (int i = 0; i < result[1].length; i++) {
            for (int j = 0; j < result[1].length; j++) { // loop inside a loop to reorganize
                if (result[1][i] > result[1][j]) { // from greatest to least frequent (by 2nd column)
                    tempVar = result[1][j];
                    result[1][j] = result[1][i];
                    result[1][i] = tempVar;
                    tempVar2 = result[0][j]; // takes first column with the second column
                    result[0][j] = result[0][i];
                    result[0][i] = tempVar2;

                }
            }
        }
        for (int k = 0; k < result[0].length; k++) {
            for (int l = 0; l < result[0].length; l++) {
                if ((result[0][k] < result[0][l]) && (result[1][k] == result[1][l])) { /*
                If two frequencies are equal, this loop will organize them alphabetically
                while leaving them in their frequency groups. */
                    tempVar3 = result[0][l];
                    result[0][l] = result[0][k];
                    result[0][k] = tempVar3;
                    tempVar4 = result[1][l]; // takes first column with the second column
                    result[1][l] = result[1][k];
                    result[1][k] = tempVar4;

                }
            }

        }

        for (int m = 0; m < result[1].length; m++) {
            if (result[1][m] != 0) {
                System.out.println((char)result[0][m] + " freq: " + result[1][m]);
            }
        }
        System.out.println();
    }

     public static void charTypes(String result)
     {

         int txtCount = 0;
         int numCount = 0;
         int whiteCount = 0;
         int symbolCount = 0;

         int[] result0 = new int [result.length()]; // defining an array from a string
         for (int j = 0; j < result.length(); j++) {
             result0[j] = (int) result.charAt(j); // this method uses int values for characters
         }

         for (int i = 0; i < result.length(); i++) {
             if ((result0[i] <= 122) && (result0[i] >= 65)) { //ascii values for text
                 txtCount = txtCount + 1;
             }
             else if ((result0[i] <= 57) && (result0[i] >= 48)) { // ascii values for numbers
                 numCount = numCount + 1;
             }
             else if ((result0[i] == 32) || ((result0[i] >= 9) && (result0[i] <= 13))) { //whitespace
                 whiteCount = whiteCount + 1;
             }
             else { // everything else is some sort of symbol
                 symbolCount = symbolCount + 1;
             }
         }

             System.out.println("Textual Character count: " + txtCount);
             System.out.println("Numerical Character count: " + numCount);
             System.out.println("WhiteSpace Character count: " + whiteCount);
             System.out.println("Symbol Character count: " + symbolCount);
             System.out.println();
     }
    public static void main(String[]args) {

        int userChoice;
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to Character Sorter Program");
        System.out.println("Please input a string to be sorted");

        String string = scnr.nextLine(); // name of a the string
        System.out.println();

        userChoice = printMenu(); // calls menu method and runs it to get a choice

        while (userChoice != 4) { // loops as long as 4 is not entered
            if (userChoice == 1) {
                alphabeticalSort(getFrequencies(string));
            }
            if (userChoice == 2) {
                frequencySort(getFrequencies(string));
            }
            if (userChoice == 3) {
                charTypes(string);
            }
            /* Method Calling Syntax
            innermost is the argument, then the method being called
            methods are called from the inside first, their results are
            then used ty the next outer method and so on
             */

            userChoice = printMenu(); // allows the user to make another choice
        }
    }

    }




















