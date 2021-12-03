package com.example.shoppinglistefes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.shoppinglistefes.data.Purchase
import com.example.shoppinglistefes.databinding.FragmentAddPurchaseBinding
import com.example.shoppinglistefes.viewmodel.PurchaseViewModel
import com.example.shoppinglistefes.viewmodel.PurchaseViewModelFactory
import com.example.shoppinglistefes.viewmodel.SharedViewModel

class AddPurchaseFragment : Fragment() {
    private var _binding: FragmentAddPurchaseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PurchaseViewModel by viewModels {
        PurchaseViewModelFactory((activity?.application as ShoppingApplication).repository)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPurchaseBinding.inflate(
            inflater,
            container,
            false
        )
        binding.apply {
            editTextNumber.isVisible = false
            priceId.isVisible = false
            buttonAdd.setText(getString(R.string.addButtonAddName))
        }
        sharedViewModel.selected.observe(viewLifecycleOwner, Observer<Purchase?> { purchase ->
            purchase?.name?.let { name ->
                binding.apply {
                    editTextPurchase.setText(name)
                    editTextNumber.isVisible = true
                    priceId.isVisible = true
                    buttonAdd.setText(getString(R.string.addButtonBuyName))
                }
            }
        })
        binding.buttonAdd.setOnClickListener {
            sharedViewModel.selected.observe(viewLifecycleOwner, Observer<Purchase?> { purchase ->
                if (purchase != null) {
                    purchase.name = binding.editTextPurchase.text.toString()
                    purchase.price = Integer.parseInt(binding.editTextNumber.text.toString())
                    viewModel.updatePurchase(purchase)
                    findNavController().navigate(R.id.action_addPurchaseFragment_to_homeFragment)
                } else {
                    val purch = Purchase(
                        name = binding.editTextPurchase.text.toString(),
                    )
                    viewModel.insertPurchase(purch)
                    findNavController().navigate(R.id.action_addPurchaseFragment_to_homeFragment)
                }
            })
        }
        return binding.root
    }
}
