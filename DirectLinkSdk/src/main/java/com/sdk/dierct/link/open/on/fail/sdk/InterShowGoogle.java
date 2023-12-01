package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterShowGoogle {

    public static InterstitialAd mInterstitialAd_admob;
    public static AdManagerInterstitialAd adManagerInterstitialAd;

    public static void ShowAd_Full(Activity source_class, AdClosedListener adCloseListener) {
        Apps_Preference appPreference = new Apps_Preference(source_class);
        if (appPreference.get_Ad_Flag().equals("admob")) {
            ShowAd_FullAdmob(source_class, adCloseListener);
        } else if (appPreference.get_Ad_Flag().equals("adx")) {
            ShowAd_FullAdx(source_class, adCloseListener);
        }
    }

    public static void ShowAd_FullAdmob(Activity source_class, AdClosedListener adCloseListener) {

        final Custom_Prog_Dialog customProgressDialog = new Custom_Prog_Dialog(source_class, "Showing Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(source_class, new Apps_Preference(source_class).get_Admob_Interstitial_Id1(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd_admob = interstitialAd;
                mInterstitialAd_admob.show(source_class);
                mInterstitialAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        mInterstitialAd_admob = null;
                        Apps_Preference.isFullScreenShow = false;
                        if (customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }
                        if (adCloseListener != null) {
                            adCloseListener.AdisClosed();
                        }
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        mInterstitialAd_admob = null;
                        Apps_Preference.isFullScreenShow = true;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        if (customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }
                        if (adCloseListener != null) {
                            adCloseListener.AdisClosed();
                        }
                        Apps_Preference.isFullScreenShow = false;

                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd_admob = null;
                Apps_Preference.isFullScreenShow = false;

                if (customProgressDialog.isShowing()) {
                    customProgressDialog.dismiss();
                }
                Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
            }
        });
    }

    public static void ShowAd_FullAdx(Activity source_class, AdClosedListener adCloseListener) {

        final Custom_Prog_Dialog customProgressDialog = new Custom_Prog_Dialog(source_class, "Showing Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(source_class, new Apps_Preference(source_class).get_Admob_Interstitial_Id1(), adRequest, new AdManagerInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                adManagerInterstitialAd = interstitialAd;
                adManagerInterstitialAd.show(source_class);
                adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        adManagerInterstitialAd = null;
                        if (customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }
                        if (adCloseListener != null) {
                            adCloseListener.AdisClosed();
                        }
                        Apps_Preference.isFullScreenShow = false;
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        adManagerInterstitialAd = null;
                        Apps_Preference.isFullScreenShow = true;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        if (customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }
                        Apps_Preference.isFullScreenShow = false;

                        if (adCloseListener != null) {
                            adCloseListener.AdisClosed();
                        }
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                adManagerInterstitialAd = null;
                Apps_Preference.isFullScreenShow = false;

                if (customProgressDialog.isShowing()) {
                    customProgressDialog.dismiss();
                }

                Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
            }
        });
    }
}
