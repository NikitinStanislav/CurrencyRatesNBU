package currencyRatesNBU.domain

import java.time.Instant
import javax.persistence.*

@Entity
class CurrencyRate(
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long=0,
    //
    private val rate: Double=0.0,
    //
    private val date: Instant = Instant.now(),
    //
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private var currency: Currency = Currency()
) {

    override fun toString(): String {
        return "CurrencyRate(rate=$rate, date=$date)"
    }
}