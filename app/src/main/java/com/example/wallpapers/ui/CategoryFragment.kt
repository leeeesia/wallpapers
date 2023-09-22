package com.example.wallpapers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpapers.R
import com.example.wallpapers.StringArg
import com.example.wallpapers.adapter.ImageAdapter
import com.example.wallpapers.adapter.OnInteractionListener
import com.example.wallpapers.databinding.FragmentCategoryBinding
import com.example.wallpapers.databinding.FragmentListCategoriesBinding
import com.example.wallpapers.model.Image
import com.example.wallpapers.viewmodel.ImageViewModel

class CategoryFragment : Fragment() {
    private val viewModel: ImageViewModel by activityViewModels()
    companion object {
        var Bundle.textArg: String? by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val query = arguments?.textArg
            ?: throw NullPointerException("Image URL is undefined")
        viewModel.updateQuery(query)
        val layoutManager =  GridLayoutManager(requireContext(), 2)
        binding.listCategories.layoutManager = layoutManager

        val adapter = ImageAdapter(object : OnInteractionListener {

            override fun onOpen(image: Image) {
                findNavController().navigate(
                    R.id.action_categoryFragment_to_imageFragment,
                    Bundle().apply {
                        textArg = image.urls?.regular
                    })
            }
        })
        viewModel.date.observe(viewLifecycleOwner){
            adapter.submitList(it.images)
        }


        binding.listCategories.adapter = adapter
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.nav_menu, menu)
    }
}