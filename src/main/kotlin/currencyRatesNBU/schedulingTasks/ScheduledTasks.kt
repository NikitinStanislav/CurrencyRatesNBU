package currencyRatesNBU.schedulingTasks

import currencyRatesNBU.domain.Currency
import currencyRatesNBU.service.CurrencyRateService
import currencyRatesNBU.service.CurrencyService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Month

@Component
class ScheduledTasks(
    val currencyService:CurrencyService,
    val rateService: CurrencyRateService
) {
    companion object {
        var counter: Int = 1
    }

    private val logger = KotlinLogging.logger{}

    @Scheduled(cron = "0/5 * * * * *")
    fun someDemoAction(){
        val month:Month = Month.APRIL
        if(counter==month.length(false)) counter=1
        val list:Iterable<Currency> = currencyService.repoFindAll()

        val localDate:LocalDate = LocalDate.of(2018, month, counter)

        for(cur in list){
            rateService.saveCurrencyRate(cur, localDate)
        }
        counter++
        logger.info { "Update complete" }
    }

    @Scheduled(cron = "0 0 0 * * *")
    fun getRates(){
        val list:Iterable<Currency> = currencyService.repoFindAll()
        for(cur in list) {
            rateService.saveCurrencyRate(cur, null)
        }
        logger.info { "Daily saving EUR, USD and GBP rates complete" }
    }
}