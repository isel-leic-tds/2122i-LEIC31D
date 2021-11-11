package isel.leic.tds.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith

const val BLANK_STRING = " \t\n  \t "

class DomainModelTests {

    @Test
    fun `instantiation of Author with blank string throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author(BLANK_STRING)
        }
    }

    @Test
    fun `instantiation of Author with blank spaces throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author(" invalid user id")
        }
    }

    @Test
    fun `instantiation of Author with tabs and newlines throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author("invalid\nuser\tid")
        }
    }

    @Test
    fun `instantiation of Author without blank characters works`() {
        Author("the_user_id")
    }

    @Test
    fun `toAuthor on blank string throws`() {
        assertFailsWith<IllegalArgumentException> {
            BLANK_STRING.toAuthor()
        }
    }
}