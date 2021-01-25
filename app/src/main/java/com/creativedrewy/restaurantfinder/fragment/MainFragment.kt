package com.creativedrewy.restaurantfinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativedrewy.restaurantfinder.adapter.StockListAdapter
import com.creativedrewy.restaurantfinder.databinding.MainFragmentBinding
import com.creativedrewy.restaurantfinder.viewmodel.ErrorResult
import com.creativedrewy.restaurantfinder.viewmodel.Loading
import com.creativedrewy.restaurantfinder.viewmodel.MainViewModel
import com.creativedrewy.restaurantfinder.viewmodel.StockList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewBinding: MainFragmentBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy { StockListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = MainFragmentBinding.inflate(inflater, container, false)

        viewBinding.stockListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.stockListRecyclerview.adapter = adapter

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is Loading -> {
                    adapter.submitList(it.loadingItems)
                }
                is StockList -> {
                    if (it.stocks.isEmpty()) {
                        viewBinding.emptyIconTextview.visibility = View.VISIBLE
                        viewBinding.emptyResultTextview.visibility = View.VISIBLE
                        adapter.submitList(listOf())
                    } else {
                        adapter.submitList(it.stocks)
                    }
                }
                is ErrorResult -> {
                    with (viewBinding) {
                        stockListRecyclerview.visibility = View.GONE
                        cryTextview.visibility = View.VISIBLE
                        sorryTextTextview.visibility = View.VISIBLE
                    }
                }
            }
        })

        viewModel.loadStocks()
    }

}