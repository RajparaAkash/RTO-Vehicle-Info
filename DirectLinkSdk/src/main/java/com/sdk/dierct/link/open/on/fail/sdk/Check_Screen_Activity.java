package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class Check_Screen_Activity {
    public static void CallOpenAd(Apps_Preference preference, Activity activity, Intent intent) {
        String string = preference.get_Splash_OpenApp_Id();
        if (Apps_Preference.isFullScreenShow) {
            return;
        }
        try {
            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                    super.onAdLoaded(appOpenAd);
                    FullScreenContentCallback r0 = new FullScreenContentCallback() {
                        @Override
                        public void onAdShowedFullScreenContent() {
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            activity.startActivity(intent);
                            activity.finish();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    };
                    appOpenAd.show(activity);
                    appOpenAd.setFullScreenContentCallback(r0);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);

                    new Handler().postDelayed(() -> {
                        activity.startActivity(intent);
                        activity.finish();
                    },2000);

                }
            };
            AppOpenAd.load(activity, string, new AdRequest.Builder().build(), 1, loadCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void startAdLoading(Activity activity, Apps_Preference preference, Intent intent) {
        MyApplication_.loadAds(initializationStatus -> {
            if (preference.get_splash_flag().equalsIgnoreCase("open")) {
                if (preference.getOpenflag().equalsIgnoreCase("on")) {
                    CallOpenAd(preference, activity, intent);
                } else {
                    activity.startActivity(intent);
                }
            } else {
                if (preference.getFullflag().equalsIgnoreCase("on")) {
                    new Handler().postDelayed(() -> {
                        new Interstitial_FullAd_Splash().Show_Ads(activity, intent, true);
                    }, 1000);
                } else {
                    new Handler().postDelayed(() -> {
                        activity.startActivity(intent);
                    }, 1000);
                }
            }
        });
    }

    public static void Native_Banner_Count(Activity activity, FrameLayout viewGroup) {
        Apps_Preference preference = new Apps_Preference(activity);
        int nativecount = Integer.parseInt(preference.getNativecount());
        if (Con_stant.NativeCountIncr == nativecount) {
            Con_stant.NativeCountIncr = 0;
        }
        if (Con_stant.NativeCountIncr % nativecount == 0) {
            Con_stant.NativeCountIncr++;
            Native_AdPreload1.getInstance(activity).Native_Banner_Ads(activity, viewGroup);
        } else {
            Con_stant.NativeCountIncr++;
        }
    }

    public static void Native_Large_Count(Activity activity, FrameLayout viewGroup, boolean isList) {
        Apps_Preference preference = new Apps_Preference(activity);
        int nativecount = Integer.parseInt(preference.getNativecount());
        if (Con_stant.NativeCountIncr == nativecount) {
            Con_stant.NativeCountIncr = 0;
        }
        if (Con_stant.NativeCountIncr % nativecount == 0) {
            Con_stant.NativeCountIncr++;
            Native_AdPreload1.getInstance(activity).addNativeAd(viewGroup, isList);
        } else {
            Con_stant.NativeCountIncr++;
        }
    }
}
