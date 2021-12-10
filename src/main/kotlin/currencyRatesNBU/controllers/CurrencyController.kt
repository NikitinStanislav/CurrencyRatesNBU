package currencyRatesNBU.controllers

import currencyRatesNBU.dto.CurrencyDTO
import currencyRatesNBU.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/currency")
class CurrencyController(){

    @Autowired
    private lateinit var currencyService:CurrencyService

    @GetMapping
    fun getCurrencies(
        @RequestParam(value="valcode", required = false) abbreviation:String?,
        @RequestParam(value = "id", required = false) id:Long?
    ): List<CurrencyDTO>{

        return if(id==null) currencyService.findExactCurrencyByValcode(abbreviation) else currencyService.findExactCurrencyById(id)
    }

    @GetMapping("/{id}")
    fun getCurrencyByDate(
            @PathVariable id:Long,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?
    ) : List<CurrencyDTO>{

        return currencyService.getCurrencyByDate(id, date)
    }

    @PostMapping
    @ResponseBody
    fun save(@RequestBody currencyDTO: List<CurrencyDTO>) = currencyService.saveCurrency(currencyDTO)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long) = currencyService.deleteCurrency(id)


}