package com.example.movienager.ui.movie.show


import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.observe
import com.example.movienager.databinding.FragmentMovieDetailsBinding
import com.example.movienager.utils.InjectorUtils
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val detailsViewModel : MovieDetailsViewModel by viewModels {
        InjectorUtils.provideMovieDetailsViewModelFactory(requireContext(), args.imdbId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = detailsViewModel
        }

        detailsViewModel.movie.observe(viewLifecycleOwner) {
            binding.viewModel = detailsViewModel
        }
        return binding.root
    }


}
