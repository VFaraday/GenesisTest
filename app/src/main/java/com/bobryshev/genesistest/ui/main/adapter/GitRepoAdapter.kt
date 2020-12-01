package com.bobryshev.genesistest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobryshev.genesistest.R
import com.bobryshev.genesistest.data.repository.domain.GitRepo
import com.bobryshev.genesistest.databinding.ItemGitRepoBinding

class GitRepoAdapter(private val context: Context, val addToFavorite: (gitRepo: GitRepo) -> Unit, val removeFromFavorite: (gitRepo: GitRepo) -> Unit):
    RecyclerView.Adapter<GitRepoViewHolder>() {

    private val favoriteRepo: MutableList<GitRepo> = mutableListOf()
    private val gitRepoList: MutableList<GitRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder = GitRepoViewHolder(
            ItemGitRepoBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        val repo = gitRepoList[holder.absoluteAdapterPosition]
        val layout = holder.layout
        layout.tvName.text = repo.name
        layout.tvDescription.text = repo.description
        layout.ivFavorite.setImageResource(
            if (favoriteRepo.contains(repo)) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_favorite_border_24
            })
        layout.ivFavorite.setOnClickListener {
            if (favoriteRepo.contains(repo)) {
                removeFromFavorite(repo)
                favoriteRepo.remove(repo)
            } else {
                addToFavorite(repo)
                favoriteRepo.add(repo)
            }
            notifyItemChanged(holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int = gitRepoList.size

    fun notifyData(gitRepoList: List<GitRepo>) {
        this.gitRepoList.clear()
        this.gitRepoList.addAll(gitRepoList)
        notifyDataSetChanged()
    }

    fun setFavorite(favoriteRepo: List<GitRepo>) {
        this.favoriteRepo.clear()
        this.favoriteRepo.addAll(favoriteRepo)
    }
}