package com.rto.vehicle.numberplate.finder.information.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.sdk.dierct.link.open.on.fail.sdk.AppOpenManager;
import com.sdk.dierct.link.open.on.fail.sdk.Con_stant;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;

public class ExtaScreen1Activity extends AppCompatActivity {

    private long BackPressedTime = 0;
    private ConsentInformation consentInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exta_screen_1);

        Dialog policyDialog = new Dialog(this);
        policyDialog.requestWindowFeature(1);
        policyDialog.setContentView(R.layout.dialog_privacy_policy);
        policyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        policyDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        policyDialog.setCancelable(false);
        policyDialog.show();

        TextView ok_text = policyDialog.findViewById(R.id.ok_text);
        TextView text_1 = policyDialog.findViewById(R.id.text_1);
        /*TextView policy_text = policyDialog.findViewById(R.id.policy_text);
        policy_text.setText(R.string.policy);*/

        String privacyPolicyText = "By continuing, you agree to our Privacy Policy.";

        // Create a ClickableSpan to handle the click event
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                AppOpenManager.isSplash = true;
                Con_stant.Privacy_App(ExtaScreen1Activity.this);
            }
        };

        // Create a SpannableStringBuilder to apply multiple spans
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(privacyPolicyText);

        // Add UnderlineSpan to underline the privacy policy text
        spannableStringBuilder.setSpan(new UnderlineSpan(), 32, privacyPolicyText.length(), 0);

        // Add ForegroundColorSpan to change text color
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), 32, privacyPolicyText.length(), 0);

        // Set the ClickableSpan for the entire text
        spannableStringBuilder.setSpan(clickableSpan, 32, privacyPolicyText.length(), 0);

        // Set the SpannableString to the TextView
        text_1.setText(spannableStringBuilder);

        // Make the TextView clickable and open links
        text_1.setMovementMethod(LinkMovementMethod.getInstance());


        ok_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                policyDialog.dismiss();
            }
        });

        findViewById(R.id.extra_screen_1_txt).setOnClickListener(v -> {
            AppOpenManager.isSplash = false;
            nextt();
        });

        ConsenFormMethod();
    }

    void ConsenFormMethod() {
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(this);
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

    public void nextt() {
        new InterAds().ShowfuullAd(this, new AdClosedListener() {
            @Override
            public void AdisClosed() {
                startActivity(new Intent(ExtaScreen1Activity.this, ExtaScreen2Activity.class));
            }
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