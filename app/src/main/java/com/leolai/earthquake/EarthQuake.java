package com.leolai.earthquake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class EarthQuake extends Activity {
    private static final int SHOW_PREFERENCE = 1;

    private boolean updatedSelf = false;
    //public int minimunMag = 0;
    private int updateFreq = 0;
    private EarthQuakeListFragement mQuakeListFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);

        mQuakeListFrag = (EarthQuakeListFragement) getFragmentManager().findFragmentById(R.id.earthquke_list_fragment_id);
        updateFromPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_earth_quake, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, PreferenceActivity.class);
            startActivityForResult(i, SHOW_PREFERENCE);
            return true;
        } else if (id == R.id.menu_fresh) {
            refreshQuake();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHOW_PREFERENCE) {
            if (resultCode == RESULT_OK) {
                updateFromPreferences();
                refreshQuake();
            }
        }
    }

    private void updateFromPreferences() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Resources r = getResources();
        String[] freqArray = r.getStringArray(R.array.update_freq_values);
        String[] miniMagArray = r.getStringArray(R.array.mini_magnitude_values);

        updatedSelf = pref.getBoolean(PreferenceActivity.PREF_AUTO_UPDATE, false);

        int index = pref.getInt(PreferenceActivity.PREF_UPDATE_FREQ, 0);
        if (index < 0) {
            index = 0;
        }
        updateFreq = Integer.valueOf(freqArray[index]);

        index = pref.getInt(PreferenceActivity.PREF_MINI_MAG, 0);
        if (index < 0) {
            index = 0;
        }
        int minimunMag = Integer.valueOf(miniMagArray[index]);
        mQuakeListFrag.setMinimumMagnitude(minimunMag);
    }

    private void refreshQuake() {

        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                mQuakeListFrag.clearAllQuakes();
                mQuakeListFrag.refleshEarthQuakes();
            }
        });
        T.start();
    }
}
