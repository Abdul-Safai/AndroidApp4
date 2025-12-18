package com.trios2025dej.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
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

        val hadithText = view.findViewById<TextView>(R.id.daily_hadith_text)
        val hadithSource = view.findViewById<TextView>(R.id.daily_hadith_source)
        val star = view.findViewById<ImageButton>(R.id.button_favorite_home)

        hadith = HadithRepository.getRandomHadith()

        val h = hadith
        if (h == null) {
            hadithText.text = "No hadith available."
            hadithSource.text = ""
            star.visibility = View.GONE
            return view
        }

        hadithText.text = "\"${h.text}\""
        hadithSource.text = "- ${h.source}"

        fun refreshStar() {
            star.setImageResource(
                if (h.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )
        }

        refreshStar()

        star.setOnClickListener {
            val before = h.isFavorite
            HadithRepository.toggleFavorite(h)
            val after = h.isFavorite

            // animation
            star.animate()
                .scaleX(1.25f).scaleY(1.25f)
                .setDuration(120)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    star.animate()
                        .scaleX(1f).scaleY(1f)
                        .setDuration(120)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .start()
                }
                .start()

            refreshStar()

            val msg = if (!before && after) "Added to favorites"
            else "Removed from favorites"
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
        }

        return view
    }
}
