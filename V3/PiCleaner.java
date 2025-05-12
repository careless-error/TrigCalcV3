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
 * UML CLASS DIAGRAM:
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
     * Parses a user string into a radian value.
     * Supports:
     *   - "π" and "pi" → Math.PI
     *   - implied multiplication: "2pi" → "2 * Math.PI"
     *   - fractions like "3pi/4"
     * Returns -999 if parsing fails.
     */
    public static double parse(String input)
    {
        try 
        {
            input = input.replace("π", "pi");
            input = input.replaceAll("(?i)pi", "Math.PI");
            input = input.replaceAll("([0-9])Math\\.PI", "$1*Math.PI");

            if (input.contains("/")) 
            {
                String[] parts = input.split("/");
                if (parts.length == 2) 
                {
                    double numerator = eval(parts[0]);
                    double denominator = eval(parts[1]);
                    return numerator / denominator;
                }
            }

            return eval(input);
        } 
        catch (Exception e) 
        {
            return -999;
        }
    }

    /**
     * Basic evaluator for simple expressions like "2*Math.PI" or "3.14".
     * Only supports one operator.
     */
    private static double eval(String expr)
    {
        expr = expr.replace("Math.PI", String.valueOf(Math.PI));

        if (expr.contains("*")) 
        {
            String[] parts = expr.split("\\*");
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } 
        else if (expr.contains("/")) 
        {
            String[] parts = expr.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        } 
        else 
        {
            return Double.parseDouble(expr);
        }
    }

    /**
     * Applies a negative sign if the trig function is negative
     * in the current quadrant.
     * Assumes rad is already normalized to [0, 2π).
     */
    public static String applySign(String func, double rad, String value)
    {
        boolean negative = false;

        if (func.equalsIgnoreCase("Sine")) {
            if (rad >= Math.PI && rad < 2 * Math.PI) {
                negative = true;
            }
        } 
        else if (func.equalsIgnoreCase("Cosine")) {
            if (rad >= Math.PI / 2 && rad < 3 * Math.PI / 2) {
                negative = true;
            }
        } 
        else if (func.equalsIgnoreCase("Tangent")) {
            if ((rad > Math.PI / 2 && rad < Math.PI) || (rad > 3 * Math.PI / 2 && rad < 2 * Math.PI)) {
                negative = true;
            }
        }

        if (negative && !value.equals("0") && !value.equals("undef")) {
            return "-" + value;
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