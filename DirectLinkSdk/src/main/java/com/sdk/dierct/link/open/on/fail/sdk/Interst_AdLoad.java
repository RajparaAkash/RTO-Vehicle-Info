//package com.sdk.dierct.link.open.on.fail.sdk;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.InterstitialAdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.admanager.AdManagerAdRequest;
//import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
//import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//
//
//public class Interst_AdLoad {
//    private static final String TAG = "fbAd";
//    public static Context context;
//
//    public static InterstitialAd interstitialAd;
//    public static com.facebook.ads.InterstitialAd fb_interstitial;
//    public static AdManagerInterstitialAd adManagerInterstitialAd;
//
//
//    public Interst_AdLoad(Context context) {
//        loadad(context);
//    }
//
//    private void loadad(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//        if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//            loadadmob(context);
//        } else if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("adx")) {
//            loadadx(context);
//        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//            loadFbAd(context);
//        }
//    }
//
//    public static void ShowfuullAd(Activity activity, AdClosedListener referrerListener) {
//        Apps_Preference preference = new Apps_Preference(activity);
//        if (preference.get_Click_Flag().equalsIgnoreCase("on")) {
//            if (Con_stant.Front_Counter % Integer.parseInt(preference.get_Click_Count()) == 0) {
//                Con_stant.Front_Counter++;
//                if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//                    showadmob(activity, referrerListener);
//                } else if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("adx")) {
//                    showadx(activity, referrerListener);
//                } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                    showFbAd(activity, referrerListener);
//                } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                    if (Con_stant.Alt_Cnt_Inter == 2) {
//                        Con_stant.Alt_Cnt_Inter = 1;
//                        if (preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//                            showadmob(activity, referrerListener);
//                        } else {
//                            showadx(activity, referrerListener);
//                        }
//                    } else {
//                        Con_stant.Alt_Cnt_Inter++;
//                        showFbAd(activity, referrerListener);
//                    }
//                } else {
//                    referrerListener.AdisClosed();
//                }
//            } else {
//                Con_stant.Front_Counter++;
//                referrerListener.AdisClosed();
//            }
//        } else {
//            if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//                showadmob(activity, referrerListener);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("adx")) {
//                showadx(activity, referrerListener);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                showFbAd(activity, referrerListener);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                if (Con_stant.Alt_Cnt_Inter == 2) {
//                    Con_stant.Alt_Cnt_Inter = 1;
//                    if (preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//                        showadmob(activity, referrerListener);
//                    } else {
//                        showadx(activity, referrerListener);
//                    }
//                } else {
//                    Con_stant.Alt_Cnt_Inter++;
//                    showFbAd(activity, referrerListener);
//                }
//            } else {
//                referrerListener.AdisClosed();
//            }
//        }
//    }
//
//    private static void showFbAd(Activity activity, AdClosedListener adCloseListener) {
//        if (fb_interstitial != null || fb_interstitial.isAdLoaded()) {
//            fb_interstitial.show();
//            fb_interstitial.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
//                @Override
//                public void onInterstitialDisplayed(Ad ad) {
//
//                }
//
//                @Override
//                public void onInterstitialDismissed(Ad ad) {
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                    loadFbAd(activity);
//                }
//
//                @Override
//                public void onError(Ad ad, AdError adError) {
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                }
//
//                @Override
//                public void onAdLoaded(Ad ad) {
//                }
//
//                @Override
//                public void onAdClicked(Ad ad) {
//
//                }
//
//                @Override
//                public void onLoggingImpression(Ad ad) {
//
//                }
//            });
//        } else {
//            Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(activity, adCloseListener);
//        }
//    }
//
//    private static void showadmob(Activity context, AdClosedListener adCloseListener) {
//        if (interstitialAd != null) {
//            interstitialAd.show(context);
//            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdClicked() {
//                    super.onAdClicked();
//                }
//
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent();
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                    loadadmob(context);
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
//                    super.onAdFailedToShowFullScreenContent(adError);
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    super.onAdShowedFullScreenContent();
//                }
//            });
//        } else {
//            Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(context, adCloseListener);
//        }
//    }
//
//    private static void showadx(Activity context, AdClosedListener adCloseListener) {
//        if (adManagerInterstitialAd != null) {
//            adManagerInterstitialAd.show(context);
//            adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdClicked() {
//                    super.onAdClicked();
//                }
//
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent();
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                    loadadx(context);
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
//                    super.onAdFailedToShowFullScreenContent(adError);
//                    if (adCloseListener != null) {
//                        adCloseListener.AdisClosed();
//                    }
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    super.onAdShowedFullScreenContent();
//                }
//
//                @Override
//                public void onAdImpression() {
//                    super.onAdImpression();
//                }
//            });
//        } else {
//            Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(context, adCloseListener);
//        }
//    }
//
//    private static void loadFbAd(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//
//        fb_interstitial = new com.facebook.ads.InterstitialAd(context, preference.get_Facebook_Interstitial());
//        // Create listeners for the Interstitial Ad
//        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                Log.e(TAG, "Interstitial ad displayed.");
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                Log.e(TAG, "Interstitial ad dismissed.");
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Ad error callback
//                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                Log.d(TAG, "Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                Log.d(TAG, "Interstitial ad impression logged!");
//            }
//        };
//
//        // For auto play video ads, it's recommended to load the ad
//        // at least 30 seconds before it is shown
//        fb_interstitial.loadAd(
//                fb_interstitial.buildLoadAdConfig()
//                        .withAdListener(interstitialAdListener)
//                        .build());
//
//    }
//
//    private static void loadadmob(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(context, preference.get_Admob_Interstitial_Id1(), adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAds) {
//                        // The mInterstitialAd reference will be null until
//                        // an ad is loaded.
//                        interstitialAd = interstitialAds;
//                        Log.i(TAG, "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
//                        interstitialAd = null;
//                    }
//                });
//    }
//
//    private static void loadadx(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
//
//        AdManagerInterstitialAd.load(context, preference.get_Admob_Interstitial_Id1(), adRequest,
//                new AdManagerInterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAds) {
//                        // The mInterstitialAd reference will be null until
//                        // an ad is loaded.
//                        adManagerInterstitialAd = interstitialAds;
//                        Log.i(TAG, "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
//                        adManagerInterstitialAd = null;
//                    }
//                });
//    }
//}
