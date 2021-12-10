package currencyRatesNBU.domain

import javax.persistence.*

@Entity
class Currency(
    //
    @Column(unique = true)
    val abbreviation:String="",
    //
     val code: Int=0,
    //
     val name: String="",
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long = 0,
    //
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currency")
     var currencyRate: MutableList<CurrencyRate> = ArrayList()
    ){

}