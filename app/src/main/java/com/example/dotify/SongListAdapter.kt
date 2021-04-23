package com.example.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListener: (song: Song) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]

        // Setting each item view
        with(holder.binding) {
            itemRoot.setOnClickListener {
                onSongClickListener(song)
            }
            tvTitle.text = song.title
            tvArtist.text = song.artist
            ivSongCover.setImageResource(song.smallImageID)
        }
    }

    fun updateSongs(newList: List<Song>) {
        this.listOfSongs = newList
        notifyDataSetChanged()
    }
    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)

}