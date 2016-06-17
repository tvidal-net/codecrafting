package net.tvidal.skillsmatter.ex3

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class YearShould {

    @Test fun `be leap year if divisible by 4`() {
        assertTrue(Year(4).isLeapYear)
        assertTrue(Year(8).isLeapYear)
        assertTrue(Year(12).isLeapYear)
    }

    @Test fun `not be leap year if not divisible by 4`() {
        assertFalse(Year(2).isLeapYear)
        assertFalse(Year(3).isLeapYear)
        assertFalse(Year(5).isLeapYear)
    }

    @Test fun `be leap year if divisible by 100`() {
        assertTrue(Year(200).isLeapYear)
        assertTrue(Year(300).isLeapYear)
        assertTrue(Year(500).isLeapYear)
    }

    @Test fun `not be leap if divisible by 400`() {
        assertFalse(Year(400).isLeapYear)
        assertFalse(Year(800).isLeapYear)
        assertFalse(Year(1200).isLeapYear)
    }

}
