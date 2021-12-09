package currencyRatesNBU.client.currencyRate

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrencyRateRecord(
     val rate:Double
) {
    override fun toString(): String {
        return "CurrencyRateRecord(rate=$rate)"
    }
}