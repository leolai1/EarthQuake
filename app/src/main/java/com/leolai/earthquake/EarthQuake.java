package com.leolai.earthquake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.leolai.earthquake.preference.FragementPreference;
import com.leolai.earthquake.preference.UserPreferences;

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
        //updateFromPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            Class target = Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                    ? UserPreferences.class : FragementPreference.class;
            Intent i = new Intent(this, target);
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
            //if (resultCode == RESULT_OK) {
                updateFromPreferences();
                refreshQuake();
            //}
        }
    }

    private void updateFromPreferences() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Resources r = getResources();

        updatedSelf = pref.getBoolean(UserPreferences.PREF_AUTO_UPDATE, false);
        updateFreq = Integer.parseInt(pref.getString(UserPreferences.PREF_UPDATE_FREQ, "5"));
        int minimunMag = Integer.parseInt(pref.getString(UserPreferences.PREF_MINI_MAG, "2"));
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
