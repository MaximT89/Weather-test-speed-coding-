package com.secondworld.buenas.weathermaxtest.ui.screens.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.secondworld.buenas.weathermaxtest.databinding.FavouriteHolderBinding
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain

class FavAdapter : ListAdapter<CityFavouriteDomain, FavAdapter.FavouriteHolder>(ItemComparator()) {

    var callBackDel: ((city: String) -> Unit)? = null

    class ItemComparator : DiffUtil.ItemCallback<CityFavouriteDomain>() {
        override fun areItemsTheSame(
            oldItem: CityFavouriteDomain,
            newItem: CityFavouriteDomain
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: CityFavouriteDomain,
            newItem: CityFavouriteDomain
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class FavouriteHolder(private val binding: FavouriteHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityFavouriteDomain) = with(binding) {

            city.text = item.city
            temperature.text = item.temperature
            btnDel.setOnClickListener {
                callBackDel?.invoke(city.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavouriteHolder(
            FavouriteHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: FavouriteHolder, position: Int) {
        holder.bind(getItem(position))
    }
}