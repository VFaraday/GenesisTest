package com.bobryshev.genesistest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobryshev.genesistest.R
import com.bobryshev.genesistest.data.network.Result
import com.bobryshev.genesistest.databinding.MainFragmentBinding
import com.bobryshev.genesistest.ui.main.adapter.GitRepoAdapter
import com.bobryshev.genesistest.ui.util.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mAdapter: GitRepoAdapter
    private lateinit var layout: MainFragmentBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = MainFragmentBinding.inflate(inflater)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        viewModel.listFavorites.observe(viewLifecycleOwner, { favorites ->
            mAdapter.setFavorite(favorites)
        })

        viewModel.listRepos.observe(viewLifecycleOwner, { result ->
            when(result) {
                is Result.Success ->  mAdapter.notifyData(result.data)
                is Result.Failure -> Toast.makeText(requireContext(), result.exception.orEmpty(), Toast.LENGTH_LONG).show()
            }
        })

        layout.refresh.setOnRefreshListener {
            viewModel.refresh()
            layout.refresh.isRefreshing = false
        }

        layout.btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }
    }

    private fun setupAdapter() {
        mAdapter = GitRepoAdapter(requireContext(), addToFavorite = { repo -> viewModel.addToFavorite(repo)}, removeFromFavorite = { repo -> viewModel.removeFromFavorite(repo)})
        layout.rvList.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        layout.rvList.addOnScrollListener(object : PaginationScrollListener() {
            override fun onReachedTheEnd() {
                viewModel.loadNextPage()
            }
        })
    }
}