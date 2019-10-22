package com.example.movienager.ui.movie.list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movienager.adapters.MovieRecyclerViewAdapter
import com.example.movienager.databinding.FragmentMovieListBinding
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movienager.R


import com.example.movienager.utils.InjectorUtils


class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels {
        InjectorUtils.provideMovieViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MovieRecyclerViewAdapter()
        binding.list.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.list.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            if(it != null) adapter.submitList(it)
            binding.isEmpty = it.isEmpty()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.menuBtnSearch)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                Toast.makeText(requireContext(), query.toString(), Toast.LENGTH_SHORT).show()

                viewModel.fetchMovies(query.toString())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
    }

}
