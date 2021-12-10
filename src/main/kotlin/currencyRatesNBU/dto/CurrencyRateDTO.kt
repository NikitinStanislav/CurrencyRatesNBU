package currencyRatesNBU.dto

import java.time.Instant

data class CurrencyRateDTO(
     val id:Long,
     val rate:Double,
     val date: Instant
)