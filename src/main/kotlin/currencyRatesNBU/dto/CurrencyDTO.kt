package currencyRatesNBU.dto

data class CurrencyDTO(
    var abbreviation:String,
    private val code: Int,
    private val name: String,
    val currencyRate: MutableList<CurrencyRateDTO>,
    private val id: Long
)