package com.example.delivery_app.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_app.App
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.databinding.FragmentMenuBinding
import com.example.delivery_app.ui.menu.adapter.BannersAdapter
import com.example.delivery_app.ui.menu.adapter.CategoriesAdapter
import com.example.delivery_app.ui.menu.adapter.ProductsAdapter
import com.example.delivery_app.ui.utils.OffsetItemDecoration
import com.example.delivery_app.utils.Status

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var bannersAdapter: BannersAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

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

    private fun setupView() {
        productsAdapter = ProductsAdapter({ }, mutableListOf())
        bannersAdapter = BannersAdapter({ }, viewModel.banners.value!!.toMutableList())
        categoriesAdapter = CategoriesAdapter({ categoryName -> changeSelected(categoryName) }, viewModel.categories.value!!, 0)


        binding.apply {
            pullToRefresh.setOnRefreshListener {
                viewModel.fetchData()
            }

            rvProducts.layoutManager = LinearLayoutManager(context)
            rvProducts.adapter = productsAdapter
            rvProducts.addItemDecoration(OffsetItemDecoration(bottomOffset = 8))

            rvBanners.adapter = bannersAdapter
            rvBanners.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rvBanners.addItemDecoration(OffsetItemDecoration(rightOffset = 32))

            rvCategories.adapter = categoriesAdapter
            rvCategories.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rvCategories.addItemDecoration(OffsetItemDecoration(rightOffset = 16))
        }
    }

    private fun setupObserver() {
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

    private fun changeSelected(selectedCategory: String) {
        if (selectedCategory == viewModel.selectedCategory) {
            return
        }
        viewModel.categories.value!![viewModel.selectedCategory] = false
        viewModel.categories.value!![selectedCategory] = true
        viewModel.selectedCategory = selectedCategory
    }

    private fun renderList(products: List<Product>) {
        productsAdapter.clear()
        productsAdapter.addData(products)
        productsAdapter.notifyDataSetChanged()
    }
}