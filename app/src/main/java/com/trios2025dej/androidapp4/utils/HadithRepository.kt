package com.trios2025dej.androidapp4.utils

import com.trios2025dej.androidapp4.models.Hadith

object HadithRepository {

    // All hadiths in the app
    private val allHadiths = listOf(
        Hadith(
            id = 1,
            category = "Faith",
            text = "Actions are judged by intentions.",
            source = "Sahih al-Bukhari"
        ),
        Hadith(
            id = 2,
            category = "Faith",
            text = "None of you truly believes until he loves for his brother what he loves for himself.",
            source = "Sahih al-Bukhari & Muslim"
        ),
        Hadith(
            id = 3,
            category = "Manners",
            text = "The best of you are those who have the best manners and character.",
            source = "Sahih al-Bukhari"
        ),
        Hadith(
            id = 4,
            category = "Knowledge",
            text = "Seeking knowledge is an obligation upon every Muslim.",
            source = "Sunan Ibn Majah"
        )
        // Add more hadiths here...
    )

    /**
     * Returns ALL categories (unique values)
     */
    fun getCategories(): List<String> {
        return allHadiths.map { it.category }.distinct()
    }

    /**
     * Returns all hadiths for a given category
     */
    fun getHadithsForCategory(category: String): List<Hadith> {
        return allHadiths.filter { it.category.equals(category, ignoreCase = true) }
    }

    /**
     * Returns a random hadith (for HomeFragment)
     */
    fun getRandomHadith(): Hadith? {
        return if (allHadiths.isNotEmpty()) allHadiths.random() else null
    }
}
