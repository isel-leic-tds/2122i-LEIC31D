
data class Node<T>(val next: Node<T>? = null, val value: T)

class PersistentStack<T>(private val topNode: Node<T>? = null) {
    fun isEmpty() = topNode == null
    fun push(value: T) = PersistentStack(Node(topNode, value))
    fun pop() = if(isEmpty()) this else PersistentStack(topNode?.next)
    fun top(): T? = topNode?.value
}

class MutableStack<T>(private var topNode: Node<T>? = null) {

    fun isEmpty() = topNode == null

    fun push(value: T): MutableStack<T> {
        topNode = Node(topNode, value)
        return this
    }

    fun pop(): MutableStack<T> {
        return if(!isEmpty()) { topNode = topNode?.next; this }
        else MutableStack()
    }

    fun top(): T? = topNode?.value
}