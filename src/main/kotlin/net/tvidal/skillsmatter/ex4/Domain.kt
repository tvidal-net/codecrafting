package net.tvidal.skillsmatter.ex4

class User
class PaymentDetails
class InvalidUserException : Exception()

interface UserValidator {
    fun validateUser(user: User): Boolean
}

interface PaymentGateway {
    fun sendPayment(paymentDetails: PaymentDetails)
}
