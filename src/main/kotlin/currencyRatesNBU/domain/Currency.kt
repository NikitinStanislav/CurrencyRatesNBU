package currencyRatesNBU.domain

import javax.persistence.*

@Entity
class Currency(
    //
    @Column(unique = true)
    val abbreviation:String="",
    //
    private val code: Int=0,
    //
    private val name: String="",
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0,
    //
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currency")
    private var currencyRate: MutableList<CurrencyRate> = ArrayList()
    ){

}