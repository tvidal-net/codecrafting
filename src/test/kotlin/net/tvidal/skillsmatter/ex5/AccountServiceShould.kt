package net.tvidal.skillsmatter.ex5

import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AccountServiceShould {

    lateinit var accountService: AccountService

    @Mock lateinit var transactionRepository: TransactionRepository

    @Mock lateinit var statementPrinter: StatementPrinter

    @Mock lateinit var dateProvider: DateProvider

    @Before fun initialize() {
        accountService = AccountService(
                transactionRepository = transactionRepository,
                statementPrinter = statementPrinter,
                dateProvider = dateProvider
        )
    }

    @Test fun `append negative transaction to statement on withdraw`() {
        val date = Date()
        val amount = 15
        val expected = Transaction(date, -amount)

        given(dateProvider.currentDate()).willReturn(date)
        accountService.withdraw(amount)

        verify(transactionRepository).append(expected)
    }

    @Test fun `append positive transaction to statement on deposit`() {
        val date = Date()
        val amount = 40
        val expected = Transaction(date, amount)

        given(dateProvider.currentDate()).willReturn(date)
        accountService.deposit(amount)

        verify(transactionRepository).append(expected)
    }

    @Test fun `print the statements from the transaction repository`() {
        val transactions = emptyList<Transaction>()

        given(transactionRepository.list()).willReturn(transactions)
        accountService.printStatement()

        verify(statementPrinter).printStatement(transactions)
    }

}
