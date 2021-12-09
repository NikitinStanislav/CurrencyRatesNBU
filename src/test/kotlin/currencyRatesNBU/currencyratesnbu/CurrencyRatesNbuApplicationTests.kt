package currencyRatesNBU.currencyratesnbu

import currencyRatesNBU.client.currencyRate.CurrencyRateClient
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CurrencyRatesNbuApplicationTests(
    val client: CurrencyRateClient
) {

    @Test
    fun contextLoads() {
        client.getCurrencyRateRecord("EUR", "20101010")
    }

}
