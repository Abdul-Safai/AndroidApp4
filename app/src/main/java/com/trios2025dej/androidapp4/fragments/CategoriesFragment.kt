package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.adapters.CategoryAdapter
import com.trios2025dej.androidapp4.utils.HadithRepository

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val categories = HadithRepository.getCategories()

        recyclerView.adapter = CategoryAdapter(categories) { category ->
            val fragment = com.trios2025dej.androidapp4.fragments.HadithListFragment.newInstance(category)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
