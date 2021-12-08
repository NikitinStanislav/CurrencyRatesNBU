package currencyRatesNBU.client.currencyRate

import lombok.Value
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
@Slf4j
class CurrencyRateClient (private val restTemplate: RestTemplate,
                          @Value("\${currencyRatesNBU.client.url}") private val url: String){

    public fun getCurrencyRateRecord(abbreviation:String, date: String){

        val fullUrl:String = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("valcode", abbreviation)
                .queryParam("json").toUriString();

    }
}