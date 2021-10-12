
private data class Node<T>(val value: T, val next: Node<T>?)

class Stack<T> {

    private var top: Node<T>? = null

    fun top(): T? = top?.value

    fun pop(): T {
        val theTop: Node<T>? = top
        // Verify pre-condition
        return if (theTop == null)
            throw IllegalStateException()
        else {
            top = theTop.next
            theTop.value
        }
    }

    fun push(value: T) {
        top = Node(value, top)
    }

    fun isEmpty() = top == null
}

