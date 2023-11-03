package com.example.delivery_app.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_app.App
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.databinding.FragmentMenuBinding
import com.example.delivery_app.ui.menu.adapter.ProductsAdapter
import com.example.delivery_app.utils.Status

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private lateinit var adapter: ProductsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MenuViewModel by viewModels {
        (requireActivity().application as App).appComponent.viewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        if (viewModel.isEmpty()) {
            viewModel.fetchData()
        }
    }

    fun setupView() {
        binding.apply {
            adapter = ProductsAdapter({ }, mutableListOf())
            rvProducts.layoutManager = LinearLayoutManager(context)
            rvProducts.adapter = adapter

            pullToRefresh.setOnRefreshListener {
                viewModel.fetchData()
            }
        }
    }

    fun setupObserver() {
        viewModel.productsList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.pullToRefresh.isRefreshing = false
                    renderList(it.data!!)
                }
                Status.LOADING -> {
                    binding.pullToRefresh.isRefreshing = true
                }
                Status.ERROR -> {
                    binding.pullToRefresh.isRefreshing = false
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderList(products: List<Product>) {
        adapter.clear()
        adapter.addData(products)
        adapter.notifyDataSetChanged()
    }
}