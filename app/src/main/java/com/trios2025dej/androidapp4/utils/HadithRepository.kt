package com.trios2025dej.androidapp4.utils

import android.content.Context
import android.content.SharedPreferences
import com.trios2025dej.androidapp4.models.Hadith

object HadithRepository {

    private const val PREFS_NAME = "hadith_prefs"
    private const val KEY_FAVORITES = "favorite_ids"

    private lateinit var prefs: SharedPreferences
    private var isReady = false

    // ✅ ONE master list (single source of truth)
    private val allHadiths: MutableList<Hadith> = mutableListOf()

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        isReady = true

        // Load data only once
        if (allHadiths.isEmpty()) {
            seedData()
        }

        // Apply saved favorites to master list
        applyFavoritesFromPrefs()
    }

    private fun ensureReady() {
        if (!isReady) {
            throw IllegalStateException("HadithRepository not initialized. Call HadithRepository.init(context) in Application.")
        }
    }

    // ✅ Example seed data (KEEP your real hadiths here)
    private fun seedData() {
        // IMPORTANT: Replace this with your existing hadith dataset if you already have one.
        // Keep IDs unique.
        allHadiths.addAll(
            listOf(
                Hadith(1, "Faith", "Actions are judged by intentions.", "Bukhari"),
                Hadith(2, "Prayer", "Pray as you have seen me praying.", "Bukhari"),
                Hadith(3, "Character", "The best of you are those with best manners.", "Tirmidhi"),
                Hadith(4, "Knowledge", "Seeking knowledge is obligatory.", "Ibn Majah"),
            )
        )
    }

    private fun applyFavoritesFromPrefs() {
        ensureReady()

        val favSet = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        val favIds = favSet.mapNotNull { it.toIntOrNull() }.toSet()

        for (h in allHadiths) {
            h.isFavorite = favIds.contains(h.id)
        }
    }

    private fun saveFavoritesToPrefs() {
        ensureReady()

        val favIds = allHadiths
            .filter { it.isFavorite }
            .map { it.id.toString() }
            .toSet()

        prefs.edit().putStringSet(KEY_FAVORITES, favIds).apply()
    }

    fun getCategories(): List<String> {
        ensureReady()
        return allHadiths.map { it.category }.distinct().sorted()
    }

    fun getHadithsForCategory(category: String): List<Hadith> {
        ensureReady()
        return allHadiths.filter { it.category.equals(category, ignoreCase = true) }
    }

    fun getFavoriteHadiths(): List<Hadith> {
        ensureReady()
        return allHadiths.filter { it.isFavorite }
    }

    fun getRandomHadith(): Hadith? {
        ensureReady()
        if (allHadiths.isEmpty()) return null
        return allHadiths.random()
    }

    fun toggleFavorite(hadith: Hadith) {
        ensureReady()

        // Update master list item by ID (not a copy)
        val master = allHadiths.find { it.id == hadith.id }
        if (master != null) {
            master.isFavorite = !master.isFavorite
            saveFavoritesToPrefs()
        }
    }
}
