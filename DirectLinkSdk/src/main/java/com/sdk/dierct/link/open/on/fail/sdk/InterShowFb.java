package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;

import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;

public class InterShowFb {

    public static void ShowAd_FullFb(Activity source_class, AdClosedListener adCloseListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        Con_stant.Front_Counter++;
        final Custom_Prog_Dialog customProgressDialog = new Custom_Prog_Dialog(source_class, "Showing Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();
        com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
        fb_interstitial.loadAd(
                fb_interstitial.buildLoadAdConfig()
                        .withAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                Apps_Preference.isFullScreenShow = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Apps_Preference.isFullScreenShow = false;
                                if (customProgressDialog.isShowing()) {
                                    customProgressDialog.dismiss();
                                }
                                if (adCloseListener != null) {
                                    adCloseListener.AdisClosed();
                                }
                            }

                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                Apps_Preference.isFullScreenShow = false;

                                if (customProgressDialog.isShowing()) {
                                    customProgressDialog.dismiss();
                                }

                                Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (!fb_interstitial.isAdLoaded()) {
                                    return;
                                }
                                if (fb_interstitial.isAdInvalidated()) {
                                    return;
                                }
                                fb_interstitial.show();
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        })
                        .build());
    }
}
