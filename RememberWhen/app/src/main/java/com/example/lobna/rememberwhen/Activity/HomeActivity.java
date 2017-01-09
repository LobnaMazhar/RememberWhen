package com.example.lobna.rememberwhen.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lobna.rememberwhen.R;
import com.example.lobna.rememberwhen.Menu.*;
import com.example.lobna.rememberwhen.Utilities.Utility;

public class HomeActivity extends Menu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean firstTime = true;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        firstTime = settings.getBoolean("FIRST_RUN", true);
        if (firstTime) { // if first time to open the app upon installation ,, turn on notifications system ,, why ?? msh naf3 3'er ama yft7 settingsActivity aw yndh Notifications utility :/
            // do the thing for the first time
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", false);
            editor.commit();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String getNotifications = sharedPreferences.getString(getString(R.string.pref_title_receive_notifications), "true");
            if (getNotifications.equals("true")) {
                Utility.Notification(true);
            } else if (getNotifications.equals("false")) {
                Utility.Notification(false);
            }
        }
    }

    public void addMemory(View view) {
        startActivity(new Intent(HomeActivity.this, AddMemoryActivity.class));
    }
}
