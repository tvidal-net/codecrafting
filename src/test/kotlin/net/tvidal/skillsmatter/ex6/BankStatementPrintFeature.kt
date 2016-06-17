package net.tvidal.skillsmatter.ex6

import com.nhaarman.mockito_kotlin.inOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class BankStatementPrintFeature {

    lateinit var accountService: AccountService

    @Mock lateinit var clock: Clock

    @Mock lateinit var console: Console

    @Before fun initialize() {
        accountService = AccountService(
                console = console,
                clock = clock
        )
    }

    @Test fun `print statement with entries in reverse order`() {
        given(clock.currentDate()).willReturn(
                dateOf(2014, 4, 1),
                dateOf(2014, 4, 2),
                dateOf(2014, 4, 10)
        )
        accountService.deposit(1000)
        accountService.withdraw(100)
        accountService.deposit(500)
        accountService.printStatement()

        val inOrder = inOrder(console)
        inOrder.verify(console).println("   DATE    |  AMOUNT | BALANCE")
        inOrder.verify(console).println("10/04/2014 |  500.00 | 1400.00")
        inOrder.verify(console).println("02/04/2014 | -100.00 |  900.00")
        inOrder.verify(console).println("01/04/2014 | 1000.00 | 1000.00")
    }

    private fun dateOf(year: Int, month: Int, day: Int): Date {
        Calendar.getInstance().let {
            it.set(year, month - 1, day)
            return it.time;
        }
    }

}
