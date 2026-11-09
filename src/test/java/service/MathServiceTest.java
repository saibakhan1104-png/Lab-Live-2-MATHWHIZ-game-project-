package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Task 09 test cases. */
class MathServiceTest {

    @Test void prime7()  { assertEquals(1, MathService.isPrime(7)); }
    @Test void prime8()  { assertEquals(0, MathService.isPrime(8)); }
    @Test void prime1()  { assertEquals(0, MathService.isPrime(1)); }
    @Test void primeZeroNegativeTwo() {
        assertEquals(0, MathService.isPrime(0));
        assertEquals(0, MathService.isPrime(-5));
        assertEquals(1, MathService.isPrime(2));
    }

    @Test void mod17By5() { assertEquals(2, MathService.calculateMod(17, 5)); }
    @Test void modNegative() {
        assertEquals(-2, MathService.calculateMod(-17, 5));
        assertEquals(2, MathService.calculateMod(17, -5));
    }
    @Test void modZeroNumerator() { assertEquals(0, MathService.calculateMod(0, 5)); }
    @Test void modZeroDivisor() {
        assertThrows(ArithmeticException.class, () -> MathService.calculateMod(10, 0));
    }

    @Test void sin30() { assertEquals(0.5, MathService.calculateSin(30), 1e-9); }
    @Test void cos60() { assertEquals(0.5, MathService.calculateCos(60), 1e-9); }
    @Test void tan45() { assertEquals(1.0, MathService.calculateTan(45), 1e-9); }
    @Test void tanUndefined() {
        assertTrue(Double.isNaN(MathService.calculateTan(90)));
        assertTrue(Double.isNaN(MathService.calculateTan(270)));
    }
    @Test void identity() {
        double s = MathService.calculateSin(37), c = MathService.calculateCos(37);
        assertEquals(1.0, s * s + c * c, 1e-9); // x^2 + y^2 = 1
    }
}
