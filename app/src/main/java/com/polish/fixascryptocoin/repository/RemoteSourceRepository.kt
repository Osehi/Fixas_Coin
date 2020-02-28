package com.polish.fixascryptocoin.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.polish.fixascryptocoin.database.CryptoCoinDatabase
import com.polish.fixascryptocoin.model.CryptoCoin
import com.polish.fixascryptocoin.network.CryptoCoinNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


const val LIMIT_SIZE = 50

class RemoteSourceRepository(private val database: CryptoCoinDatabase) {

    // database source
    fun getAllCryptoCoin() = database.CryptoCoinDao().getAllCryptoCoin()

    suspend fun getCryptoCoinResponse(): List<CryptoCoin>{
        Log.d("CHECK", "PICK PICK")
        var data = listOf<CryptoCoin>()
        withContext(Dispatchers.IO){
            try {
                 data = CryptoCoinNetwork.cryptoCoins.getCryptoCoinRes(LIMIT_SIZE).await()
                println(data)
                Log.d("NETWORK", "${data}")
                // store data to database
                database.CryptoCoinDao().insertAll(data)


            } catch (t:Throwable){
                Log.e("ERROR", t.message)
            }
        }
        return data
    }

}