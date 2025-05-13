/**
 * -----------------------------------------
 * PiCleaner
 * -----------------------------------------
 * 
 * Parses and analyzes user-entered trig input like "2pi/3" or "π/4".
 * Converts strings to radian values, finds reference angles, 
 * and applies correct trig signs based on quadrant.
 * 
 * Author: Jeff Peterson
 * 
 * -----------------------------------------
 * UML CLASS DIAGRAM
 * -----------------------------------------
 * + parse(String input): double
 * + getReferenceAngle(double rad): double
 * + applySign(String func, double rad, String value): String
 * -----------------------------------------
 * - eval(String expr): double
 * -----------------------------------------
*/

public class PiCleaner
{
    /**
     * Breaks up (parses) user input string into into a radian value.
     * "π" and "pi" to Math.PI
     * implied multiplication like "2pi" to "2 * Math.PI"
     * fractions like "3pi/4"
     * Returns -999 if parsing fails.
     */
    public static double parse(String input)
    {
        try 
        {
            input = input.replace("\u03c0", "pi"); // support unicode pi symbol
            input = input.replaceAll("(?i)pi", "Math.PI"); // uses regex (pattern-matching rules) (?i) makes it case-insensitive
            input = input.replaceAll("([0-9])Math\\.PI", "$1*Math.PI"); // If a digit comes right before "Math.PI", insert a * so "2Math.PI" becomes "2*Math.PI"

            if (input.contains("/")) 
            {
                String[] parts = input.split("/"); // Split the input into top and bottom if it looks like a fraction like 3pi/4
                if (parts.length == 2) 
                {
                    double numerator = eval(parts[0]);   // Turn the part before the slash into a number
                    double denominator = eval(parts[1]); // Turn the part after the slash into a number
                    return numerator / denominator;  
                }
            }

            return eval(input); // evaluate as simple expression using eval() method
        } 
        catch (Exception e) // "e" is the error object. Java gives it to us if something crashes above
        {
            return -999; // return error code if parsing fails
        }
    }

    /**
     * Basic evaluator for simple expressions like "2*Math.PI" or "3.14".
     * Turns a math string into a number.
     * Only supports one operator.
     */
    private static double eval(String expr)
    {
        expr = expr.replace("Math.PI", String.valueOf(Math.PI)); // replace Math.PI with its numeric value

        if (expr.contains("*")) 
        {
            String[] parts = expr.split("\\*"); // Split the string around the * sign (\\ because * means repeat in regex)
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]); // Multiply the two parts as numbers
        } 
        else if (expr.contains("/")) 
        {
            String[] parts = expr.split("/"); // Split the string around the / sign
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]); // Divide the first number by the second
        } 
        else 
        {
            return Double.parseDouble(expr); // handle plain numbers
        }
    }

    /**
     * Applies a negative sign if the trig function is negative in the current quadrant.
     * Assumes rad already normalized to [0,2π]
     */
    public static String applySign(String func, double rad, String value)
    {
        boolean negative = false;

        if (func.equalsIgnoreCase("Sine")) { // built-in String method, checks if func matches "Sine" (case-insensitive)
            if (rad >= Math.PI && rad < 2 * Math.PI) {
                negative = true; // Sine is negative in Q3 and Q4
            }
        } 
        else if (func.equalsIgnoreCase("Cosine")) {
            if (rad >= Math.PI / 2 && rad < 3 * Math.PI / 2) {
                negative = true; // Cosine is negative in Q2 and Q3
            }
        } 
        else if (func.equalsIgnoreCase("Tangent")) {
            if ((rad > Math.PI / 2 && rad < Math.PI) || (rad > 3 * Math.PI / 2 && rad < 2 * Math.PI)) {
                negative = true; // Tangent is negative in Q2 and Q4
            }
        }

        if (negative && !value.equals("0") && !value.equals("undef")) {
            return "-" + value; // only apply sign to meaningful values
        }

        return value;
    }

    /**
     * Returns the reference angle in radians.
     * Always between 0 and π/2.
     * Used to match special angles regardless of quadrant.
     */
    public static double getReferenceAngle(double rad)
    {
        if (rad <= Math.PI / 2) {
            return rad; // Quadrant I
        } 
        else if (rad <= Math.PI) {
            return Math.PI - rad; // Quadrant II
        } 
        else if (rad <= 3 * Math.PI / 2) {
            return rad - Math.PI; // Quadrant III
        } 
        else {
            return 2 * Math.PI - rad; // Quadrant IV
        }
    }
}
