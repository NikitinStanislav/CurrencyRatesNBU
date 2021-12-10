package currencyRatesNBU.domain

import java.time.Instant
import javax.persistence.*

@Entity
data class CurrencyRate(
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long=0,
    //
     val rate: Double=0.0,
    //
     val date: Instant = Instant.now(),
    //
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private var currency: Currency = Currency()
) {

    override fun toString(): String {
        return "CurrencyRate(rate=$rate, date=$date)"
    }
}