package com.leolai.earthquake.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.leolai.earthquake.R;

import java.util.List;

/**
 * Created by apple on 15/11/5.
 */
public class FragementPreference extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.settings_header, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {

        return true;//super.isValidFragment(fragmentName);
    }
}
