package currencyRatesNBU.repository

import currencyRatesNBU.domain.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository : CrudRepository<Currency, Long> {

    fun findByAbbreviation(abbreviation: String):Currency
}