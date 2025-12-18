package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.trios2025dej.androidapp4.R
import com.trios2025dej.androidapp4.models.Hadith
import com.trios2025dej.androidapp4.utils.HadithRepository

class HomeFragment : Fragment() {

    private var hadith: Hadith? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val text = view.findViewById<TextView>(R.id.daily_hadith_text)
        val star = view.findViewById<ImageButton>(R.id.button_favorite_home)

        hadith = HadithRepository.getRandomHadith()

        hadith?.let { h ->
            text.text = "\"${h.text}\"\n\n- ${h.source}"

            fun refresh() {
                star.setImageResource(
                    if (h.isFavorite)
                        android.R.drawable.btn_star_big_on
                    else
                        android.R.drawable.btn_star_big_off
                )
            }

            refresh()

            star.setOnClickListener {
                HadithRepository.toggleFavorite(h)
                refresh()
            }
        }

        return view
    }
}
