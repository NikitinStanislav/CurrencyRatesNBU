package currencyRatesNBU.client.currency

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrencyRecord(
    @JsonProperty("cc")
    private val abbreviation:String="",
    @JsonProperty("r030")
     val code:Int=0,
    @JsonProperty("txt")
     val name:String=""
){
    override fun toString(): String {
        return "CurrencyRecord(abbreviation='$abbreviation', code=$code, name='$name')"
    }
}