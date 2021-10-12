import kotlin.test.*

class MutableStackTests {

    @Test
    fun `creating stack with no arguments returns empty stack`() {
        val sut = MutableStack<Int>()
        assertTrue(sut.isEmpty())
    }

    @Test
    fun `pop on a non empty stack leaves at the top the second topmost element`() {
        val sut = MutableStack<Int>()
        sut.push(1)
        sut.push(2)
        sut.pop()
        assertEquals(1, sut.top())
    }

    @Test
    fun `sequence of stack operations leaves stack with correct contents`() {
        val sut = MutableStack<Int>()
        val otherSut = sut.pop()

        sut.push(1)
        sut.push(2)

        sut.pop()
        assertFalse(sut.isEmpty())
        assertEquals(1, sut.top())

        assertTrue(otherSut.isEmpty())
    }



}