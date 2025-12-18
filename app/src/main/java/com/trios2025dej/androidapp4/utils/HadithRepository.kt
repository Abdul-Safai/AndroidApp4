package com.trios2025dej.androidapp4.utils

import com.trios2025dej.androidapp4.models.Hadith

object HadithRepository {

    private val hadiths = mutableListOf(
        Hadith(1, "Faith", "Actions are judged by intentions.", "Bukhari"),
        Hadith(2, "Faith", "Islam is built on five pillars.", "Muslim"),
        Hadith(3, "Patience", "Patience is light.", "Muslim"),
        Hadith(4, "Knowledge", "Seeking knowledge is obligatory.", "Ibn Majah")
    )

    fun getCategories(): List<String> {
        return hadiths.map { it.category }.distinct()
    }

    fun getHadithsForCategory(category: String): List<Hadith> {
        return hadiths.filter { it.category == category }
    }

    fun getFavoriteHadiths(): List<Hadith> {
        return hadiths.filter { it.isFavorite }
    }

    fun toggleFavorite(hadith: Hadith) {
        hadith.isFavorite = !hadith.isFavorite
    }

    fun getRandomHadith(): Hadith? {
        return hadiths.randomOrNull()
    }
}
