/********************************************
*   TrigCalcV3
*********************************************
*   PROGRAM DESCRIPTION:
*   Main class for trig program.
*   Allows the user to choose between a trig calculator
*   and a unit circle quiz.
*********************************************
*   ALGORITHM:
*   Start program  
*   Create Scanner for input  
*   LOOP mainMenu:
*     1 TrigCalculator.calculateTrig()
*     2 Flashcard.runFlashcard()
*     3 Quit
*     else -> Show error
*********************************************
*   STATIC METHODS:
*   main(String[] args)            
*   displayMenu()
*   getChoice(Scanner input)
*   handleChoice(int choice) 
*********************************************
*   IMPORTED PACKAGES NEEDED AND PURPOSE:
*   Scanner - used for console input
*********************************************/

import java.util.Scanner;

public class TrigCalcV3 {

    /***** CONSTANT ARRAYS *****/
    
    /** Names of trig functions used in the program. */
    public static final String[] TRIG_FUNCTIONS = {"Sine", "Cosine", "Tangent"};

    /** Radian values used for reference in calculations and flashcards. */
    public static final String[] RADIANS = 
    {
        "0", "π/6", "π/4", "π/3", "π/2", "2π/3", "3π/4", "5π/6", "π"
    };

    /** 
     * Exact trig values organized by function (rows) and angle (columns).
     * Index order aligns with TRIG_FUNCTIONS and RADIANS.
     */
    public static final String[][] EXACT_VALUES = 
    {
        {"0", "1/2", "√2/2", "√3/2", "1", "√3/2", "√2/2", "1/2", "0"},       // Sine
        {"1", "√3/2", "√2/2", "1/2", "0", "-1/2", "-√2/2", "-√3/2", "-1"},   // Cosine
        {"0", "√3/3", "1", "√3", "undef", "-√3", "-1", "-√3/3", "0"}         // Tangent
    };

    /***** MAIN METHOD *****/

    /**
     * Entry point of the program. Displays the menu and handles user input.
     * @param args not used
     */
    public static void main(String[] args) 
    {
        System.out.println("===============================");
        System.out.printf("%21s%n", "TrigCalc v3");
        System.out.printf("%23s%n", "Tools for Trig!");
        System.out.println("===============================");
        
        Scanner input = new Scanner(System.in);

        int choice;
        do 
        {
            displayMenu();
            choice = getChoice(input);
            handleChoice(choice);
        } 
        while (choice != 3);

        input.close();
    }

    /***** DISPLAY MENU *****/

    /**
     * Shows the main tool selection menu to the user.
     */
    public static void displayMenu() 
    {
        System.out.println("\n========== TOOL MENU ==========");
        System.out.println("1. Calculate a trig value");
        System.out.println("2. Unit Circle Flashcards");
        System.out.println("3. Quit");
        System.out.print("\nEnter your choice (1–3): ");
    }

    /***** GET USER CHOICE *****/

    /**
     * Reads and returns the user's menu choice.
     * @param input Scanner used to read input
     * @return the user's choice as an int, or -1 if invalid
     */
    public static int getChoice(Scanner input) 
    {
        int choice = -1;

        if (input.hasNextInt()) 
        {
            choice = input.nextInt();
            input.nextLine(); // clear buffer
        } 
        else 
        {
            input.nextLine(); // discard invalid input
        }

        return choice;
    }

    /***** HANDLE USER CHOICE *****/

    /**
     * Handles logic based on the user's menu choice.
     * @param choice the selected menu option
     */
    public static void handleChoice(int choice) 
    {
        if (choice == 1) 
        {
           TrigCalculator.calculateTrig();
        } 
        else if (choice == 2) 
        {
            FlashcardFactory.runFlashcard();
        } 
        else if (choice == 3) 
        {
            System.out.println("\nGoodbye!");
        } 
        else 
        {
            System.out.println("Invalid choice. Try again.");
        }
    }
}