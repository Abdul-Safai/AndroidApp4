package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.models.Hadith
import com.trios2025dej.androidapp4.utils.HadithRepository

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val hadithTextView = view.findViewById<TextView>(R.id.daily_hadith_text)

        val hadith: Hadith? = HadithRepository.getRandomHadith()

        if (hadith != null) {
            hadithTextView.text = "\"${hadith.text}\"\n\n- ${hadith.source}"
        } else {
            hadithTextView.text = "No hadith available."
        }

        return view
    }
}
