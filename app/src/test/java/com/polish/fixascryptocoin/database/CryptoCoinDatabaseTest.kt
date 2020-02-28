package com.polish.fixascryptocoin.database

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polish.fixascryptocoin.getOrAwaitValue
import com.polish.fixascryptocoin.model.CryptoCoin
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class CryptoCoinDatabaseTest {

    private var application: Application = mockk()
    private lateinit var database:CryptoCoinDatabase
    private lateinit var cryptoCoinDao: CryptoCoinDao
    var cryptoCoin:List<CryptoCoin>? = null

    private val mainThreadTest = newSingleThreadContext("UI thread")
    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){

        MockKAnnotations.init(this)
//        cryptoCoin = List<CryptoCoin>(2)

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), CryptoCoinDatabase::class.java
        ).build()
        cryptoCoinDao = database.CryptoCoinDao()

        Dispatchers.setMain(mainThreadTest)


    }

    @After
    fun tearDown(){
        database.close()
    }


    /*
    * Data is stored into database correctly
     */
    @Test
    @Throws(Exception::class)
    fun `database stores data`(){
        runBlocking {
            launch(Dispatchers.Main){
                // when
                cryptoCoinDao.insertAll(cryptoCoin!!)

                // Then
                val outcome = cryptoCoinDao.getAllCryptoCoin()
                assertNull(outcome.getOrAwaitValue())
            }
        }
    }


}