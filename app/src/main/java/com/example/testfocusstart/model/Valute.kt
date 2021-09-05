package ermilov.focusstarttestovoe.model

data class Valute(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Long,
    val Name: String,
    val Value: Double,
    val Previous: Double
)