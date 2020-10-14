package com.droiddevstar.jokeapp

import androidx.recyclerview.widget.DiffUtil

class JokeDiffUtilCallback(private val oldList: List<String>,
                           private val newList: List<String>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int,
                                 newItemPosition: Int): Boolean {
        val oldJoke = oldList[oldItemPosition]
        val newJoke = newList[newItemPosition]
        return oldJoke == newJoke
    }

    override fun areContentsTheSame(oldItemPosition: Int,
                                    newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}