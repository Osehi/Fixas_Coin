package com.polish.fixascryptocoin.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class CryptoCoinDatabase: RoomDatabase() {

    abstract val cryptoCoinDao: CryptoCoinDao

    companion object {

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