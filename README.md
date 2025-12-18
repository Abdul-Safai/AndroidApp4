# AndroidApp4 — Daily Hadith (Home / Categories / Favorites)

## Project Summary
This Android app displays Hadith content in a simple and modern UI.
Users can:
- View a daily random hadith on the Home screen
- Browse hadiths by category
- Star (favorite) hadiths and view them in a Favorites list
- Unstar a hadith to remove it from Favorites immediately

---

## Features Completed
✅ Bottom Navigation with 3 screens:
- Home
- Categories
- Favorites

✅ Hadith list by category (RecyclerView)

✅ Favorite system:
- Tap ⭐ to add/remove favorites
- Favorites page only shows starred hadiths
- Unstarring in Favorites removes the item immediately

✅ Empty Favorites state:
- Shows a message when no favorites exist

✅ Modern UI styling:
- Home screen modern header + card design
- Clean layout spacing and typography

---

## Development Stages / Process (What I Did)

### Stage 1 — Project Setup
- Created a new Android Studio project
- Set up GitHub repository and pushed initial commit
- Organized the project packages:
  - `fragments/`
  - `adapters/`
  - `models/`
  - `utils/`

### Stage 2 — Navigation (App Structure)
- Added BottomNavigationView in `activity_main.xml`
- Created fragments:
  - `HomeFragment`
  - `CategoriesFragment`
  - `FavoritesFragment`
- Connected navigation in `MainActivity.kt`

### Stage 3 — Data Model & Repository
- Created `Hadith` data class to store:
  - id, category, text, source, isFavorite
- Built `HadithRepository` to:
  - return categories
  - return hadiths by category
  - return favorites
  - toggle favorite state

### Stage 4 — RecyclerView Lists
- Created RecyclerView layouts for:
  - categories list
  - hadith list
  - favorites list
- Created adapters:
  - `CategoryAdapter`
  - `HadithAdapter`

### Stage 5 — Favorites Logic (Important Requirement)
- Implemented star toggle:
  - ⭐ Star in Hadith list → appears in Favorites
  - ⭐ Unstar in Favorites → disappears immediately
- Ensured Favorites list shows ONLY items with `isFavorite = true`

### Stage 6 — UI Improvements
- Improved Home screen header and styling
- Improved Hadith card layout
- Added better spacing and a clean modern look

### Stage 7 — Testing & Fixing Bugs
- Verified these required behaviors:
  - Star adds to Favorites
  - Unstar removes from Favorites
  - Navigation works correctly
  - App does not crash
- Fixed missing resources and ids in XML
- Fixed adapter refresh and filtering logic

---

## How To Run
1. Open project in Android Studio
2. Sync Gradle
3. Run on emulator or Android device

---

## Tech Used
- Kotlin
- Fragments
- RecyclerView
- BottomNavigationView
- XML Layouts
- Simple Repository pattern

---

## GitHub Repository
https://github.com/Abdul-Safai/AndroidApp4

