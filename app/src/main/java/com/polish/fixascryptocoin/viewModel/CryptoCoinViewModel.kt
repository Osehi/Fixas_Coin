package com.polish.fixascryptocoin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polish.fixascryptocoin.database.CryptoCoinDatabase
import com.polish.fixascryptocoin.model.CryptoCoin
import com.polish.fixascryptocoin.repository.RemoteSourceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class CryptoCoinViewModel(application: Application): AndroidViewModel(application){

    private val repository = RemoteSourceRepository(CryptoCoinDatabase.getInstance(application.applicationContext))

    val allCryptoCoin = repository.getAllCryptoCoin()


    private val _workingModel = MutableLiveData<Boolean>(true)
    val workingModel:LiveData<Boolean> = _workingModel


    // create a coroutinescope
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _allCoin = MutableLiveData<List<CryptoCoin>>()
    val allCoin
    get() = _allCoin

    /*
    * Flag to display error message
     */
//    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
//    val isNetworkErrorShown: LiveData<Boolean>
//    get() = _isNetworkErrorShown


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