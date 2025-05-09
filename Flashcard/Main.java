/********************************************
*   AUTHORS:    Jeff Peterson
*   COLLABORATORS: None
*   LAST MODIFIED:  05/03/2025
********************************************/

/********************************************
*   TESTER of Flashcard
*   This program tests all methods of the Flashcard class to make sure they work right before using them in other programs.
********************************************/

/********************************************
*   HOW FLASHCARDS GET THE RIGHT VALUES
*   As long as the array order stays consistent, each flashcard will be correct.
*   [x] is the trig function index
*   [y] is the angle index 
*   new Flashcard(TRIG_FUNC[x], RADIAN_VALUE[y], EXACT_VALUES[x][y], Math.[x]([y])
*********************************************/

/********************************************
UML CLASS DIAGRAM:
********************************************
Main
********************************************
+ main(args : String[]) : void
+ getExactValue(funcIndex : int, angleIndex : int) : String
+ getDecimalValue(funcIndex : int, angleIndex : int) : double
+ generateFlashcards() : Flashcard[]
+ getRandomFlashcard() : Flashcard
********************************************/

public class Main 
{
  public static void main(String[] args)
  {
    /***** DECLARATION SECTION *****/
    Flashcard[] flashcards = new Flashcard[10]; // array to store 10 flashcard objects

    /***** TEST CONSTRUCTORS *****/
    System.out.println("TESTING CONSTRUCTORS & toString");

    flashcards[0] = new Flashcard(); // test no-arg constructor
    flashcards[1] = new Flashcard(); // test no-arg again
    flashcards[2] = new Flashcard("Sine", "π/6", "1/2", 0.5); // test full constructor
    //flashcards[3] = new Flashcard(flashcards[9]); // test bad copy (commented out so program doesn't crash)
    flashcards[3] = new Flashcard(flashcards[2]); // test copy constructor
    flashcards[4] = new Flashcard(flashcards[2]); // more copies to test
    flashcards[5] = new Flashcard(flashcards[2]);
    flashcards[6] = new Flashcard(flashcards[2]);
    flashcards[7] = new Flashcard(flashcards[2]);
    flashcards[8] = new Flashcard(flashcards[2]);

    Flashcard.displayFlashcards(flashcards); // print flashcards to test toString() and displayFlashcards()

    /***** TEST SETTERS & GETTERS *****/
    System.out.println("\nTESTING SETTERS & GETTERS");

    flashcards[3].setTrigFunction("Cosine"); // change function
    System.out.println("Trig Function: " + flashcards[3].getTrigFunction()); // check if it worked

    flashcards[4].setRadian("π/4"); // change angle
    System.out.println("Radian: " + flashcards[4].getRadian()); // check if it worked

    flashcards[5].setExactValue("√2/2"); // change exact value
    System.out.println("Exact Value: " + flashcards[5].getExactValue()); // check if it worked

    flashcards[6].setApprox(0.707); // change decimal
    System.out.println("Approximate: " + flashcards[6].getApprox()); // check if it worked

    flashcards[7].setAll("Tangent", "π/3", "√3", 1.732); // change all values at once
    System.out.println("All data: " + flashcards[7].toString()); // check toString()

    flashcards[8].setRadian("π"); // change just one thing for later deep copy test

    flashcards[0].setAll("Sine", "π/6", "1/2", 0.5); // match flashcards[2] so equals test works

    /***** TESTING DEEP COPY *****/
    System.out.println("\nTESTING DEEP COPY");
    System.out.println("Note: flashcards[2] should not have changed. flashcards[3] through flashcards[8] should each have one variable changed.");
    Flashcard.displayFlashcards(flashcards); // print to verify changes

    /***** TESTING EQUALS *****/
    System.out.println("\nTESTING EQUALS");
    System.out.println("Note: flashcards[0] should equal itself and flashcards[2]. All others are different.");
    for (int i = 0; i < flashcards.length; i++)
    {
      System.out.printf("flashcards[0] == flashcards[%d] is %b%n", i, flashcards[0].equals(flashcards[i]));
    }

    /***** GENERATE FULL FLASHCARD SET (27 cards) *****/
    System.out.println("\nDISPLAYING ALL 27 GENERATED FLASHCARDS");
    Flashcard[] allFlashcards = generateFlashcards(); // make full set of trig flashcards
    Flashcard.displayFlashcards(allFlashcards); // print them all

    /***** PRINT 1 RANDOM FLASHCARD *****/
    System.out.println("\nRANDOM FLASHCARD:");
    System.out.println(getRandomFlashcard());
  }

  /***** CONSTANT SECTION *****/
  public static final String[] TRIG_FUNC = {
    "Sine", "Cosine", "Tangent" // names of trig functions
  };

  public static final String[] RADIAN_VALUE = {
    "0", "π/6", "π/4", "π/3", "π/2", "2π/3", "3π/4", "5π/6", "π" // angle labels
  };

  public static final String[][] EXACT_VALUES = {
    { "0", "1/2", "√2/2", "√3/2", "1", "√3/2", "√2/2", "1/2", "0" },     // sine
    { "1", "√3/2", "√2/2", "1/2", "0", "-1/2", "-√2/2", "-√3/2", "-1" }, // cosine
    { "0", "√3/3", "1", "√3", "UNDEFINED", "-√3", "-1", "-√3/3", "0" }   // tangent
  };

  public static final double[] ANGLES = {
    0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2,
    2 * Math.PI / 3, 3 * Math.PI / 4, 5 * Math.PI / 6, Math.PI // angles in radians for math
  };

  /***** CALCULATION METHODS *****/

  /** returns the exact value from the array */
  public static String getExactValue(int funcIndex, int angleIndex)
  {
    return EXACT_VALUES[funcIndex][angleIndex];
  }

  /** returns the decimal value using Math trig functions */
  public static double getDecimalValue(int funcIndex, int angleIndex)
  {
    double result = 0.0;
    switch (funcIndex)
    {
      case 0:
        result = Math.sin(ANGLES[angleIndex]);
        break;
      case 1:
        result = Math.cos(ANGLES[angleIndex]);
        break;
      case 2:
        result = Math.tan(ANGLES[angleIndex]);
        break;
    }
    return result;
  }

  /** makes all 27 flashcards using function + angle */
  public static Flashcard[] generateFlashcards()
  {
    Flashcard[] flashcards = new Flashcard[27]; // array for all combinations
    int index = 0;

    for (int funcIndex = 0; funcIndex < 3; funcIndex++) // loop through Sine, Cosine, Tangent
    {
      for (int angleIndex = 0; angleIndex < 9; angleIndex++) // loop through all 9 angles
      {
        String function = TRIG_FUNC[funcIndex];
        String radian = RADIAN_VALUE[angleIndex];
        String exact = getExactValue(funcIndex, angleIndex);
        double approx = 0.0;

        if (!exact.equals("UNDEFINED")) // only calculate if valid
        {
          approx = getDecimalValue(funcIndex, angleIndex);
        }

        flashcards[index] = new Flashcard(function, radian, exact, approx); // make flashcard
        index++;
      }
    }

    return flashcards; // return the array of all 27
  }
  
  /** returns one random flashcard from the full set */
  public static Flashcard getRandomFlashcard()
  {
    Flashcard[] all = generateFlashcards();
    int index = (int)(Math.random() * all.length);
    return all[index];
  }

}