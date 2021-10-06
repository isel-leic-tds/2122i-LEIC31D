import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class FactorialTests {

    @Test
    fun `factorial of 0 is 1`() {
        assertEquals(1, factorial(0))
    }

    @Test
    fun `factorial of 5 returns 120`() {
        assertEquals(120, factorial(5))
    }

    @Test
    fun `factorial of 32 returns correct value`() {
        val result = factorial(32)
        assertTrue(result > 0)
    }
}