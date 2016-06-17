package net.tvidal.skillsmatter.ex1

import org.junit.Assert.assertSame
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.*

class MyStackShould {

    lateinit var stack: MyStack<Any>

    companion object {
        val anObject = "an Object"
        val anotherObject = "another Object"
    }

    @Before fun initialize() {
        stack = MyStack()
    }

    @Test fun `throw exception on pop if empty`() {
        stack = MyStack()
        try {
            stack.pop()
            fail("pop should throw exception if empty")
        } catch (ignore: EmptyStackException) {
            // pass
        }
    }

    @Test fun `pop the same object that was pushed`() {
        stack.push(anObject)
        assertSame(anObject, stack.pop())
    }

    @Test fun `pop objects in the reverse order they were pushed`() {
        stack.push(anObject)
        stack.push(anotherObject)

        assertSame(anotherObject, stack.pop())
        assertSame(anObject, stack.pop())
    }

}
