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
//public class Interst_AdLoad_BackClick {
//    private static final String TAG = "fbAd";
//    public static Context context;
//
//    public static InterstitialAd interstitialAdback;
//    public static com.facebook.ads.InterstitialAd fb_interstitialback;
//    public static AdManagerInterstitialAd adManagerInterstitialAdback;
//
//
//    public Interst_AdLoad_BackClick(Context context) {
//        loadad(context);
//    }
//
//    private void loadad(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//        if (preference.getBackclickadstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//            loadadmob(context);
//        } else if (preference.getBackclickadstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("adx")) {
//            loadadx(context);
//        } else if (preference.getBackclickadstyle().equalsIgnoreCase("fb")) {
//            loadFbAd(context);
//        }
//    }
//
//    public static void ShowfuullAd(Activity activity, AdClosedListener referrerListener) {
//        Apps_Preference preference = new Apps_Preference(activity);
//        if (preference.getBackflag().equalsIgnoreCase("on")) {
//            if (Con_stant.Back_Counter % Integer.parseInt(preference.getBackclick()) == 0) {
//                Con_stant.Back_Counter++;
//                if (preference.getBackclickadstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("admob")) {
//                    showadmob(activity, referrerListener);
//                } else if (preference.getBackclickadstyle().equalsIgnoreCase("Normal") && preference.get_Ad_Flag().equalsIgnoreCase("adx")) {
//                    showadx(activity, referrerListener);
//                } else if (preference.getBackclickadstyle().equalsIgnoreCase("fb")) {
//                    showFbAd(activity, referrerListener);
//                } else {
//                    referrerListener.AdisClosed();
//                }
//            } else {
//                Con_stant.Back_Counter++;
//                referrerListener.AdisClosed();
//            }
//        } else {
//            referrerListener.AdisClosed();
//        }
//    }
//
//    private static void showFbAd(Activity activity, AdClosedListener referrerListener) {
//        if (fb_interstitialback != null || fb_interstitialback.isAdLoaded()) {
//            fb_interstitialback.show();
//            fb_interstitialback.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
//                @Override
//                public void onInterstitialDisplayed(Ad ad) {
//
//                }
//
//                @Override
//                public void onInterstitialDismissed(Ad ad) {
//                    if (referrerListener != null) {
//                        referrerListener.AdisClosed();
//                    }
//                    loadFbAd(activity);
//                }
//
//                @Override
//                public void onError(Ad ad, AdError adError) {
//                    if (referrerListener != null) {
//                        referrerListener.AdisClosed();
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
//            Log.e("fail", "failed");
//        }
//    }
//
//    private static void showadmob(Activity context, AdClosedListener referrerListener) {
//        if (interstitialAdback != null) {
//            interstitialAdback.show(context);
//            interstitialAdback.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdClicked() {
//                    super.onAdClicked();
//                }
//
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent();
//                    if (referrerListener != null) {
//                        referrerListener.AdisClosed();
//                    }
//                    loadadmob(context);
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
//                    super.onAdFailedToShowFullScreenContent(adError);
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    super.onAdShowedFullScreenContent();
//                }
//            });
//        }
//    }
//
//    private static void showadx(Activity context, AdClosedListener referrerListener) {
//        if (adManagerInterstitialAdback != null) {
//            adManagerInterstitialAdback.show(context);
//            adManagerInterstitialAdback.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdClicked() {
//                    super.onAdClicked();
//                }
//
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent();
//                    if (referrerListener != null) {
//                        referrerListener.AdisClosed();
//                    }
//                    loadadx(context);
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
//                    super.onAdFailedToShowFullScreenContent(adError);
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
//        }
//    }
//
//    private static void loadFbAd(Context context) {
//        Apps_Preference preference = new Apps_Preference(context);
//
//        fb_interstitialback = new com.facebook.ads.InterstitialAd(context, preference.get_Facebook_Interstitial());
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
//        fb_interstitialback.loadAd(
//                fb_interstitialback.buildLoadAdConfig()
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
//                        interstitialAdback = interstitialAds;
//                        Log.i(TAG, "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
//                        interstitialAdback = null;
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
//                        adManagerInterstitialAdback = interstitialAds;
//                        Log.i(TAG, "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
//                        adManagerInterstitialAdback = null;
//                    }
//                });
//    }
//}
