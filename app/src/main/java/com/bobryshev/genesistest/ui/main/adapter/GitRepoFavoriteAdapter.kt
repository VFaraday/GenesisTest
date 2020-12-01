package com.bobryshev.genesistest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobryshev.genesistest.R
import com.bobryshev.genesistest.data.repository.domain.GitRepo
import com.bobryshev.genesistest.databinding.ItemGitRepoBinding

class GitRepoFavoriteAdapter(private val context: Context, val removeFromFavorite: (gitRepo: GitRepo) -> Unit):
    RecyclerView.Adapter<GitRepoViewHolder>() {

    private val favoriteRepo: MutableList<GitRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder = GitRepoViewHolder(
        ItemGitRepoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        val repo = favoriteRepo[holder.absoluteAdapterPosition]
        val layout = holder.layout
        layout.tvName.text = repo.name
        layout.tvDescription.text = repo.description
        layout.ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        layout.ivFavorite.setOnClickListener {
            removeFromFavorite(repo)
            favoriteRepo.remove(repo)
            notifyItemRemoved(holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int = favoriteRepo.size

    fun notifyData(favoriteRepo: List<GitRepo>) {
        this.favoriteRepo.clear()
        this.favoriteRepo.addAll(favoriteRepo)
        notifyDataSetChanged()
    }
}