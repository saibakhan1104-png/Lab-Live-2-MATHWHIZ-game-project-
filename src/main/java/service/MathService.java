package service;

import model.Question;

import java.util.List;

/** All mathematical logic. Every answer is calculated here - nothing is hard-coded. */
public class MathService {

    public static final String DIFFICULTY = "NORMAL";
    public static final double TOLERANCE = 0.01;


    // ---------- Task 03 ----------
    /** Returns 1 if number is prime, otherwise 0. Numbers below 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) return 0;
        if (number < 4) return 1;
        if (number % 2 == 0) return 0;
        for (int i = 3; (long) i * i <= number; i += 2) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    // ---------- Task 04 ----------
    /** Returns a % b. Throws ArithmeticException when the divisor is zero. */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Validation error: divisor must not be zero.");
        }
        return a % b;
    }

    // ---------- Task 05 ----------
    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    /** Returns NaN when tangent is undefined (90, 270, ... degrees). */
    public static double calculateTan(double degree) {
        double normalized = ((degree % 180) + 180) % 180;
        if (Math.abs(normalized - 90) < 1e-9) {
            return Double.NaN;
        }
        return Math.tan(Math.toRadians(degree));
    }

    // ---------- Task 06 ----------
    /** The correct answer for a question, computed with the methods above. */
    public static double expectedValue(Question q) {
        switch (q.getType()) {
            case Question.PRIME:
                return isPrime(q.getA());
            case Question.MOD:
                return calculateMod(q.getA(), q.getB());
            default:
                switch (q.getFunc()) {
                    case "SIN": return calculateSin(q.getAngle());
                    case "COS": return calculateCos(q.getAngle());
                    default:    return calculateTan(q.getAngle());
                }
        }
    }

    public static boolean isCorrect(Question q, double userAnswer) {
        double expected = expectedValue(q);
        if (Double.isNaN(expected)) return false;
        if (Question.TRIG.equals(q.getType())) {
            return Math.abs(expected - userAnswer) <= TOLERANCE;
        }
        return expected == userAnswer;
    }

    public static String expectedText(Question q) {
        double v = expectedValue(q);
        if (Double.isNaN(v)) return "undefined";
        if (Question.TRIG.equals(q.getType())) return String.format(java.util.Locale.US, "%.4f", v);
        return String.valueOf((int) v);
    }

    public static int calculateScore(List<Question> questions, boolean[] correct) {
        int totalScore = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (correct[i]) totalScore += questions.get(i).getMarks(); // 2 + 2 + 3 + 3 = 10
        }
        return totalScore;
    }
}
