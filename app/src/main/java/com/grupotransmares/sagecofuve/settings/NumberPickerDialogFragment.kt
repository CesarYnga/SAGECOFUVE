package com.grupotransmares.sagecofuve.settings

import android.os.Bundle
import android.support.v7.preference.PreferenceDialogFragmentCompat
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import com.grupotransmares.sagecofuve.R

class NumberPickerDialogFragment : PreferenceDialogFragmentCompat() {

    var numberPicker : NumberPicker? = null
    var label : TextView? = null

    companion object {
        fun newInstance(key: String): NumberPickerDialogFragment {
            val fragment = NumberPickerDialogFragment()
            val b = Bundle()
            b.putString(PreferenceDialogFragmentCompat.ARG_KEY, key)
            fragment.arguments = b
            return fragment
        }
    }

    override fun onBindDialogView(view: View?) {
        super.onBindDialogView(view)
        numberPicker = view?.findViewById(R.id.numberPicker)
        label = view?.findViewById(R.id.txtUnit)

        if (preference is NumberPickerPreferenceJava) {
            val numberPickerPref = preference as NumberPickerPreferenceJava
            numberPicker?.minValue = numberPickerPref.min
            numberPicker?.maxValue = numberPickerPref.max
            numberPicker?.value = numberPickerPref.value
            label?.text = numberPickerPref.label
        }
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            numberPicker?.value?.let {
                (preference as NumberPickerPreferenceJava).saveValue(it)
                preference.callChangeListener(it)
            }
        }
    }

}