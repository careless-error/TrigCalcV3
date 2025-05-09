/**
 * Holds data for a single flashcard used 
 * in the unit circle quiz.
 * 
 * Stores a trig function, a radian angle,
 * and the correct exact trig value.
 * 
 * Used to compare answers and display
 * results during the flashcard quiz.
 * 
 * @author Jeff Peterson
 */

/* UML CLASS DIAGRAM:
-----------------------------------------
Flashcard
-----------------------------------------
- trigFunction: String
- radian: String
- exactValue: String
-----------------------------------------
+ Flashcard()
+ Flashcard(String func, String radian, String exactValue)
+ Flashcard(Flashcard other)

+ void setAll(String, String, String)
+ void setTrigFunction(String)
+ void setRadian(String)
+ void setExactValue(String)

+ String getTrigFunction()
+ String getRadian()
+ String getExactValue()

+ String toString()
+ boolean equals(Flashcard)
-----------------------------------------
*/

public class Flashcard
{
        
    /***** INSTANCE VARIABLES *****/
    private String trigFunction;  // e.g. "Sine"
    private String radian;        // e.g. "π/4"
    private String exactValue;    // e.g. "√2/2"

    /***** CONSTRUCTORS *****/

    /** Default constructor */
    public Flashcard() 
    {
        this.setAll("Sine", "π/2", "1");
    }

    /**  Full constructor — sets all values using parameters */
    public Flashcard(String trigFunction, String radian, String exactValue) 
    {
        this.setAll(trigFunction, radian, exactValue);
    }

    /** Copy constructor — creates a new Flashcard from an existing one */
    public Flashcard(Flashcard original) 
    {
        if (original != null) 
        {
            this.setAll(original.trigFunction, original.radian, original.exactValue);
        } 
        else 
        {
            System.out.println("ERROR: attempt to copy null Flashcard. Exiting.");
            System.exit(0);
        }
    }

    /***** SETTERS (mutators) *****/

    /** Sets the trig function */
    public void setTrigFunction(String trigFunction) 
    {
        this.trigFunction = trigFunction;
    }

    /** Sets the radian */
    public void setRadian(String radian) 
    {
        this.radian = radian;
    }

    /** Sets the exact value */
    public void setExactValue(String exactValue) 
    {
        this.exactValue = exactValue;
    }

    /** setAll */
    public void setAll(String trigFunction, String radian, String exactValue) 
    {
        this.trigFunction = trigFunction;
        this.radian = radian;
        this.exactValue = exactValue;
    }

    /***** GETTERS (accessors) *****/

    /** Gets the trig function name (e.g. "Sine") */
    public String getTrigFunction() 
    {
        return trigFunction;
    }

    /** Gets the radian value (e.g. "π/4") */
    public String getRadian() 
    {
        return radian;
    }

    /** Gets the exact trig value (e.g. "√2/2") */
    public String getExactValue() 
    {
        return exactValue;
    }

    /***** toString *****/
    public String toString() 
    {
        return trigFunction + " of " + radian + " is " + exactValue;
    }
     /***** equals *****/
    public boolean equals(Flashcard other)
    {
        if (other == null) 
        {
            return false;
        }

        return this.trigFunction.equals(other.trigFunction)
            && this.radian.equals(other.radian)
            && this.exactValue.equals(other.exactValue);
    }

}