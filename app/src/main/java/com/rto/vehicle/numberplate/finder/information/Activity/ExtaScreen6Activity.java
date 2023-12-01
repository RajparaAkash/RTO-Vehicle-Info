package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.R;
import com.sdk.dierct.link.open.on.fail.sdk.AdClosedListener;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

public class ExtaScreen6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exta_screen_6);

        findViewById(R.id.extra_screen_6_txt).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, new AdClosedListener() {
                @Override
                public void AdisClosed() {
                    startActivity(new Intent(ExtaScreen6Activity.this, GetStartActivity.class));
                    finish();
                }
            });
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}