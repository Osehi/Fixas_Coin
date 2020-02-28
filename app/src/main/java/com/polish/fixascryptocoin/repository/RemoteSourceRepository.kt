package com.polish.fixascryptocoin.repository

import android.util.Log
import com.polish.fixascryptocoin.database.CryptoCoinDatabase
import com.polish.fixascryptocoin.network.CryptoCoinNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


const val LIMIT_SIZE = 50

class RemoteSourceRepository(private val database: CryptoCoinDatabase) {

    suspend fun getCryptoCoinResponse(){
        withContext(Dispatchers.IO){
            try {
                var data = CryptoCoinNetwork.cryptoCoins.getCryptoCoinRes(LIMIT_SIZE).await()
                println(data)
                // store data to database
//                database.CryptoCoinDao().insertAll(data)


            } catch (t:Throwable){
                Log.e("ERROR", t.message)
            }
        }
    }

}