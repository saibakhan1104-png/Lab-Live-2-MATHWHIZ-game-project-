package model;

import java.io.Serializable;

/** One challenge built from the values the player typed. Answers are always calculated by MathService. */
public class Question implements Serializable {
    public static final String PRIME = "PRIME";
    public static final String MOD = "MOD";
    public static final String TRIG = "TRIG";

    private final String type;
    private final int a;
    private final int b;
    private final String func;   // SIN, COS, TAN (TRIG only)
    private final double angle;  // degrees (TRIG only)
    private final int marks;

    public Question(String type, int a, int b, String func, double angle, int marks) {
        this.type = type;
        this.a = a;
        this.b = b;
        this.func = func;
        this.angle = angle;
        this.marks = marks;
    }

    public String getType() { return type; }
    public int getA() { return a; }
    public int getB() { return b; }
    public String getFunc() { return func; }
    public double getAngle() { return angle; }
    public int getMarks() { return marks; }

    public String getText() {
        switch (type) {
            case PRIME:
                return "Is " + a + " a prime number? (1 = Prime, 0 = Not Prime)";
            case MOD:
                return a + " % " + b + " = ?";
            default:
                String deg = angle == Math.rint(angle) ? String.valueOf((long) angle) : String.valueOf(angle);
                return func.toLowerCase() + "(" + deg + "\u00B0) = ?";
        }
    }
}
