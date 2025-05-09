/********************************************
*  AUTHORS:         Jeff Peterson, Noah Shew
*  COLLABORATORS:
*  LAST MODIFIED:   4/18/2025
*********************************************
*  TrigCalc v2
*********************************************
*  PROGRAM DESCRIPTION:
*  this program takes standard position angles in radians and calculates their trigonometric functions.
*********************************************
*  ALGORITHM:
*  1. print program title and introduction
*  2. ask the user to choose a trig function (sine, cosine, tangent)
*  3. ask the user to choose a special angle in radians
*  4. look up and display the exact trig value
*  5. ask and (optionally) display the decimal value
*  6. repeat unless the user chooses to stop
*********************************************
*  STATIC METHODS:
*  + main(String[] args) : void                             // written collaboratively by Jeff & Noah
*  + userInput() : boolean                                  // written by Noah
*  + output(int, int) : void                                // written by Noah
*  + getDecimalValue(int, int) : double                     // written by Jeff
*  + getExactValue(int, int) : String                       // written collaboratively by Jeff & Noah
*  + printDecimalResult(int, int, double) : void            // written by Noah
*  + printExactValue(int, int) : void                       // written by Noah
*  + printTrigMenu() : void                                 // written by Jeff based on Noah's input loop
*  + printAngleMenu() : void                                // written by Jeff based on Noah's input loop
*  + printIntro() : void                                    // written by Jeff
*********************************************
* IMPORTED PACKAGES NEEDED AND PURPOSE:
* UtilityBelt ---> Used to get user input from the console
* Scanner ---> Used in all of the UtilityBelt methods, so extremely important
* readInt ---> Used to read the numbers that the user inputs into the console to choose things like the trig function and radians
* readChar ---> Used to read the letters typed into the console to check if they were "yYnN" to check for yes or no responses
**********************************************/

public class TrigCalcV2
{
/***** CONSTANT SECTION *****/

    /** description: array storing the names of trig functions */
    public static final String[] TRIG_FUNC = {
        "Sine", "Cosine", "Tangent"
    };

    /** description: 2d array storing the exact trig values for each function and angle */
    public static final String[][] EXACT_VALUES = {
        { "0", "1/2", "√2/2", "√3/2", "1", "√3/2", "√2/2", "1/2", "0" },
        { "1", "√3/2", "√2/2", "1/2", "0", "-1/2", "-√2/2", "-√3/2", "-1" },
        { "0", "√3/3", "1", "√3", "UNDEFINED", "-√3", "-1", "-√3/3", "0" }
    };

    /** description: array storing angle values in radians (for decimal calculations) */
    public static final double[] ANGLES = {
        0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2,
        2 * Math.PI / 3, 3 * Math.PI / 4, 5 * Math.PI / 6, Math.PI
    };

    /** description: array storing display labels for the angles */
    public static final String[] RADIAN_VALUE = {
        "0", "π/6", "π/4", "π/3", "π/2", "2π/3", "3π/4", "5π/6", "π"
    };


/***** MAIN METHOD *****/
    
    /** description: runs the main program loop — shows intro, handles input, and repeats as needed */
    public static void main(String[] args)
    {
        printIntro();
        
        boolean shouldRepeat;

        do
        {
            shouldRepeat = userInput();
        }
        while (shouldRepeat);

        System.out.printf("%nThanks for using TrigCalc!");
    }


/***** STATIC METHODS *****/
    
    /***** INTRO METHODS *****/
    
    /** description: prints program title and formatted introduction */
    public static void printIntro()
    {
        System.out.printf("%51s%n", "---------------------------------------------------");
        System.out.printf("%31s%n", "TrigCalc v2");
        System.out.printf("%50s%n", "Calculate trigonometric values for special angles");
        System.out.printf("%51s%n", "---------------------------------------------------");
        System.out.printf("%44s%n", "This program shows exact and decimal");
        System.out.printf("%48s%n%n", "approximations for sine, cosine, and tangent");
    }
    
    
    /***** MENU METHODS *****/
    
    /** description: prints menu of trig function options */
    public static void printTrigMenu()
    {
        System.out.printf("%nChoose a Trig Function:%n");
        for (int i = 0; i < TRIG_FUNC.length; i++) 
        {
            System.out.printf("  %d. %s%n", i + 1, TRIG_FUNC[i]);
        }
    }
    
    /** description: prints menu of available angle choices */
    public static void printAngleMenu()
    {
        System.out.printf("%nChoose an angle in radians:%n");
        for (int i = 0; i < RADIAN_VALUE.length; i++) 
        {
            System.out.printf("  %d. %s%n", i + 1, RADIAN_VALUE[i]);
        }
    }
    
    
    /***** INPUT AND CONTROL METHODS *****/
    
    /** description: handles user interaction: displays menus, gathers input, shows output, returns loop condition */
    public static boolean userInput()
    {
        int chosenTrigFunc;
        int chosenRadianValue;
        char repeat;
    
        printTrigMenu();
        System.out.println();
        chosenTrigFunc = UtilityBelt.readInt("Enter your choice (1–3): ", 1, 3);
    
        printAngleMenu();
        System.out.println();
        chosenRadianValue = UtilityBelt.readInt("Enter your angle choice (1–9): ", 1, 9);
    
        output(chosenTrigFunc, chosenRadianValue);
        
        System.out.println();
        repeat = UtilityBelt.readChar("Would you like to calculate another value? [Y/N]: ", "YyNn");
        
        return Character.toLowerCase(repeat) == 'y';
    }
    
    
    /***** OUTPUT METHODS *****/
    
    /** description: displays exact result and asks whether to show decimal value */
    public static void output(int chosenTrigFunc, int chosenRadianValue)
    {
        char showDecimal;
    
        printExactValue(chosenTrigFunc, chosenRadianValue);
    
        showDecimal = UtilityBelt.readChar("Show Decimal Approximation? [Y/N]: ", "YyNn");
    
        if (Character.toLowerCase(showDecimal) == 'y')
        {
            double decimalValue = getDecimalValue(chosenTrigFunc, chosenRadianValue);
            
            printDecimalResult(chosenTrigFunc, chosenRadianValue, decimalValue);
        }
        
        System.out.printf("---------------------------------------%n");
    }
    
    /** description: prints the exact value for the given trig function and angle */
    public static void printExactValue(int chosenTrigFunc, int chosenRadianValue) 
    {
        System.out.printf("%n---------------------------------------%n");
        System.out.printf("%s of (%s) = %s%n%n", 
            TRIG_FUNC[chosenTrigFunc - 1],
            RADIAN_VALUE[chosenRadianValue - 1],
            getExactValue(chosenTrigFunc, chosenRadianValue));
    }
    
    /** description: prints the decimal result of the trig function */
    public static void printDecimalResult(int chosenTrigFunc, int chosenRadianValue, double decimalValue)
    {
        System.out.printf("%n%s of (%s) = %.4f%n",
            TRIG_FUNC[chosenTrigFunc - 1],
            RADIAN_VALUE[chosenRadianValue - 1],
            decimalValue);
    }
    
    
    /***** CALCULATION METHODS *****/
    
    /** description: returns the exact trig value from the stored array */
    public static String getExactValue(int chosenTrigFunc, int chosenRadianValue) 
    {
        return EXACT_VALUES[chosenTrigFunc - 1][chosenRadianValue - 1];
    }
    
    /** description: returns the decimal approximation of a trig function at a given angle */
    public static double getDecimalValue(int chosenTrigFunc, int chosenRadianValue)
    {
        double result = 0.0;
    
        switch (chosenTrigFunc)
        {
            case 1:
                result = Math.sin(ANGLES[chosenRadianValue - 1]);
                break;
            case 2:
                result = Math.cos(ANGLES[chosenRadianValue - 1]);
                break;
            case 3:
                result = Math.tan(ANGLES[chosenRadianValue - 1]);
                break;
        }
    
        return result;
    }
}