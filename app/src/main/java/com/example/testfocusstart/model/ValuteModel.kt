package ermilov.focusstarttestovoe.model

data class ValuteModel(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    var Valute: Map<String, Valute>
)