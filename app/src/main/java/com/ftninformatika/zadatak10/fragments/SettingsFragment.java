package com.ftninformatika.zadatak10.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.ftninformatika.zadatak10.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}