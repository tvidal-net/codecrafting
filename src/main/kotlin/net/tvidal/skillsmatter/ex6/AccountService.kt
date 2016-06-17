package net.tvidal.skillsmatter.ex6

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

data class Transaction(
        val date: Date,
        val amount: Int
)

open class TransactionRepository {

    val transactionList = ArrayList<Transaction>()

    open fun append(transaction: Transaction) {
        transactionList.add(transaction)
    }

    open fun list(): List<Transaction> {
        return transactionList
    }

}

open class Clock {

    open fun currentDate() = Date()

}

open class Console {

    open fun println(line: String) {
        println(line)
    }

}

open class StatementPrinter(val console: Console) {

    companion object {
        val STATEMENT_HEADER = "   DATE    |  AMOUNT | BALANCE"
        val DATE_FORMATTER = SimpleDateFormat("dd/MM/yyyy")
        val TRANSACTION_FORMAT = "%-10s | %4d.00 | %4d.00"
    }

    open fun printStatement(transactions: Collection<Transaction>) {
        console.println(STATEMENT_HEADER)
        val runningBalance = AtomicInteger()
        transactions.map {
            val formattedDate = DATE_FORMATTER.format(it.date)
            TRANSACTION_FORMAT.format(formattedDate, it.amount, runningBalance.addAndGet(it.amount))
        }
                .reversed()
                .forEach { console.println(it) }
    }
}

class AccountService(
        console: Console = Console(),
        val transactionRepository: TransactionRepository = TransactionRepository(),
        val statementPrinter: StatementPrinter = StatementPrinter(console),
        val clock: Clock = Clock()
) {

    fun deposit(amount: Int) {
        val date = clock.currentDate()
        val transaction = Transaction(date, amount)
        transactionRepository.append(transaction)
    }

    fun withdraw(amount: Int) {
        val date = clock.currentDate()
        val transaction = Transaction(date, -amount)
        transactionRepository.append(transaction)
    }

    fun printStatement() {
        val transactions = transactionRepository.list()
        statementPrinter.printStatement(transactions)
    }

}
