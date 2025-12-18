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
        val view = inflater.inflate(R.layout.fragment_hadith_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.hadith_recycler_view)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter = HadithAdapter(
            HadithRepository.getFavoriteHadiths().toMutableList()
        ) { hadith ->
            HadithRepository.toggleFavorite(hadith)
            adapter.refresh(HadithRepository.getFavoriteHadiths())
        }

        recycler.adapter = adapter
        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh(HadithRepository.getFavoriteHadiths())
    }
}
