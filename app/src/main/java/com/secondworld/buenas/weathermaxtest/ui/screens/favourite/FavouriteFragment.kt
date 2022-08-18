package com.secondworld.buenas.weathermaxtest.ui.screens.favourite

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import com.secondworld.buenas.weathermaxtest.core.bases.BaseFragment
import com.secondworld.buenas.weathermaxtest.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>(FragmentFavouriteBinding::inflate) {
    override val viewModel: FavouriteViewModel by viewModels()

    private val favAdapter = FavAdapter()

    override fun initView() = with(binding){

        recyclerView.adapter = favAdapter

        favAdapter.callBackDel = {
            viewModel.deleteCity(it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initObservers() {
        viewModel.listCities.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCitiesFromBd()
    }
}