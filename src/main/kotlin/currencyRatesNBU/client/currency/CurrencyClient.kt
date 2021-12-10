package currencyRatesNBU.client.currency

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class CurrencyClient(
    private val restTemplate: RestTemplate,
    @Value("\${currencyRatesNBU.client.url}") private val url: String
) {

    public fun getCurrencyRecord(abbreviation: String): CurrencyRecord {

        val fullUrl: String = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("valcode", abbreviation)
            .queryParam("json").toUriString();

        //log.info("")

        val response: ResponseEntity<List<CurrencyRecord>> = restTemplate.exchange(fullUrl, HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<CurrencyRecord>>() {})  // object?      тут изменить


        return response.body?.stream()?.findAny()?.orElse(CurrencyRecord()) ?: CurrencyRecord()

    }
}