package currencyRatesNBU.controllers

import currencyRatesNBU.service.CurrencyRateService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/currency-rate")
class CurrencyRateController(
    private val currencyRateService: CurrencyRateService
) {

    @PostMapping
    fun save(
        @RequestParam("valcode") abbreviation:String,
        @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ) {
        currencyRateService.saveCurrencyRateFromAbbreviation(abbreviation, date)
    }
}