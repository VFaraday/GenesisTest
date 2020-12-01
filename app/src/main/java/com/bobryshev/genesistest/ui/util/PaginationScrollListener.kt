package com.bobryshev.genesistest.ui.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {

    abstract fun onReachedTheEnd()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (recyclerView.adapter?.itemCount == 0) {
            return
        }
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastPosition = layoutManager.findLastVisibleItemPosition()
        if (lastPosition == recyclerView.adapter!!.itemCount - 1) {
            onReachedTheEnd()
        }
    }
}