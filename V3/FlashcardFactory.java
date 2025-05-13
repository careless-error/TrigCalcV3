/**
 * -----------------------------------------
 * FlashcardFactory
 * -----------------------------------------
 * 
 * Runs the flashcard quiz portion of the trig tool.
 * Randomly selects a trig function and angle, then quizzes the user
 * on the exact trig value. Tracks score and offers multiple attempts.
 * 
 * Author: Jeff Peterson
 * 
 * -----------------------------------------
 * UML CLASS DIAGRAM
 * -----------------------------------------
 * + getRandomFlashcard()
 * + runFlashcard()
 * -----------------------------------------
*/

import java.util.Scanner;

public class FlashcardFactory
{

    /**
     * Main flashcard quiz loop.
     * Prompts the user with a trig question, takes input, checks correctness,
     * and tracks score. User can go again, return to menu, or quit.
     */
    public static void runFlashcard()
    {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        int correctCount = 0;
        int totalCount = 0;

        // Welcome banner
        System.out.println("\n                    ╔═══════════════════════════╗");
        System.out.println("                    ║ Memorize Your Unit Circle ║");
        System.out.println("                    ╚═══════════════════════════╝");

        // Loop through questions until user quits or returns to menu
        while (keepGoing) 
        {
            // Generate random flashcard (function, angle, exact value)
            Flashcard correct = getRandomFlashcard();

            // Ask the question
            System.out.println("\n    What is " + correct.getTrigFunction() + "(" + correct.getRadian() + ")?");
            System.out.println("══════════════════════════════════════════════════════════════════════");
                

            // Show options 1–7 from Cosine row
            for (int i = 1; i <= 7; i++) 
            {
                System.out.printf("%2d. %-6s", i, TrigCalcV3.EXACT_VALUES[1][i]);
            }
            System.out.println("\n");
            
            // Show options 8–14 from Tangent row
            for (int i = 1; i <= 7; i++) 
            {
                System.out.printf("%2d. %-6s", i + 7, TrigCalcV3.EXACT_VALUES[2][i]);
            }
            System.out.println("\n══════════════════════════════════════════════════════════════════════");

            // Get a valid user input (must be 1–14)
            int userChoice = -1;
            while (userChoice < 1 || userChoice > 14) 
            {
                System.out.print("Select your answer (1–14): ");

                if (input.hasNextInt()) 
                {
                    userChoice = input.nextInt();
                    input.nextLine(); // flush newline
                } 
                else 
                {
                    input.nextLine(); // clear invalid input
                }
            }

            // Get the value associated with that answer number
            String userValue;
            if (userChoice <= 7) 
            {
                userValue = TrigCalcV3.EXACT_VALUES[1][userChoice]; // Cosine
            } 
            else 
            {
                userValue = TrigCalcV3.EXACT_VALUES[2][userChoice - 7]; // Tangent
            }

            // Build the user's flashcard and check correctness
            Flashcard userCard = new Flashcard(correct.getTrigFunction(), correct.getRadian(), userValue);
            totalCount++;
            
            System.out.println();
            System.out.println("    You chose: " + userCard);
            System.out.println();

            // Give feedback
            if (userCard.equals(correct)) 
            {
                correctCount++;
                System.out.println("                            ✨ Correct!✨");
            } 
            else 
            {
                System.out.println("                           👎  Do Better 👎");
            }

            // Show the right answer
            System.out.println("                    ╔═══════════════════════════╗");
            System.out.println("                    ║                           ║");
            System.out.printf("                    ║%5s%-18s%4s║%n", "", correct, "");
            System.out.println("                    ║                           ║");
            System.out.println("                    ╚═══════════════════════════╝");

            // Score so far
            System.out.println("                          Score: " + correctCount + " out of " + totalCount);
            System.out.println("\n══════════════════════════════════════════════════════════════════════");

            // Ask what to do next. Keep asking until the user enters "g", "m", or "q"

            String choice = "";

            while (!choice.equalsIgnoreCase("g") && 
                   !choice.equalsIgnoreCase("m") && 
                   !choice.equalsIgnoreCase("q")) 
            {
                System.out.print("Go again (g), return to menu (m), or quit (q): ");
                choice = input.nextLine();
            }

            // Handle menu choice
            if (choice.equalsIgnoreCase("q")) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            } 
            else if (choice.equalsIgnoreCase("m")) {
                keepGoing = false;
            }

            if (keepGoing) {
                System.out.println(); // spacer before next question
            }
        }
    }


    /**
     * Randomly generates a Flashcard by selecting a trig function and angle.
     * Pulls the correct value from the exact value table based on row/col.
     * 
     * @return a Flashcard with the function, angle, and correct value
     */
    public static Flashcard getRandomFlashcard() 
    {
        int funcIndex = (int)(Math.random() * TrigCalcV3.TRIG_FUNCTIONS.length);
        int angleIndex = (int)(Math.random() * TrigCalcV3.EXACT_VALUES[0].length);

        String func = TrigCalcV3.TRIG_FUNCTIONS[funcIndex];
        String angle = TrigCalcV3.RADIANS[angleIndex];
        String exact = TrigCalcV3.EXACT_VALUES[funcIndex][angleIndex];

        return new Flashcard(func, angle, exact);
    }
}