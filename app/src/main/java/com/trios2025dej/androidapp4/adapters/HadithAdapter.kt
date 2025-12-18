package com.trios2025dej.androidapp4.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.models.Hadith

class HadithAdapter(
    private val hadiths: MutableList<Hadith>,
    private val onFavoriteClick: (Hadith) -> Unit
) : RecyclerView.Adapter<HadithAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.hadith_text)
        val source: TextView = view.findViewById(R.id.hadith_source)
        val star: ImageButton = view.findViewById(R.id.button_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hadith, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hadith = hadiths[position]

        holder.text.text = hadith.text
        holder.source.text = "- ${hadith.source}"

        holder.star.setImageResource(
            if (hadith.isFavorite)
                android.R.drawable.btn_star_big_on
            else
                android.R.drawable.btn_star_big_off
        )

        holder.star.setOnClickListener {
            onFavoriteClick(hadith)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = hadiths.size

    fun refresh(newList: List<Hadith>) {
        hadiths.clear()
        hadiths.addAll(newList)
        notifyDataSetChanged()
    }
}
