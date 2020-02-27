package com.polish.fixascryptocoin.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polish.fixascryptocoin.model.CryptoCoin
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCoinService {

    @GET("ticker")
    fun getCryptoCoinRes(@Query("linit") limit:Int): Deferred<List<CryptoCoin>>

}


/*
    The entry point for network access
 */

object CryptoCoinNetwork {

    private const val URL = "https://api.coinmarketcap.com/v1/"

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val cryptoCoins = retrofit.create(CryptoCoinService::class.java)

}