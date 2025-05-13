/**
 * -----------------------------------------
 * TrigCalculator
 * -----------------------------------------
 * 
 * Trig calculator tool for exact and decimal values.
 * Accepts any radian input — if it's a special angle,
 * returns the exact trig value. If not, gives a decimal.
 * Handles inputs like "π/4", "2pi/3", and negative or co-terminal angles.
 * 
 * Author: Jeff Peterson
 * 
 * -----------------------------------------
 * UML CLASS DIAGRAM:
 * -----------------------------------------
 * + calculateTrig(): void
 * -----------------------------------------
*/
import java.util.Scanner;

public class TrigCalculator

{
    /**
     * Main calculator loop.
     * Ask user which function to use, then ask for any radian input.
     * If it's a special angle (one we know exact values for),
     * display the exact value — otherwise, show the decimal approximation.
     */
    public static void calculateTrig()
    {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;

        // Header
        System.out.println("\n===============================");
        System.out.printf("%21s%n", "Trig Calculator");
        System.out.printf("%22s%n", "Exact or Decimal");
        System.out.println("===============================\n");

        while (keepGoing)
        {
            //choose function
            int funcChoice = 0;
            while (funcChoice < 1 || funcChoice > 3) 
            {
                System.out.println("1. Sine");
                System.out.println("2. Cosine");
                System.out.println("3. Tangent");
                System.out.print("\nSelect a function (1–3): ");
                if (input.hasNextInt()) 
                {
                    funcChoice = input.nextInt();
                    input.nextLine(); // clear newline
                } 
                else 
                {
                    input.nextLine(); // discard bad input
                }
            }

            String func = TrigCalcV3.TRIG_FUNCTIONS[funcChoice - 1];

            // Ask for angle in radians — supports pi notation and decimals
            System.out.print("\nEnter a radian value (number or string like pi/4): ");
            String raw = input.nextLine().replaceAll("\\s", "").toLowerCase();
            double original = PiCleaner.parse(raw);  // user’s actual input

            if (original == -999) 
            {
                System.out.println("Invalid format. Try again.");
                continue;
            }

            // Convert angle to range [0, 2π)
            double rad = original % (2 * Math.PI);
            if (rad < 0) rad += 2 * Math.PI;

            // Get the reference angle (always between 0 and π/2)
            double refAngle = PiCleaner.getReferenceAngle(rad);

            // Try to match the reference angle to one we know
            boolean isSpecial = false;
            String exact = "";

            for (int i = 0; i < TrigCalcV3.RADIANS.length; i++) 
            {
                double tableAngle = PiCleaner.parse(TrigCalcV3.RADIANS[i]);
                if (Math.abs(refAngle - tableAngle) < 0.01) 
                {
                    exact = TrigCalcV3.EXACT_VALUES[funcChoice - 1][i];
                    isSpecial = true;
                    break;
                }
            }

            // Show the result
            System.out.println("\n---------------------------");
            if (isSpecial) 
            {
                // Apply the correct sign based on quadrant + trig function
                exact = PiCleaner.applySign(func, rad, exact);
                System.out.println(func + " of " + raw + " = " + exact);
            } 
            else 
            {
                // Fall back to decimal approximation
                double decimal = 0;

                switch (funcChoice) {
                    case 1:
                        decimal = Math.sin(original);
                        break;
                    case 2:
                        decimal = Math.cos(original);
                        break;
                    case 3:
                        decimal = Math.tan(original);
                        break;
                }

                System.out.println(func + " of " + raw + " = " + decimal + " (approx.)");
            }
            System.out.println("---------------------------");

            // Ask what to do next
            String choice = "";
            while (!choice.equalsIgnoreCase("g") && 
                   !choice.equalsIgnoreCase("m") && 
                   !choice.equalsIgnoreCase("q")) 
            {
                System.out.print("\nGo again (g), return to menu (m), or quit (q): ");
                choice = input.nextLine();
            }

            if (choice.equalsIgnoreCase("q")) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            } 
            else if (choice.equalsIgnoreCase("m")) {
                keepGoing = false;
            }
        }
    }
}