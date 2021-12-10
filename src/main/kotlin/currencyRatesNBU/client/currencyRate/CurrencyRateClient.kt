package currencyRatesNBU.client.currencyRate

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class CurrencyRateClient (
    private val restTemplate: RestTemplate,
    @Value("\${currencyRatesNBU.client.url}") private val url: String
    ){

    public fun getCurrencyRateRecord(abbreviation:String, date: String) : CurrencyRateRecord{

        val fullUrl:String = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("valcode", abbreviation)
                .queryParam("json").toUriString();

        //log.info("ResponseEntity creating");

        val response: ResponseEntity<List<CurrencyRateRecord>> = restTemplate.exchange(fullUrl, HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<CurrencyRateRecord>>() {})  // object?

        return response.body?.stream()?.findAny()?.orElse(CurrencyRateRecord()) ?: CurrencyRateRecord()
    }
}