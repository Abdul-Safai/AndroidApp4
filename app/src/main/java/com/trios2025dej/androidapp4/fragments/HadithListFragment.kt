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

class HadithListFragment : Fragment() {

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): HadithListFragment {
            return HadithListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
        }
    }

    private lateinit var adapter: HadithAdapter
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString(ARG_CATEGORY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_hadith_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.hadith_recycler_view)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val hadiths = HadithRepository
            .getHadithsForCategory(category ?: "")
            .toMutableList()

        adapter = HadithAdapter(hadiths) { hadith ->
            HadithRepository.toggleFavorite(hadith)
        }

        recycler.adapter = adapter
        return view
    }
}
