package com.polish.fixascryptocoin.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.polish.fixascryptocoin.adapter.DisplayCoin
import com.polish.fixascryptocoin.databinding.FragmentCryptoCoinDisplayBinding
import com.polish.fixascryptocoin.viewModel.CryptoCoinViewModel

/**
 * A simple [Fragment] subclass.
 */
class CryptoCoin_Display : Fragment() {

    private lateinit var cryptoCoinViewModel:CryptoCoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_crypto_coin__display, container, false)

        val binding = FragmentCryptoCoinDisplayBinding.inflate(inflater, container, false)

        cryptoCoinViewModel = ViewModelProvider(this).get(CryptoCoinViewModel::class.java)

        val recyclerView = binding.recyclerViewId
        recyclerView.layoutManager = LinearLayoutManager(context)

        var adapter = DisplayCoin(DisplayCoin.OnClickListener{})

        recyclerView.adapter = adapter

//        cryptoCoinViewModel.allCoin.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })

        if(isNetworkConnected(context!!)){
            Log.d("AVAILABLE", "working")
            println("yes")
            cryptoCoinViewModel.allCoin.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
        } else{
            Toast.makeText(context, "check internet connection", Toast.LENGTH_SHORT).show()
            cryptoCoinViewModel.allCryptoCoin.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })

        }




        return binding.root
    }


    private fun isNetworkConnected(context: Context):Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (!isConnected){
            Toast.makeText(context, "Check network connection", Toast.LENGTH_SHORT).show()
        }
        return isConnected
    }


}
