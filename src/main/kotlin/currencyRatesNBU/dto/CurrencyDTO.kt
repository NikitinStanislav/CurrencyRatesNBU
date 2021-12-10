package currencyRatesNBU.dto

data class CurrencyDTO(
    var abbreviation:String,
     val code: Int?,
     val name: String?,
    val currencyRate: MutableList<CurrencyRateDTO>?,
     val id: Long?
)