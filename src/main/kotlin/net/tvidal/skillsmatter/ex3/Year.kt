package net.tvidal.skillsmatter.ex3

class Year(val year: Int) {

    val isLeapYear: Boolean
        get() = if (divisibleBy(100)) !divisibleBy(400)
        else divisibleBy(4)

    private fun divisibleBy(divisor: Int) =
            year.mod(divisor) == 0

}
