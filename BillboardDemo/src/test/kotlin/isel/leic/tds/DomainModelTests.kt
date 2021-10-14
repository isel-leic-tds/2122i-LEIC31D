package isel.leic.tds

import kotlin.test.Test

class DomainModelTests {

    @Test(expected = IllegalArgumentException::class)
    fun `instantiation of Author with blank string throws`() {
        val sut = Author("   \t\n  \t")
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
}