package com.polish.fixascryptocoin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polish.fixascryptocoin.databinding.CryptoItemBinding
import com.polish.fixascryptocoin.model.CryptoCoin

class DisplayCoin(val onclickListener:OnClickListener):ListAdapter<CryptoCoin, DisplayCoin.CryptoCoinViewHolder>(DiffCallback) {

    class CryptoCoinViewHolder(val binding: CryptoItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoCoin:CryptoCoin){
            binding.cryptoCoin = cryptoCoin

            binding.executePendingBindings()
        }
    }


    companion object DiffCallback: DiffUtil.ItemCallback<CryptoCoin>(){
        override fun areItemsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCoinViewHolder {
        return CryptoCoinViewHolder(CryptoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CryptoCoinViewHolder, position: Int) {

        val cryptoCoin = getItem(position)

        holder.itemView.setOnClickListener {
            onclickListener.onClick(cryptoCoin)
        }

        holder.bind(cryptoCoin)

    }

    class OnClickListener(val clickListener:(cryptoCoin:CryptoCoin) -> Unit){
        fun onClick(cryptoCoin:CryptoCoin) = clickListener(cryptoCoin)
    }

}


