package com.leolai.earthquake.preference;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import com.leolai.earthquake.R;

import java.sql.Array;
import java.util.List;
import java.util.prefs.PreferenceChangeListener;


/**
 * Created by apple on 15/10/19.
 */
public class UserPreferences extends PreferenceActivity implements Preference.OnPreferenceChangeListener{


    public final static String PREF_AUTO_UPDATE = "PREF_AUTO_UPDATE";
    public final static String PREF_MINI_MAG = "PREF_QUAKE_MINIMAG";
    public final static String PREF_UPDATE_FREQ = "PREF_UPDATE_FREQ";

    private ListPreference mFreqPre;
    private ListPreference mMiniMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);
        mFreqPre = (ListPreference)getPreferenceScreen().findPreference(PREF_UPDATE_FREQ);
        mMiniMag = (ListPreference)getPreferenceScreen().findPreference(PREF_MINI_MAG);
        mFreqPre.setSummary(mFreqPre.getEntry());
        mMiniMag.setSummary(mMiniMag.getEntry());

        mFreqPre.setOnPreferenceChangeListener(this);
        mMiniMag.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        /*switch (preference.getKey()) {
            case PREF_MINI_MAG:
                String[] array = getApplicationContext().getResources().getStringArray(R.array.mini_magnitude);
                mMiniMag.setSummary(mMiniMag.getEntry());
                break;
            case PREF_UPDATE_FREQ:
                mFreqPre.setSummary(mFreqPre.getEntry());
                break;
            default:break;
        }*/
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case PREF_MINI_MAG:
                //String[] array = getApplicationContext().getResources().getStringArray(R.array.mini_magnitude);
                mMiniMag.setValue(newValue.toString());
                mMiniMag.setSummary(mMiniMag.getEntry());
                break;
            case PREF_UPDATE_FREQ:
                mFreqPre.setValue(newValue.toString());
                mFreqPre.setSummary(mFreqPre.getEntry());
                break;
            default:break;
        }
        return false;
    }
}
