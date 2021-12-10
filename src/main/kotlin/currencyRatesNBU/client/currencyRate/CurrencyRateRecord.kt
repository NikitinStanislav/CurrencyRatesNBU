package currencyRatesNBU.client.currencyRate

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrencyRateRecord(
     val rate:Double=0.0
) {
    override fun toString(): String {
        return "CurrencyRateRecord(rate=$rate)"
    }
}