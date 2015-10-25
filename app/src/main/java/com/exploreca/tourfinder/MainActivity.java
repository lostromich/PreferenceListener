package com.exploreca.tourfinder;

import com.exploreca.tourfinder.utils.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	public static final String LOGTAG="EXPLORECA";
	public static final String USERNAME="pref_username";
	public static final String VIEWIMAGE="pref_viewimages";
	
	private SharedPreferences settings;
	// Declare class field for listener
	private SharedPreferences.OnSharedPreferenceChangeListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		// Initialize Listener
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				// Add action to react to change settings
				MainActivity.this.refreshDisplay(null); // take into account any preferences that may changed
			}
		};
		// Register preferences object with listener
		settings.registerOnSharedPreferenceChangeListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void setPreference(View v) {
		Log.i(LOGTAG, "Clicked set");
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void refreshDisplay(View v) {
		Log.i(LOGTAG, "Clicked show");
		
		String prefValue = settings.getString(USERNAME, "Not found");
		UIHelper.displayText(this, R.id.textView1, prefValue);
		UIHelper.setCBChecked(this, R.id.checkBox1, settings.getBoolean(VIEWIMAGE, false));
	}
	
}
