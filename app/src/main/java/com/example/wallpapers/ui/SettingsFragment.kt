package com.example.wallpapers.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentListCategoriesBinding

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val preference = findPreference<Preference>("theme_preference")
        preference?.setOnPreferenceClickListener {
            showThemeSelectionDialog()
            true
        }
    }

    private fun showThemeSelectionDialog() {
        val themeValues = arrayOf("light", "dark")
        val currentThemeValue = PreferenceManager.getDefaultSharedPreferences(requireActivity())
            .getString("theme_preference", "light")

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Выберите тему")
            .setSingleChoiceItems(
                themeValues,
                themeValues.indexOf(currentThemeValue)
            ) { dialog, which ->
                val selectedTheme = themeValues[which]
                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
                sharedPreferences.edit().putString("theme_preference", selectedTheme).apply()
                dialog.dismiss()
                (requireActivity() as AppActivity).updateTheme()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}