package net.tvidal.skillsmatter.ex1

import java.util.*

class MyStack<T> {

    private val items = ArrayList<T>()

    fun pop(): T {
        if (items.isEmpty()) {
            throw EmptyStackException()
        }
        return items.removeAt(items.size - 1)
    }

    fun push(obj: T) {
        items.add(obj)
    }

}
