package com.trios2025dej.androidapp4.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.models.Hadith

class HadithAdapter(
    private val hadiths: MutableList<Hadith>,
    private val onFavoriteClick: (Hadith) -> Unit
) : RecyclerView.Adapter<HadithAdapter.HadithViewHolder>() {

    inner class HadithViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hadithText: TextView = itemView.findViewById(R.id.hadith_text)
        val hadithSource: TextView = itemView.findViewById(R.id.hadith_source)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.button_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hadith, parent, false)
        return HadithViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        val hadith = hadiths[position]

        holder.hadithText.text = hadith.text
        holder.hadithSource.text = "- ${hadith.source}"

        fun setStarIcon() {
            holder.favoriteButton.setImageResource(
                if (hadith.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )
        }

        setStarIcon()

        holder.favoriteButton.setOnClickListener {
            // toggle via repository / callback
            val before = hadith.isFavorite
            onFavoriteClick(hadith)
            val after = hadith.isFavorite

            // animate star
            holder.favoriteButton.animate()
                .scaleX(1.25f).scaleY(1.25f)
                .setDuration(120)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    holder.favoriteButton.animate()
                        .scaleX(1f).scaleY(1f)
                        .setDuration(120)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .start()
                }
                .start()

            setStarIcon()

            // snackbar
            val msg = if (!before && after) "Added to favorites"
            else if (before && !after) "Removed from favorites"
            else "Updated"
            Snackbar.make(holder.itemView, msg, Snackbar.LENGTH_SHORT).show()

            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = hadiths.size

    fun refresh(newHadiths: List<Hadith>) {
        hadiths.clear()
        hadiths.addAll(newHadiths)
        notifyDataSetChanged()
    }
}
