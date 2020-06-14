package com.example.watchwithb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class TVShowsAdapter (
    private var tvshows: MutableList<TVShow>,
    private val onTVShowClick: (tvshow: TVShow) -> Unit
) : RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder{
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tvshow, parent, false)
        return TVShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvshows.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(tvshows[position])
    }

    fun appendTVShows(tvshows: List<TVShow>){
        this.tvshows.addAll(tvshows)
        notifyItemRangeInserted(
            this.tvshows.size,
            tvshows.size - 1
        )
    }

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_tvshow_poster)
        fun bind(tvshow: TVShow){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvshow.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            itemView.setOnClickListener { onTVShowClick.invoke(tvshow) }
        }
    }
}