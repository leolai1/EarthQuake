package com.leolai.earthquake.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.leolai.earthquake.R;

/**
 * Created by apple on 15/11/4.
 */
public class UerPreferenceFragement extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);
    }
}
