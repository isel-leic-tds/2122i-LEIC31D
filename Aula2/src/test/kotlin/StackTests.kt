import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StackTests {

    @Test
    fun `creating stack with no arguments returns empty stack`() {
        val sut = Stack<Int>()
        assertTrue(sut.isEmpty())
    }

    @Test
    fun `pop on a non empty stack leaves at the top the second topmost element`() {
        val sut = Stack<Int>()
        sut.push(1)
        sut.push(2)
        sut.pop()
        assertEquals(1, sut.top())
    }
}