package com.trios2025dej.androidapp4.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.models.Hadith

class HadithAdapter(
    private var items: List<Hadith>
) : RecyclerView.Adapter<HadithAdapter.HadithViewHolder>() {

    inner class HadithViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hadithText: TextView = itemView.findViewById(R.id.hadith_text)
        val hadithSource: TextView = itemView.findViewById(R.id.hadith_source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hadith, parent, false)
        return HadithViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        val hadith = items[position]
        holder.hadithText.text = hadith.text
        holder.hadithSource.text = hadith.source
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<Hadith>) {
        items = newItems
        notifyDataSetChanged()
    }
}
