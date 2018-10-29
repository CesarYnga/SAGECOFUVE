package com.grupotransmares.sagecofuve.settings;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.preference.DialogPreference;
import android.util.AttributeSet;

import com.grupotransmares.sagecofuve.R;

public class NumberPickerPreferenceJava extends DialogPreference {

    public static final int DEFAULT_VALUE = 5;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 20;

    private int value;
    private int min;
    private int max;
    private String label;

    public NumberPickerPreferenceJava(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public NumberPickerPreferenceJava(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public NumberPickerPreferenceJava(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NumberPickerPreferenceJava(Context context) {
        super(context);
    }

    private void init(AttributeSet attrs) {
        setDialogLayoutResource(R.layout.dialog_number_picker);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        int[] defaultValueAttr = new int[]{android.R.attr.defaultValue};
        TypedArray dialogType = getContext().obtainStyledAttributes(attrs, defaultValueAttr);
        TypedArray numberPickerType = getContext().obtainStyledAttributes(attrs,
                R.styleable.NumberPickerPreference);

        value = dialogType.getInt(0, DEFAULT_VALUE);
        min = numberPickerType.getInt(R.styleable.NumberPickerPreference_min, MIN_VALUE);
        max = numberPickerType.getInt(R.styleable.NumberPickerPreference_max, MAX_VALUE);
        label = numberPickerType.getString(R.styleable.NumberPickerPreference_label);

        dialogType.recycle();
        numberPickerType.recycle();


    }

    @Override
    public CharSequence getSummary() {
        return value + " " + label;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, DEFAULT_VALUE);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            // Restore existing state
            value = this.getPersistedInt(DEFAULT_VALUE);
        } else {
            // Set default state from the XML attribute
            value = (int) defaultValue;
            persistInt(value);
        }
    }

    public void saveValue(int value) {
        this.value = value;
        persistInt(value);
    }

    public int getValue() {
        return value;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getLabel() {
        return label;
    }
}
