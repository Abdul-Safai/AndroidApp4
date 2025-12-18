package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.adapters.HadithAdapter
import com.trios2025dej.androidapp4.utils.HadithRepository

class FavoritesFragment : Fragment() {

    private lateinit var adapter: HadithAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Use your favorites layout if you have it, otherwise keep fragment_hadith_list
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.favorites_recycler_view)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter = HadithAdapter(HadithRepository.getFavoriteHadiths().toMutableList()) { hadith ->
            HadithRepository.toggleFavorite(hadith)
            adapter.updateData(HadithRepository.getFavoriteHadiths()) // refresh list
            updateEmptyState(view)
        }

        recycler.adapter = adapter
        updateEmptyState(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(HadithRepository.getFavoriteHadiths())
        view?.let { updateEmptyState(it) }
    }

    private fun updateEmptyState(root: View) {
        val emptyText = root.findViewById<View>(R.id.favorites_empty_text)
        val recycler = root.findViewById<View>(R.id.favorites_recycler_view)

        val hasFavorites = HadithRepository.getFavoriteHadiths().isNotEmpty()
        emptyText.visibility = if (hasFavorites) View.GONE else View.VISIBLE
        recycler.visibility = if (hasFavorites) View.VISIBLE else View.GONE
    }
}
