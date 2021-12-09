package com.example.shoppinglistefes.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistefes.ShoppingApplication
import com.example.shoppinglistefes.presentation.adapter.OnItemClickListener
import com.example.shoppinglistefes.presentation.adapter.ShoppingListAdapter
import com.example.shoppinglistefes.databinding.FragmentWastedBinding
import com.example.shoppinglistefes.presentation.viewmodel.PurchaseViewModel
import com.example.shoppinglistefes.presentation.viewmodel.PurchaseViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WastedFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentWastedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PurchaseViewModel by viewModels()
    private var shopAdapter: ShoppingListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWastedBinding.inflate(
            inflater,
            container,
            false
        )
        shopAdapter = ShoppingListAdapter(this)
        binding.recyclerViewWasted.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shopAdapter
        }
        lifecycle.coroutineScope.launch {
            viewModel.allWastedPurchase.collect() { list ->
                shopAdapter?.let { adapter ->
                    adapter.submitList(list)
                    binding.priceWastedId.text = "${list.sumOf { it.price!! }} ₪"
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(
            this.context,
            "Your purchase is complete",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}