package com.polish.fixascryptocoin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polish.fixascryptocoin.model.CryptoCoin


@Dao
interface CryptoCoinDao {

    @Query("SELECT * FROM cryptoCoin_table ORDER BY id DESC")
    fun getAllCryptoCoin(): LiveData<List<CryptoCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cryptoCoin:List<CryptoCoin>)

}