package com.rto.vehicle.numberplate.finder.information.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;
import com.rto.vehicle.numberplate.finder.information.R;
import com.sdk.dierct.link.open.on.fail.sdk.AdClosedListener;
import com.sdk.dierct.link.open.on.fail.sdk.Apps_Preference;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;
import com.squareup.picasso.Picasso;

public class GetStartActivity extends AppCompatActivity {

    private long BackPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);

        findViewById(R.id.now_lets_get_started_txt).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, new AdClosedListener() {
                @Override
                public void AdisClosed() {
                    if (isPermissionGranted()) {
                        nextt();
                    } else {
                        takePermission();
                    }
                }
            });
        });

        if (new Apps_Preference(GetStartActivity.this).getApp_status().equalsIgnoreCase("off")) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder
                            (GetStartActivity.this);
            View view = LayoutInflater.from(GetStartActivity.this).inflate(
                    R.layout.layout_after_app_remove,
                    findViewById(R.id.layoutDialogContainer)
            );
            builder.setView(view);
            int unicode = 0x1F60A;
            ((TextView) view.findViewById(R.id.textMessage))
                    .setText("This application is not availbale more, please click on install to get new application. Thank you! " + getEmojiByUnicode(unicode));
            ((TextView) view.findViewById(R.id.app_title))
                    .setText(new Apps_Preference(GetStartActivity.this).getApp_name());
            Picasso.get().load("http://139.59.69.66/elisionanalytics/" + new Apps_Preference(GetStartActivity.this).getApp_image()).placeholder(R.drawable.bg_default_album_art_empity).into((ImageView) view.findViewById(R.id.image));

            final AlertDialog alertDialog = builder.create();
            view.findViewById(R.id.buttonAction).setOnClickListener(view1 -> {
                alertDialog.dismiss();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(new Apps_Preference(GetStartActivity.this).getApp_link())));
                    finishAffinity();
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(new Apps_Preference(GetStartActivity.this).getApp_link())));
                }
            });
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

        findViewById(R.id.tell_your_friends_txt).setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        });

        findViewById(R.id.give_us_rating_txt).setOnClickListener(v -> {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(GetStartActivity.this, "Unable to Find Market App !", Toast.LENGTH_LONG).show();
            }
        });
        SharedPreferences pref1 = this.getSharedPreferences("com.myapp", 0);
        boolean firstRun = pref1.getBoolean("firstRun", true);
        if (!firstRun) {
            ConsenFormMethod();
        }
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    void ConsenFormMethod() {
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(this);
        consentInformation.requestConsentInfoUpdate(
                this,
                params,
                () -> {
                    // TODO: Load and show the consent form.
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                            this,
                            loadAndShowError -> {
                                if (loadAndShowError != null) {
                                    // Consent gathering failed.
                                    Log.w("consentform", String.format("%s: %s",
                                            loadAndShowError.getErrorCode(),
                                            loadAndShowError.getMessage()));
                                }

                                // Consent has been gathered.
                            }
                    );
                },
                requestConsentError -> {
                    // Consent gathering failed.
                    Log.w("ExtraScreeenactivity", String.format("%s: %s",
                            requestConsentError.getErrorCode(),
                            requestConsentError.getMessage()));
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            finishAffinity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            int postnoti = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
            return postnoti == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void takePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.POST_NOTIFICATIONS}, 102);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0) {

                boolean storage = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (storage) {
                    nextt();
                }
            }
        }
    }

    public void nextt(){
        new InterAds().ShowfuullAd(this, new AdClosedListener() {
            @Override
            public void AdisClosed() {
                startActivity(new Intent(GetStartActivity.this, DashBoardActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (BackPressedTime + 2000 > System.currentTimeMillis()) {
            try {
                finishAffinity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        BackPressedTime = System.currentTimeMillis();
    }
}