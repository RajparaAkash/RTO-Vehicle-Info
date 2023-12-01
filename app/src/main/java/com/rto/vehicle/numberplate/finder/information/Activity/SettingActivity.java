package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.BuildConfig;
import com.rto.vehicle.numberplate.finder.information.R;
import com.sdk.dierct.link.open.on.fail.sdk.Con_stant;
import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.back_img).setOnClickListener(v -> onBackPressed());

        ((TextView) findViewById(R.id.setting_version_text)).setText("V : " + BuildConfig.VERSION_NAME);

        findViewById(R.id.setting_1_rate).setOnClickListener(v -> {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(SettingActivity.this, "Unable to Find Market App !", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.setting_2_share).setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        });

        findViewById(R.id.setting_3_privacy).setOnClickListener(v -> {
            Con_stant.Privacy_App(SettingActivity.this);
        });

        findViewById(R.id.setting_4_info).setOnClickListener(v -> {
            startActivity(new Intent(SettingActivity.this, InfoActivity.class));
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}