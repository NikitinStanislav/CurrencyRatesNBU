package currencyRatesNBU.client

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Configuration {

    @Bean
    fun restTemplate() : RestTemplate {
        val builder = RestTemplateBuilder()
        return builder.build()
    }
}