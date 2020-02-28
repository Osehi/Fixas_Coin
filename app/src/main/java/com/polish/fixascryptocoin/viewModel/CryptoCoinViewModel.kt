package com.polish.fixascryptocoin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.polish.fixascryptocoin.database.CryptoCoinDatabase
import com.polish.fixascryptocoin.model.CryptoCoin
import com.polish.fixascryptocoin.repository.RemoteSourceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CryptoCoinViewModel(application: Application): AndroidViewModel(application){

    private val repository = RemoteSourceRepository(CryptoCoinDatabase.getInstance(application.applicationContext))

    // create a coroutinescope
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _allCoin = MutableLiveData<List<CryptoCoin>>()
    val allCoin
    get() = _allCoin


    init {

        getDataFromRespository()
    }

    private fun getDataFromRespository(){

        viewModelScope.launch {
            _allCoin.value = repository.getCryptoCoinResponse()
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob
    }

}