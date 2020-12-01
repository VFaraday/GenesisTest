package com.bobryshev.genesistest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobryshev.genesistest.databinding.FragmentFavoritesBinding
import com.bobryshev.genesistest.ui.main.adapter.GitRepoFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var layout: FragmentFavoritesBinding
    private lateinit var mAdapter: GitRepoFavoriteAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = FragmentFavoritesBinding.inflate(inflater, container, false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        viewModel.listFavorites.observe(viewLifecycleOwner, { favorites -> mAdapter.notifyData(favorites)})
    }

    private fun setupAdapter() {
        mAdapter = GitRepoFavoriteAdapter(requireContext(), removeFromFavorite = { repo -> viewModel.removeFromFavorite(repo) })
        layout.rvFavorites.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }
}