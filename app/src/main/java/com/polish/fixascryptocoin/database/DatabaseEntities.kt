package com.polish.fixascryptocoin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.polish.fixascryptocoin.model.CryptoCoin


/*
    DatabaseCryptoCoin represents a cryptoCoin entity in the database
 */
@Entity
data class DatabaseCryptoCoin constructor(
    @SerializedName("available_supply")
    val availableSupply: String,
    @SerializedName("24h_volume_usd")
    val hVolumeUsd: String,
    @PrimaryKey
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("market_cap_usd")
    val marketCapUsd: String,
    @SerializedName("max_supply")
    val maxSupply: String? = null,
    val name: String,
    @SerializedName("percent_change_1h")
    val percentChange1h: String,
    @SerializedName("percent_change_24h")
    val percentChange24h: String,
    @SerializedName("percent_change_7d")
    val percentChange7d: String,
    @SerializedName("price_btc")
    val priceBtc: String,
    @SerializedName("price_usd")
    val priceUsd: String,
    val rank: String,
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: String

)

/*
    Map DatabaseCryptoCoins to domain entities
 */

fun List<DatabaseCryptoCoin>.asDomainModel(): List<CryptoCoin> {

    return map {
        CryptoCoin(
            availableSupply = it.availableSupply,
            hVolumeUsd = it.hVolumeUsd,
            id = it.id,
            lastUpdated = it.lastUpdated,
            marketCapUsd =it.marketCapUsd,
            name = it.name,
            percentChange1h = it.percentChange1h,
            percentChange24h = it.percentChange24h,
            percentChange7d = it.percentChange7d,
            priceBtc = it.priceBtc,
            priceUsd = it.priceUsd,
            rank = it.rank,
            symbol = it.symbol,
            totalSupply = it.totalSupply
        )
    }

}