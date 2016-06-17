package net.tvidal.skillsmatter.ex5

import java.util.*

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

open class DateProvider {

    open fun currentDate() = Date()

}

open class Console {

    open fun println(line: String) {
        println(line)
    }

}

open class StatementPrinter(val console: Console) {

    companion object {
        val HEADER = "   DATE    |  AMOUNT | BALANCE"
        val FORMAT = "%dd/MM/yyyyT | %4d.00 | %4d.00"
    }

    open fun printStatement(transactions: Collection<Transaction>) {
        console.println(HEADER)
        var runningBalance = 0
        transactions.map {
            runningBalance += it.amount
            FORMAT.format(it.date, it.amount, runningBalance)
        }
                .reversed()
                .forEach { console.println(it) }
    }
}

class AccountService(
        val transactionRepository: TransactionRepository = TransactionRepository(),
        val statementPrinter: StatementPrinter = StatementPrinter(Console()),
        val dateProvider: DateProvider = DateProvider()
) {

    fun deposit(amount: Int) {
        val date = dateProvider.currentDate()
        val transaction = Transaction(date, amount)
        transactionRepository.append(transaction)
    }

    fun withdraw(amount: Int) {
        val date = dateProvider.currentDate()
        val transaction = Transaction(date, -amount)
        transactionRepository.append(transaction)
    }

    fun printStatement() {
        val transactions = transactionRepository.list()
        statementPrinter.printStatement(transactions)
    }

}
