package com.polish.fixascryptocoin.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface CryptoCoinDao {

    @Query("select * from databaseCryptoCoin")
    fun getCryptoCoins(): LiveData<List<DatabaseCryptoCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins:List<DatabaseCryptoCoin>)

}