package currencyRatesNBU

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CurrencyRatesNbuApplication

fun main(args: Array<String>) {
    runApplication<CurrencyRatesNbuApplication>(*args)
}
