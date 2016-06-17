package net.tvidal.skillsmatter.ex4

class PaymentService(
        val userValidator: UserValidator,
        val paymentGateway: PaymentGateway
) {

    fun executePayment(user: User, paymentDetails: PaymentDetails) {
        if (!userValidator.validateUser(user)) {
            throw InvalidUserException()
        }
        paymentGateway.sendPayment(paymentDetails)
    }

}
