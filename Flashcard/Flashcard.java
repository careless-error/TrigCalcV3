/********************************************
*   AUTHORS:    Jeff Peterson
*   COLLABORATORS: None
*   LAST MODIFIED:  05/06/2025
********************************************/

/********************************************
*   Flashcard class
*   Stores one trig flashcard with function, angle, exact value, and decimal approximation.
*   Includes constructors, setters, getters, and helper methods for printing and comparing.
********************************************
*   HOW FLASHCARDS GET THE RIGHT VALUES
*   As long as the array order stays consistent, each flashcard will be correct.
*   [x] is the trig function index
*   [y] is the angle index 
*   new Flashcard(TRIG_FUNC[x], RADIAN_VALUE[y], EXACT_VALUES[x][y], [x], Math([y])
*********************************************
UML CLASS DIAGRAM:
********************************************
Flashcard
********************************************
- trigFunction : String
- radian : String
- exactValue : String
- approx : double
********************************************
+ Flashcard()
+ Flashcard(trigFunction : String, radian : String, exactValue : String, approx : double)
+ Flashcard(original : Flashcard)
+ setTrigFunction(trigFunction : String) : void
+ setRadian(radian : String) : void
+ setExactValue(exactValue : String) : void
+ setApprox(approx : double) : void
+ setAll(trigFunction : String, radian : String, exactValue : String, approx : double) : void
+ getTrigFunction() : String
+ getRadian() : String
+ getExactValue() : String
+ getApprox() : double
+ toString() : String
+ equals(other : Flashcard) : boolean
+ getDecimalValue(funcIndex : int, angleIndex : int) : double
+ generateFlashcards() : Flashcard[]
+ getRandomFlashcard() : Flashcard
+ displayFlashcards(flashcards : Flashcard[]) : void
********************************************/

public class Flashcard {

    /***** INSTANCE VARIABLES *****/
    private String trigFunction;  // e.g. "Sine"
    private String radian;        // e.g. "π/4"
    private String exactValue;    // e.g. "√2/2"
    private double approx;        // e.g. 0.7071

    /***** CONSTANT ARRAYS *****/
    public static final String[] TRIG_FUNC = {
        "Sine", "Cosine", "Tangent"
    };

    public static final String[] RADIAN_VALUE = {
        "0", "π/6", "π/4", "π/3", "π/2", "2π/3", "3π/4", "5π/6", "π"
    };

    public static final String[][] EXACT_VALUES = {
        { "0", "1/2", "√2/2", "√3/2", "1", "√3/2", "√2/2", "1/2", "0" },     // sine
        { "1", "√3/2", "√2/2", "1/2", "0", "-1/2", "-√2/2", "-√3/2", "-1" }, // cosine
        { "0", "√3/3", "1", "√3", "UNDEFINED", "-√3", "-1", "-√3/3", "0" }   // tangent
    };

    public static final double[] ANGLES = {
        0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2,
        2 * Math.PI / 3, 3 * Math.PI / 4, 5 * Math.PI / 6, Math.PI
    };

    /***** CONSTRUCTORS *****/

    /**
     * No-argument constructor: Sets default values for a new flashcard
     */
    public Flashcard() {
        this.setAll("Sine", "2π", "0", 0.0);
    }

    /**
     * Full constructor: Creates a flashcard using the given values for function, angle, exact value, and decimal.
     */
    public Flashcard(String trigFunction, String radian, String exactValue, double approx) {
        this.setAll(trigFunction, radian, exactValue, approx);
    }

    /**
     * Copy constructor: Makes a new flashcard by copying all values from another flashcard.
     * If the original is null, the program prints an error and exits.
     */
    public Flashcard(Flashcard original) {
        if (original != null) {
            this.setAll(original.trigFunction, original.radian, original.exactValue, original.approx);
        } else {
            System.out.println("ERROR: attempt to copy null Flashcard. Exiting.");
            System.exit(0);
        }
    }

    /***** SETTERS (mutators) *****/

    /** Sets the trig function name (e.g. "Sine") */
    public void setTrigFunction(String trigFunction) {
        this.trigFunction = trigFunction;
    }

    /** Sets the angle in radians (e.g. "π/4") */
    public void setRadian(String radian) {
        this.radian = radian;
    }

    /** Sets the exact value (e.g. "√2/2") */
    public void setExactValue(String exactValue) {
        this.exactValue = exactValue;
    }

    /** Sets the approximate decimal value (e.g. 0.7071) */
    public void setApprox(double approx) {
        this.approx = approx;
    }

    /**
     * Sets all instance variables at once.
     * Used by all constructors to simplify and avoid repeating code.
     */
    public void setAll(String trigFunction, String radian, String exactValue, double approx) {
        this.trigFunction = trigFunction;
        this.radian = radian;
        this.exactValue = exactValue;
        this.approx = approx;
    }

    /***** GETTERS (accessors) *****/

    /** Returns the trig function name */
    public String getTrigFunction() {
        return trigFunction;
    }

    /** Returns the angle in radians */
    public String getRadian() {
        return radian;
    }

    /** Returns the exact value */
    public String getExactValue() {
        return exactValue;
    }

    /** Returns the approximate decimal value */
    public double getApprox() {
        return approx;
    }

    /***** OTHER METHODS *****/

    /**
     * Returns a readable string version of the flashcard.
     * Automatically called when printing: System.out.println(flashcards[i]);
     */
    public String toString() {
        if (exactValue.equals("UNDEFINED")) {
            return trigFunction + " of " + radian + " is undefined";
        } else {
            return trigFunction + " of " + radian + " is " + exactValue
                + " (approx. " + String.format("%.5f", approx == 0.0 ? 0.0 : approx) + ")";
        }
    }

    /**
     * Compares this flashcard to another.
     * Returns true if all values match exactly.
     */
    public boolean equals(Flashcard other) {
        if (other == null) {
            return false;
        } else {
            return this.trigFunction.equals(other.trigFunction)
                && this.radian.equals(other.radian)
                && this.exactValue.equals(other.exactValue)
                && this.approx == other.approx;
        }
    }

    /**
     * Returns the decimal approximation of a trig function at a specific angle index.
     * funcIndex: 0 = sin, 1 = cos, 2 = tan
     * angleIndex: 0 to 8 for the 9 angle values
     */
    public static double getDecimalValue(int funcIndex, int angleIndex) {
        if (EXACT_VALUES[funcIndex][angleIndex].equals("UNDEFINED")) {
            return Double.NaN;
        }
        switch (funcIndex) {
            case 0: return Math.sin(ANGLES[angleIndex]);
            case 1: return Math.cos(ANGLES[angleIndex]);
            case 2: return Math.tan(ANGLES[angleIndex]);
            default: return 0.0;
        }
    }

    /**
     * Returns an array of all 27 flashcards (3 functions × 9 angles) with correct values.
     */
    public static Flashcard[] generateFlashcards() {
        Flashcard[] flashcards = new Flashcard[27];
        int index = 0;

        for (int f = 0; f < 3; f++) {
            for (int a = 0; a < 9; a++) {
                String func = TRIG_FUNC[f];
                String angle = RADIAN_VALUE[a];
                String exact = EXACT_VALUES[f][a];
                double approx = getDecimalValue(f, a);
                flashcards[index++] = new Flashcard(func, angle, exact, approx);
            }
        }

        return flashcards;
    }

    /**
     * Returns one random flashcard from the full 27.
     * Returns a deep copy so original array remains unchanged.
     */
    public static Flashcard getRandomFlashcard() {
        Flashcard[] all = generateFlashcards();
        int index = (int)(Math.random() * all.length);
        return new Flashcard(all[index]); // return a copy
    }

    /**
     * Prints all flashcards in the given array using toString().
     */
    public static void displayFlashcards(Flashcard[] flashcards) {
        for (int i = 0; i < flashcards.length; i++) {
            if (flashcards[i] == null) {
                System.out.printf("flashcards[%d] null%n", i);
            } else {
                System.out.printf("flashcards[%d] %s%n", i, flashcards[i].toString());
            }
        }
    }
}