package isel.leic.tds

import kotlin.test.Test
import kotlin.test.assertFailsWith

const val BLANK_STRING = " \t\n  \t "

class DomainModelTests {

    @Test(expected = IllegalArgumentException::class)
    fun `instantiation of Author with blank string throws`() {
        val sut = Author(BLANK_STRING)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `instantiation of Author with blank spaces throws`() {
        val sut = Author(" invalid user id")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `instantiation of Author with tabs and newlines throws`() {
        val sut = Author("invalid\nuser\tid")
    }

    @Test
    fun `instantiation of Author without blank characters works`() {
        val sut = Author("the_user_id")
    }

    @Test
    fun `toAuthor on blank string throws`() {
        assertFailsWith<IllegalArgumentException> {
            BLANK_STRING.toAuthor()
        }
    }
}