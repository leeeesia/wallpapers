package com.example.wallpapers.ui

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentImageBinding
import com.example.wallpapers.databinding.FragmentListCategoriesBinding
import com.example.wallpapers.ui.CategoryFragment.Companion.textArg
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentImageBinding.inflate(inflater, container, false)
        val url = arguments?.textArg ?:throw NullPointerException("Image URL is undefined")
        binding.apply {
            Glide.with(imageV)
                .load(url)
                .timeout(10_000)
                .into(imageV)
            setWallpaper.setOnClickListener {
                downloadAndSetWallpaper(url)
            }
        }
        return binding.root

    }

    private fun setWallpaper(bitmap: Bitmap) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(requireActivity())
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(requireContext(), "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadAndSetWallpaper(imageUrl: String) {
        Thread {
            try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(input)

                activity?.runOnUiThread {
                    setWallpaper(bitmap)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                activity?.runOnUiThread {
                    Toast.makeText(activity, "Failed to download image", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}