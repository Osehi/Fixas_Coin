package com.polish.fixascryptocoin.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polish.fixascryptocoin.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class CryptoCoinViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getCryptoCoinData(){

        // Given CryptoCoinViewModel
        val cryptoCoinViewModel = CryptoCoinViewModel(ApplicationProvider.getApplicationContext())

        assertEquals(cryptoCoinViewModel.workingModel.getOrAwaitValue(), true)

    }

}