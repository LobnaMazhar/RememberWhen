//package com.example.lobna.rememberwhen.Fragments;
//
///**
// * Created by Lobna on 09-Jan-17.
// */
//
//import android.annotation.TargetApi;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.preference.PreferenceFragment;
//import android.view.MenuItem;
//
//import com.example.lobna.rememberwhen.Activity.SettingsActivity;
//import com.example.lobna.rememberwhen.R;
//
///**
// * This fragment shows notification preferences only. It is used when the
// * activity is showing a two-pane settings UI.
// */
//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//public class NotificationPreferenceFragment extends PreferenceFragment {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.pref_notification);
//        setHasOptionsMenu(true);
//
//        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//        // to their values. When their values change, their summaries are
//        // updated to reflect the new value, per the Android Design
//        // guidelines.
//        SettingsActivity.bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            startActivity(new Intent(getActivity(), SettingsActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}