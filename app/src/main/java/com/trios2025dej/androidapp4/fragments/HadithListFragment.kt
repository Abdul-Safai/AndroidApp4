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
            val fragment = HadithListFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Make sure this matches the XML file name in res/layout
        val view = inflater.inflate(R.layout.fragment_hadith_list, container, false)

        val category = arguments?.getString(ARG_CATEGORY) ?: ""
        val hadiths = HadithRepository.getHadithsForCategory(category)

        val recyclerView = view.findViewById<RecyclerView>(R.id.hadith_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HadithAdapter(hadiths)

        return view
    }
}
