package currencyRatesNBU.service

import currencyRatesNBU.client.currency.CurrencyClient
import currencyRatesNBU.client.currency.CurrencyRecord
import currencyRatesNBU.domain.Currency
import currencyRatesNBU.dto.CurrencyDTO
import currencyRatesNBU.repository.CurrencyRepository
import org.modelmapper.ModelMapper
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@Service
class CurrencyService(
    private val client:CurrencyClient,
    private val currencyRepository: CurrencyRepository,
    private val currencyRateService: CurrencyRateService
) {

    fun findExactCurrencyByValcode(abbreviation:String?):List<CurrencyDTO>{
        if(abbreviation==null)    //тут разберись с null
            return getDTOFromEntities(getFullListOfCurrencies())

        return getDTOFromEntities(listOf(currencyRepository.findByAbbreviation(abbreviation)))
        }

    fun findExactCurrencyById(id:Long):List<CurrencyDTO>{
        val list:MutableList<Currency> = ArrayList()
        list.add(currencyRepository.findById(id).get())
        return getDTOFromEntities(list)
    }

    fun saveCurrency(currencyDTO:List<CurrencyDTO>){
        for(cur in currencyDTO){
            val abbreviation:String = cur.abbreviation
            val curRec:CurrencyRecord = client.getCurrencyRecord(abbreviation)
            try{
                currencyRepository.save(Currency(abbreviation, curRec.code, curRec.name))
            } catch (ex: DataIntegrityViolationException){
                    //log.error("Trying to add a duplicate currency: " + abbreviation)
            }catch(ex: NullPointerException){
                //log.error("Wrong name. Currency " + abbreviation + " not added")
            }
        }
    }

    fun deleteCurrency(id:Long){
        currencyRateService.deleteCurrencyRates(currencyRepository.findById(id).get())
        currencyRepository.deleteById(id)
    }

    fun getCurrencyByDate(id:Long, date:LocalDate?) : List<CurrencyDTO>{
        val localDate = date ?: LocalDate.now()
        val instant:Instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
        val result:List<CurrencyDTO> = findExactCurrencyById(id)

        for(currencyDTO in result){
            currencyDTO.currencyRate.removeIf{ it.date != instant }
        }
        return result
    }

    private fun getFullListOfCurrencies() : List<Currency>{
        val iters:Iterable<Currency> = currencyRepository.findAll()
        val list:MutableList<Currency> = ArrayList()
        for(cur in iters){
            list.add(cur)
        }
        return list;
    }

    private fun getDTOFromEntities(currencies:List<Currency>):List<CurrencyDTO>{
        val result:MutableList<CurrencyDTO> = ArrayList()
        val modelMapper:ModelMapper = ModelMapper()

        for(cur in currencies){
            val currencyDTO:CurrencyDTO = modelMapper.map(cur, CurrencyDTO::class)  // хз че тут
            result.add(currencyDTO)
        }
        return result
    }

    fun getCurrencyFromAbbreviation(abbreviation: String):Currency{
        return currencyRepository.findByAbbreviation(abbreviation)
    }
}