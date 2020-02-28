package com.polish.fixascryptocoin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.polish.fixascryptocoin.model.CryptoCoin

@Database(entities = [CryptoCoin::class], version = 1, exportSchema = false)
abstract class CryptoCoinDatabase: RoomDatabase() {

    abstract fun CryptoCoinDao(): CryptoCoinDao

    companion object {

        @Volatile
        private var INSTANCE:CryptoCoinDatabase? = null
        fun getInstance(context: Context):CryptoCoinDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CryptoCoinDatabase::class.java,
                        "cryptoCoin_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}