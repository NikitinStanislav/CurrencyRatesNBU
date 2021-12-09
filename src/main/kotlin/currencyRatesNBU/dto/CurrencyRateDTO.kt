package currencyRatesNBU.dto

import java.time.Instant

data class CurrencyRateDTO(
    private val id:Long,
    private val rate:Double,
     val date: Instant
)