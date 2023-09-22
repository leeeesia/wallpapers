package com.example.wallpapers.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentListCategoriesBinding
import com.example.wallpapers.ui.CategoryFragment.Companion.textArg

class ListCategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentListCategoriesBinding.inflate(inflater, container, false)

        binding.apply {
            Glide.with(imageButton)
                .load("https://images.unsplash.com/photo-1484406566174-9da000fda645?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1889&q=80")
                .centerCrop()
                .timeout(10_000)
                .into(imageButton)
            imageButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_listCategoriesFragment_to_categoryFragment,
                    Bundle().apply {
                        textArg = "animal"
                    })
            }
            Glide.with(imageButton2)
                .load("https://images.unsplash.com/photo-1580414057403-c5f451f30e1c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80")
                .centerCrop()
                .timeout(10_000)
                .into(imageButton2)

            imageButton2.setOnClickListener {
                findNavController().navigate(
                    R.id.action_listCategoriesFragment_to_categoryFragment,
                    Bundle().apply {
                        textArg = "car"
                    })
            }
            Glide.with(imageButton3)
                .load("https://images.unsplash.com/photo-1447875569765-2b3db822bec9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fGZsb3dlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60")
                .centerCrop()
                .timeout(10_000)
                .into(imageButton3)
            imageButton3.setOnClickListener {
                findNavController().navigate(
                    R.id.action_listCategoriesFragment_to_categoryFragment,
                    Bundle().apply {
                        textArg = "flower"
                    })
            }
            Glide.with(imageButton4)
                .load("https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1981&q=80")
                .centerCrop()
                .timeout(10_000)
                .into(imageButton4)
            imageButton4.setOnClickListener {
                findNavController().navigate(
                    R.id.action_listCategoriesFragment_to_categoryFragment,
                    Bundle().apply {
                        textArg = "food"
                    })
            }
            Glide.with(imageButton5)
                .load("https://images.unsplash.com/photo-1497465135434-9dc15238075a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1716&q=80")
                .centerCrop()
                .timeout(10_000)
                .into(imageButton5)
            imageButton5.setOnClickListener {
                findNavController().navigate(
                    R.id.action_listCategoriesFragment_to_categoryFragment,
                    Bundle().apply {
                        textArg = "sky"
                    })
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_settings -> {
                findNavController().navigate(R.id.action_listCategoriesFragment_to_settingsFragment)
                true
            }
            else -> false
        }
    }



}