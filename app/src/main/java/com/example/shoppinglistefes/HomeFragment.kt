package com.example.shoppinglistefes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistefes.adapter.ShoppingListAdapter
import com.example.shoppinglistefes.databinding.FragmentHomeBinding
import com.example.shoppinglistefes.viewmodel.PurchaseViewModel
import com.example.shoppinglistefes.viewmodel.PurchaseViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val myViewModel: PurchaseViewModel by viewModels {
        PurchaseViewModelFactory((activity?.application as ShoppingApplication).repository)
    }
    private var shoppingAdapter: ShoppingListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerId.layoutManager = LinearLayoutManager(context)
        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addPurchaseFragment)
        }
        shoppingAdapter = ShoppingListAdapter()
        binding.recyclerId.adapter = shoppingAdapter
        lifecycleScope.launch {
            myViewModel.allPurchase.observe(viewLifecycleOwner, Observer { list ->
                shoppingAdapter?.let {
                    it.submitList(list)
                }
            })
        }
        return binding.root

    }
}