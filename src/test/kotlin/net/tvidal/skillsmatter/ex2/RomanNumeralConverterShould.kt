package net.tvidal.skillsmatter.ex2

import org.junit.Assert.assertEquals
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith

@RunWith(Theories::class)
class RomanNumeralConverterShould {

    val romanConverter = RomanNumeralConverter()

    companion object {
        @DataPoints
        @JvmStatic fun values() = arrayOf(
                0 to "",
                1 to "I",
                2 to "II",
                3 to "III",
                4 to "IV",
                5 to "V",
                7 to "VII",
                9 to "IX",
                13 to "XIII",
                35 to "XXXV",
                49 to "XLIX",
                94 to "XCIV",
                256 to "CCLVI",
                489 to "CDLXXXIX",
                794 to "DCCXCIV",
                3444 to "MMMCDXLIV",
                3784 to "MMMDCCLXXXIV",
                3999 to "MMMCMXCIX"
        )

    }

    @Theory fun `convert decimals to roman`(value: Pair<Int, String>) {
        val (number, expected) = value
        val actual = romanConverter.convert(number)
        assertEquals(expected, actual)
    }

}
