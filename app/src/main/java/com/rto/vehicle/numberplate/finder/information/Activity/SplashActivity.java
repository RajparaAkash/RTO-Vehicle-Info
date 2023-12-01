package com.rto.vehicle.numberplate.finder.information.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.rto.vehicle.numberplate.finder.information.R;
import com.sdk.dierct.link.open.on.fail.sdk.Apps_Preference;
import com.sdk.dierct.link.open.on.fail.sdk.Check_Screen_Activity;

import org.json.JSONException;

public class SplashActivity extends Activity {

    private FirebaseRemoteConfig firebaseRemoteConfig;
    Apps_Preference pref;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        pref = new Apps_Preference(this);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        fetchRemoteConfigValues();
    }

    private void fetchRemoteConfigValues() {

        firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Fetch successful
                        String jsonString = firebaseRemoteConfig.getString("rto_vehicle_info");
                        Log.e("json response => ", jsonString);
                        try {
                            pref.GetDatafromJson_AndSet(jsonString);
                            NextCall();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Use the jsonString as needed
                    } else {
                        // Fetch failed
                        Exception exception = task.getException();
                        if (exception instanceof FirebaseRemoteConfigFetchThrottledException) {
                            // Fetch throttled, wait before retrying
                            exception.printStackTrace();
                        } else if (exception instanceof FirebaseRemoteConfigException) {
                            // Fetch failed due to an error
                            exception.printStackTrace();
                        }
                    }
                });
    }

    private void NextCall() {
        Apps_Preference preference = new Apps_Preference(SplashActivity.this);
        if (pref.get_Ad_Status().equalsIgnoreCase("on")) {
            Check_Screen_Activity.startAdLoading(SplashActivity.this, preference, Call_Intent());
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(Call_Intent());
                    finish();
                }
            }, 2000);
        }
    }

    private Intent Call_Intent() {
        SharedPreferences pref1 = this.getSharedPreferences("com.myapp", 0);
        SharedPreferences.Editor editor = pref1.edit();
        boolean firstRun = pref1.getBoolean("firstRun", true);
        Intent intent;
        if (firstRun) {
            editor.putBoolean("firstRun", false);
            editor.apply();
            intent = new Intent(SplashActivity.this, ExtaScreen1Activity.class);
        } else {
            intent = new Intent(SplashActivity.this, GetStartActivity.class);
        }
        return intent;
    }

    @Override
    public void onBackPressed() {

    }
}
