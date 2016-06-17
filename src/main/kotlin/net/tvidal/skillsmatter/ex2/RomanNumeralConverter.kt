package net.tvidal.skillsmatter.ex2

class RomanNumeralConverter {

    companion object {
        private val values = arrayOf(
                1000 to "M",
                900 to "CM",
                500 to "D",
                400 to "CD",
                100 to "C",
                90 to "XC",
                50 to "L",
                40 to "XL",
                10 to "X",
                9 to "IX",
                5 to "V",
                4 to "IV",
                1 to "I"
        )
    }

    fun convert(number: Int): String {
        values.forEach {
            val (decimal, roman) = it
            if (number >= decimal) {
                val suffix = convert(number - decimal)
                return roman + suffix
            }
        }
        return String()
    }

}
