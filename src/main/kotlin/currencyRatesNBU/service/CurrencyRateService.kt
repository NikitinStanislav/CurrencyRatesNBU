package currencyRatesNBU.service

import currencyRatesNBU.client.currencyRate.CurrencyRateClient
import currencyRatesNBU.client.currencyRate.CurrencyRateRecord
import currencyRatesNBU.domain.Currency
import currencyRatesNBU.domain.CurrencyRate
import currencyRatesNBU.repository.CurrencyRateRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Service
class CurrencyRateService(
    val client: CurrencyRateClient,
    val currencyRateRepository: CurrencyRateRepository
) {
    @Autowired
    private lateinit var currencyService: CurrencyService

    private val logger = KotlinLogging.logger{}

    fun saveCurrencyRate(currency: Currency?, date: LocalDate?){
        if(currency==null) return

        val localDate = date ?: LocalDate.now()
        val dateString: String = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        val curRateRec: CurrencyRateRecord = client.getCurrencyRateRecord(currency.abbreviation, dateString)
            try{
                currencyRateRepository.save(
                    CurrencyRate(
                    rate=curRateRec.rate,
                    date = localDate.atStartOfDay().toInstant(ZoneOffset.UTC),
                    currency = currency)
                )
                logger.info{"Currency rates for ${currency.abbreviation} on $date saved"}
            } catch(ex: DataIntegrityViolationException){
                logger.error{"Trying to add a duplicating values: currency ${currency.abbreviation}, date $date"}
            }
    }


    fun saveCurrencyRateFromAbbreviation(abbreviation:String, date:LocalDate){
        val currency:Currency = currencyService.getCurrencyFromAbbreviation(abbreviation)
        saveCurrencyRate(currency, date)
    }

    fun deleteCurrencyRates(currency: Currency){
        val cur:List<CurrencyRate> = currencyRateRepository.findAllByCurrency(currency)
        currencyRateRepository.deleteAll(cur)
    }

}