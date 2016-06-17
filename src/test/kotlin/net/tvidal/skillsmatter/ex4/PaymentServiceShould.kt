package net.tvidal.skillsmatter.ex4

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PaymentServiceShould {

    companion object {
        val validUser = User()
        val invalidUser = User()
        val paymentDetails = PaymentDetails()
    }

    lateinit var paymentService: PaymentService

    @Mock lateinit var userValidator: UserValidator

    @Mock lateinit var paymentGateway: PaymentGateway

    @Before fun initialize() {
        paymentService = PaymentService(userValidator, paymentGateway)
    }

    @Test fun `throw exception if user is not valid`() {
        given(userValidator.validateUser(invalidUser)).willReturn(false)
        try {
            paymentService.processPayment(invalidUser, paymentDetails)
            fail("Should throw exception if user is not valid!")
        } catch (e: InvalidUserException) {
            verify(userValidator).validateUser(invalidUser)
            verifyZeroInteractions(paymentGateway)
        }
    }

    @Test fun `send payment details to payment gateway if user is valid`() {
        given(userValidator.validateUser(validUser)).willReturn(true)
        paymentService.processPayment(validUser, paymentDetails)

        verify(userValidator).validateUser(validUser)
        verify(paymentGateway).sendPayment(paymentDetails)
        verifyNoMoreInteractions(userValidator, paymentGateway)
    }

}
