package com.rto.vehicle.numberplate.finder.information.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rto.vehicle.numberplate.finder.information.R;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;


public class DLErrorActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dl_error);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        String string = getIntent().getExtras().getString("DLNO");
        ((TextView) findViewById(R.id.title_txt)).setText(string);

        findViewById(R.id.back_img).setOnClickListener(view -> onBackPressed());

        findViewById(R.id.search_again_txt).setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
