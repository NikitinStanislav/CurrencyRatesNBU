package currencyRatesNBU.repository

import currencyRatesNBU.domain.Currency
import currencyRatesNBU.domain.CurrencyRate
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Table

@Table(name ="currencyRate")
interface CurrencyRateRepository : JpaRepository<CurrencyRate, Long>{

    fun findAllByCurrency(currency: Currency):List<CurrencyRate>
}