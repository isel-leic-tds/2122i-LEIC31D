// factorial(0) -> 1
// factorial(n) -> n * factorial(n-1)

fun factorial(value: Int): Int =
    when(value) {
        0 -> 1
        else -> value * factorial(value-1)
    }
