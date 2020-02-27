package com.polish.fixascryptocoin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.polish.fixascryptocoin.database.CryptoCoinDatabase
import com.polish.fixascryptocoin.database.asDomainModel
import com.polish.fixascryptocoin.model.CryptoCoin
import com.polish.fixascryptocoin.network.CryptoCoinNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.polish.fixascryptocoin.network.asDatabaseModel
import timber.log.Timber

const val LIMIT_SIZE = 50

class CryptoCoinsRepository(private val database: CryptoCoinDatabase) {

    val cyrptoCoins:LiveData<List<CryptoCoin>> = Transformations.map(database.cryptoCoinDao.getCryptoCoins()){
        it.asDomainModel()
    }


    suspend fun refreshCryptoCoins(){
        withContext(Dispatchers.IO){
            Timber.d("refresh crptoCoin is called")
            val listOfCoins = CryptoCoinNetwork.cryptoCoins.getCryptoCoinRes(LIMIT_SIZE).await()
//            databse.cryptoCoinDao.inserAll(listOfCoins.asDatabaseModel())
        }
    }


}