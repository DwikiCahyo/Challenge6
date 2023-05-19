@file:Suppress("unused")

package com.dwiki.movieapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dwiki.movieapplication.R
import com.dwiki.movieapplication.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("unused")
@AndroidEntryPoint
class DetailDialogFragment(
    val title:String,
    private val releaseDate:String,
    private val overview:String,
    private val imagePath:String,
    private val idMovie:Int
):DialogFragment() {

    private val viewModel:FavoriteViewModel by viewModels()
    private var isMovieFavorite  = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.layout_dialog_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvReleaseDate = view.findViewById<TextView>(R.id.tv_release_date)
        val tvOverview = view.findViewById<TextView>(R.id.tv_overview)
        val tvImagePath = view.findViewById<ImageView>(R.id.iv_poster_image)
        val btnFavorite = view.findViewById<ToggleButton>(R.id.btn_favorite)

        tvTitle.text = title
        tvReleaseDate.text = "Release Date : $releaseDate"
        tvOverview.text = overview
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/$imagePath").into(tvImagePath)

        setClickFavorite(btnFavorite)
        checkMovieFavorite(btnFavorite)
    }

    private fun checkMovieFavorite(btnFavorite: ToggleButton) {
        CoroutineScope(Dispatchers.IO).launch {
            val check = viewModel.checkUserFavorite(idMovie)
            withContext(Dispatchers.Main) {
                if (check != null) {
                    if (check > 0) {
                        btnFavorite.isChecked = true
                        isMovieFavorite = true
                    } else {
                        btnFavorite.isChecked = false
                        isMovieFavorite = false
                    }
                }
            }
        }
    }

    private fun setClickFavorite(btnFavorite: ToggleButton) {
        btnFavorite.setOnClickListener {
            isMovieFavorite = !isMovieFavorite
            if (isMovieFavorite) {
                if (idMovie != null) {
                    viewModel.insertMovie(title, idMovie, imagePath, releaseDate,overview)
                    Toast.makeText(
                        requireContext(),
                        "Berhasil Ditambahkan ke Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "gagal Ditambakan", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (idMovie != null) {
                    viewModel.removeMovie(idMovie)
                    Toast.makeText(requireContext(), "Berhasil dihapus", Toast.LENGTH_SHORT)
                        .show()
                    dismiss()
                }
            }
        }

        btnFavorite.isChecked = isMovieFavorite
    }
}