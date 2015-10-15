package com.leolai.earthquake;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class PreferenceActivity extends Activity implements View.OnClickListener {

    public static final String PREF_AUTO_UPDATE = "pref_auto_update";
    public static final String PREF_UPDATE_FREQ = "pref_update_freq";
    public static final String PREF_MINI_MAG = "pref_mini_mag";

    SharedPreferences pref;

    CheckBox autoUpdate;
    Spinner updateFreq;
    Spinner magSpinner;

    Button okButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        autoUpdate = (CheckBox) findViewById(R.id.checkbox_auto_update);
        updateFreq = (Spinner) findViewById(R.id.spinner_freq);
        magSpinner = (Spinner) findViewById(R.id.spinner_quake_mag);

        pupulateSpinners();

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        updateUiPreferences();
    }

    private void updateUiPreferences() {
        boolean updated = pref.getBoolean(PREF_AUTO_UPDATE, false);
        int updateGap = pref.getInt(PREF_UPDATE_FREQ, 1);
        int miniMag = pref.getInt(PREF_MINI_MAG, 0);

        autoUpdate.setChecked(updated);
        updateFreq.setSelection(updateGap);
        magSpinner.setSelection(miniMag);

        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    private void pupulateSpinners() {
        //fill updateFreq spinner
        ArrayAdapter<CharSequence> freqAdapter;
        freqAdapter = ArrayAdapter.createFromResource(this, R.array.update_freq_options, android.R.layout.simple_spinner_item);
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateFreq.setAdapter(freqAdapter);

        //fill magSpinner
        ArrayAdapter<CharSequence> magAdapter;
        magAdapter = ArrayAdapter.createFromResource(this, R.array.mini_magnitude, android.R.layout.simple_spinner_item);
        magAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        magSpinner.setAdapter(magAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.prefereces_menu, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.okButton:
                savePreferences();
                PreferenceActivity.this.setResult(RESULT_OK);
                finish();
                break;
            case R.id.cancelButton:
                savePreferences();
                PreferenceActivity.this.setResult(RESULT_CANCELED);
                finish();
                break;
            default:
                break;
        }
    }

    private void savePreferences() {
        boolean updated = autoUpdate.isChecked();
        int updatefreqIndex = updateFreq.getSelectedItemPosition();
        int miniIndex = magSpinner.getSelectedItemPosition();

        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(PREF_AUTO_UPDATE, updated);
        edit.putInt(PREF_UPDATE_FREQ, updatefreqIndex);
        edit.putInt(PREF_MINI_MAG, miniIndex);
        edit.commit();
    }
}
