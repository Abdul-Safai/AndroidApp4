package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.adapters.HadithAdapter
import com.trios2025dej.androidapp4.utils.HadithRepository

class FavoritesFragment : Fragment() {

    private lateinit var adapter: HadithAdapter
    private lateinit var emptyText: TextView
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // ✅ IMPORTANT: use fragment_favorites (NOT fragment_hadith_list)
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        emptyText = view.findViewById(R.id.favorites_empty_text)
        recycler = view.findViewById(R.id.favorites_recycler_view)

        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter = HadithAdapter(
            HadithRepository.getFavoriteHadiths().toMutableList()
        ) { hadith ->
            // Unfavorite
            HadithRepository.toggleFavorite(hadith)

            // Refresh list so it disappears immediately
            refreshList()
        }

        recycler.adapter = adapter
        refreshList()

        return view
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        val favorites = HadithRepository.getFavoriteHadiths()

        // show/hide empty message
        emptyText.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
        recycler.visibility = if (favorites.isEmpty()) View.GONE else View.VISIBLE

        adapter.refresh(favorites)
    }
}
