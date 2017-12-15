package com.grupotransmares.sagecofuve.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.grupotransmares.sagecofuve.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_location)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        if (preference is NumberPickerPreferenceJava) {
            val f = NumberPickerDialogFragment.newInstance(preference.key)
            f.setTargetFragment(this, 0)
            f.show(fragmentManager, "NumberPickerPrefDialog")
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val preference = findPreference(key)
        if (preference is NumberPickerPreferenceJava) {
            preference.summary = sharedPreferences?.getInt(key, NumberPickerPreferenceJava.DEFAULT_VALUE).toString()
        }
    }
}