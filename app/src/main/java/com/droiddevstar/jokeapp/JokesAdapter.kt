package com.droiddevstar.jokeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class JokesAdapter(private val list: List<String>) : RecyclerView.Adapter<JokeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): JokeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_joke, parent, false)
        return JokeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokeViewHolder,
                                  position: Int) {
        holder.tvJokeText.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvJokeText: AppCompatTextView = itemView
        .findViewById(R.id.tv_joke_text)
}
