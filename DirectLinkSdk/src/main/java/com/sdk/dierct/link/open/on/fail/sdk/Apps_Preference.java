package com.sdk.dierct.link.open.on.fail.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Apps_Preference {

    public String USER_PREFS = "USER PREFS";
    public Editor prefEditor;
    public SharedPreferences appSharedPref;

    String Ad_Status = "Ad_Status";
    String Adstyle = "Adstyle";
    String AdstyleNative = "AdstyleNative";
    String AdstyleBanner = "AdstyleBanner";
    String Ad_Flag = "Ad_Flag";
    String Qureka_Flag = "Qureka_Flag";
    String Click_Flag = "Click_Flag";
    String Click_Count = "Click_Count";

    String Qureka_Link = "Qureka_Link";
    String Ad_Time_Interval = "Ad_Time_Interval";
    String Privacy_Policy = "Privacy_Policy";
    String url_for_ads = "url_for_ads";
    String direct_link = "direct_link";
    String backclickadstyle = "backclickadstyle";
    String openflag = "openflag";

    String Splash_Interstitial_Id = "Splash_Interstitial_Id";

    String Admob_Interstitial_Id1 = "Admob_Interstitial_Id1";
    String Admob_Interstitial_Id2 = "Admob_Interstitial_Id2";
    String Admob_Interstitial_Id3 = "Admob_Interstitial_Id3";
    String Admob_Native_Id1 = "Admob_Native_Id1";
    String Admob_Native_Id2 = "Admob_Native_Id2";
    String Admob_Native_Id3 = "Admob_Native_Id3";
    String Admob_Banner_Id1 = "Admob_Banner_Id1";
    String Admob_Banner_Id2 = "Admob_Banner_Id2";
    String Admob_Banner_Id3 = "Admob_Banner_Id3";

    String Facebook_Interstitial = "Facebook_Interstitial";
    String Facebook_Native = "Facebook_Native";
    String Facebook_Banner = "Facebook_Banner";
    String adbtcolor = "Adbtcolor";
    Context contexts;
    String medium = "medium";
    String nativecount = "nativecount";
    String Admob_OpenApp_Id1 = "admob-open1";

    String splash_flag = "splash_flag";
    String textColor = "textColor";
    String backColor = "backColor";
    String backflag = "backflag";
    String backclick = "backclick";
    String native_type_list = "native_type_list";
    String native_type_othe = "native_type_othe";
    public static boolean isFullScreenShow = false;
    String referrerUrl = "referrerUrl";
    String nativeflag = "native";
    String bannerflag = "banner";
    String fullflag = "fullflag";
    String app_status = "app_status";
    String app_name = "app_name";
    String app_image = "app_image";
    String app_link = "app_link";
    String Splash_OpenApp_Id = "Splash_OpenApp_Id";

    public Apps_Preference(Context context) {
        this.appSharedPref = context.getSharedPreferences(this.USER_PREFS, 0);
        this.prefEditor = this.appSharedPref.edit();
        contexts = context;
    }

    public boolean isConnected() {
        boolean connected;
        try {
            ConnectivityManager cm = (ConnectivityManager) contexts.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return false;
    }

    public String get_Splash_OpenApp_Id() {
        return this.appSharedPref.getString(this.Splash_OpenApp_Id, "");
    }

    public void set_Splash_OpenApp_Id(String str) {
        this.prefEditor.putString(this.Splash_OpenApp_Id, str).commit();
    }

    public String getUrl_for_ads() {
        return this.appSharedPref.getString(this.url_for_ads, "");
    }

    public void setUrl_for_ads(String url_for_ads) {
        this.prefEditor.putString(this.url_for_ads, url_for_ads).commit();
    }

    public String getDirect_link() {
        return this.appSharedPref.getString(this.direct_link, "");
    }

    public void setDirect_link(String direct_link) {
        this.prefEditor.putString(this.direct_link, direct_link).commit();
    }

    public String getMedium() {
        return this.appSharedPref.getString(this.medium, "");
    }

    public void setMedium(String medium) {
        this.prefEditor.putString(this.medium, medium).commit();
    }

    public String getBackclickadstyle() {
        return this.appSharedPref.getString(this.backclickadstyle, "");
    }

    public void setBackclickadstyle(String backclickadstyle) {
        this.prefEditor.putString(this.backclickadstyle, backclickadstyle).commit();
    }

    public String getNativecount() {
        return this.appSharedPref.getString(this.nativecount, "");
    }

    public void setNativecount(String str) {
        this.prefEditor.putString(this.nativecount, str).commit();
    }

    public String getReferrerUrl() {
        return this.appSharedPref.getString(this.referrerUrl, "");
    }

    public void setReferrerUrl(String type) {
        this.prefEditor.putString(this.referrerUrl, type).commit();
    }

    public String get_splash_flag() {
        return this.appSharedPref.getString(this.splash_flag, "");
    }

    public void set_splash_flag(String str) {
        this.prefEditor.putString(this.splash_flag, str).commit();
    }

    public void GetDatafromJson_AndSet(String res) throws JSONException {

        JSONObject object = new JSONObject(res);
        JSONArray jsonArray = new JSONArray(new JSONObject(object.getString("json_data")).getString("Data"));

        set_Splash_Interstitial_Id("ca-app-pub-3940256099942544/1033173712");
        set_Splash_OpenApp_Id("/6499/example/app-open");
        set_Admob_Interstitial_Id1("ca-app-pub-3940256099942544/1033173712");
        set_Admob_OpenApp_Id1("/6499/example/app-open");
        set_Admob_Native_Id1("ca-app-pub-3940256099942544/2247696110");
        set_Admob_Banner_Id1("ca-app-pub-3940256099942544/6300978111");

//        set_Splash_OpenApp_Id(jsonArray.getJSONObject(0).optString("admob-splash-open"));
//        set_Splash_Interstitial_Id(jsonArray.getJSONObject(0).optString("admob-splash"));
//        set_Admob_Interstitial_Id1(jsonArray.getJSONObject(0).optString("admob-full1"));
//        set_Admob_Banner_Id1(jsonArray.getJSONObject(0).optString("admob-banner1"));
//        set_Admob_Native_Id1(jsonArray.getJSONObject(0).optString("admob-native1"));
//        set_Admob_OpenApp_Id1(jsonArray.getJSONObject(0).optString("admob-open1"));

        set_Admob_Interstitial_Id2(jsonArray.getJSONObject(0).optString("admob-full2"));
        set_Admob_Interstitial_Id3(jsonArray.getJSONObject(0).optString("admob-full3"));
        set_Admob_Native_Id2(jsonArray.getJSONObject(0).optString("admob-native2"));
        set_Admob_Native_Id3(jsonArray.getJSONObject(0).optString("admob-native3"));
        set_Admob_Banner_Id2(jsonArray.getJSONObject(0).optString("admob-banner2"));
        set_Admob_Banner_Id3(jsonArray.getJSONObject(0).optString("admob-banner3"));

        set_Facebook_Interstitial(jsonArray.getJSONObject(0).optString("fb-full"));
        set_Facebook_Native(jsonArray.getJSONObject(0).optString("fb-native"));
        set_Facebook_Banner(jsonArray.getJSONObject(0).optString("fb-banner"));
        set_Qureka_Flag(jsonArray.getJSONObject(0).optString("qureka"));
        set_Qureka_Link(jsonArray.getJSONObject(0).optString("qureka-link"));
        setDirect_link(jsonArray.getJSONObject(0).optString("direct_link", "off"));
        setUrl_for_ads(jsonArray.getJSONObject(0).optString("url_for_ads"));
        set_Click_Count(jsonArray.getJSONObject(0).optString("click"));
        setBackclick(jsonArray.getJSONObject(0).optString("backclick"));
        set_Click_Flag(jsonArray.getJSONObject(0).optString("clickflag"));
        setBackflag(jsonArray.getJSONObject(0).optString("backflag"));
        setNativeTypeList(jsonArray.getJSONObject(0).optString("native_type_list"));
        setNativeTypeOther(jsonArray.getJSONObject(0).optString("native_type_other"));
        set_Ad_Flag(jsonArray.getJSONObject(0).optString("Adflag"));
        set_Adstyle(jsonArray.getJSONObject(0).optString("Adstyle"));
        setNativecount(jsonArray.getJSONObject(0).optString("nativecount", "2"));
        setBackclickadstyle(jsonArray.getJSONObject(0).optString("backclickadstyle", "admob"));
        set_AdstyleNative(jsonArray.getJSONObject(0).optString("AdstyleNative", "admob"));
        setAdstyleBanner(jsonArray.getJSONObject(0).optString("AdstyleBanner", "admob"));
        set_splash_flag(jsonArray.getJSONObject(0).optString("splash"));
        set_Ad_Status(jsonArray.getJSONObject(0).optString("Adstatus"));
        set_Privacy_Policy(jsonArray.getJSONObject(0).optString("pp"));
        setNativeflag(jsonArray.getJSONObject(0).optString("native", "on"));
        setBannerflag(jsonArray.getJSONObject(0).optString("banner", "on"));
        setFullflag(jsonArray.getJSONObject(0).optString("full", "on"));
        setMedium(jsonArray.getJSONObject(0).optString("medium"));
        setAdbtcolor(jsonArray.getJSONObject(0).optString("adbtclr"));
        setBackcolor(jsonArray.getJSONObject(0).optString("backcolor", "ffffff"));
        setTextColor(jsonArray.getJSONObject(0).optString("textcolor", "000000"));
        setApp_status(jsonArray.getJSONObject(0).optString("app_status"));
        setApp_image(jsonArray.getJSONObject(0).optString("app_image"));
        setApp_name(jsonArray.getJSONObject(0).optString("app_name"));
        setApp_link(jsonArray.getJSONObject(0).optString("app_link"));
        setOpenflag(jsonArray.getJSONObject(0).optString("open", "on"));
    }

    public String getOpenflag() {
        return this.appSharedPref.getString(this.openflag, "");
    }

    public void setOpenflag(String str) {
        this.prefEditor.putString(this.openflag, str).commit();
    }

    public String getApp_name() {
        return this.appSharedPref.getString(this.app_name, "");
    }

    public void setApp_name(String str) {
        this.prefEditor.putString(this.app_name, str).commit();
    }

    public String getApp_status() {
        return this.appSharedPref.getString(this.app_status, "");
    }

    public void setApp_status(String str) {
        this.prefEditor.putString(this.app_status, str).commit();
    }

    public String getApp_link() {
        return this.appSharedPref.getString(this.app_link, "");
    }

    public void setApp_link(String str) {
        this.prefEditor.putString(this.app_link, str).commit();
    }

    public String getApp_image() {
        return this.appSharedPref.getString(this.app_image, "");
    }

    public void setApp_image(String str) {
        this.prefEditor.putString(this.app_image, str).commit();
    }

    public String getFullflag() {
        return this.appSharedPref.getString(this.fullflag, "");
    }

    public void setFullflag(String str) {
        this.prefEditor.putString(this.fullflag, str).commit();
    }

    public String getNativeflag() {
        return this.appSharedPref.getString(this.nativeflag, "");
    }

    public void setNativeflag(String str) {
        this.prefEditor.putString(this.nativeflag, str).commit();
    }

    public String getBannerflag() {
        return this.appSharedPref.getString(this.bannerflag, "");
    }

    public void setBannerflag(String str) {
        this.prefEditor.putString(this.bannerflag, str).commit();
    }


    public String get_Admob_OpenApp_Id1() {
        return this.appSharedPref.getString(this.Admob_OpenApp_Id1, "");
    }

    public void set_Admob_OpenApp_Id1(String str) {
        this.prefEditor.putString(this.Admob_OpenApp_Id1, str).commit();
    }


    public String getBackflag() {
        return this.appSharedPref.getString(this.backflag, "");
    }

    public void setBackflag(String backflag) {
        this.prefEditor.putString(this.backflag, backflag).commit();
    }

    public String getBackclick() {
        return this.appSharedPref.getString(this.backclick, "");
    }

    public void setBackclick(String backclick) {
        this.prefEditor.putString(this.backclick, backclick).commit();
    }

    public void setNativeTypeOther(String type) {
        this.prefEditor.putString(this.native_type_othe, type).commit();
    }

    public String getNativeTypeList() {
        return this.appSharedPref.getString(this.native_type_list, "");
    }

    public void setNativeTypeList(String type) {
        this.prefEditor.putString(this.native_type_list, type).commit();
    }

    public String getNativeTypeOther() {
        return this.appSharedPref.getString(this.native_type_othe, "");
    }

    public String get_Click_Flag() {
        return this.appSharedPref.getString(this.Click_Flag, "");
    }

    public void set_Click_Flag(String str) {
        this.prefEditor.putString(this.Click_Flag, str).commit();
    }

    public String get_Click_Count() {
        return this.appSharedPref.getString(this.Click_Count, "");
    }

    public void set_Click_Count(String str) {
        this.prefEditor.putString(this.Click_Count, str).commit();
    }

    public String get_Ad_Status() {
        return this.appSharedPref.getString(this.Ad_Status, "");
    }

    public void set_Ad_Status(String str) {
        this.prefEditor.putString(this.Ad_Status, str).commit();
    }

    public String get_Adstyle() {
        return this.appSharedPref.getString(this.Adstyle, "");
    }

    public void set_Adstyle(String str) {
        this.prefEditor.putString(this.Adstyle, str).commit();
    }

    public String getAdstyleBanner() {
        return this.appSharedPref.getString(this.AdstyleBanner, "");
    }

    public void setAdstyleBanner(String AdstyleBanner) {
        this.prefEditor.putString(this.AdstyleBanner, AdstyleBanner).commit();
    }

    public String get_AdstyleNative() {
        return this.appSharedPref.getString(this.AdstyleNative, "");
    }

    public void set_AdstyleNative(String str) {
        this.prefEditor.putString(this.AdstyleNative, str).commit();
    }

    public String get_Ad_Flag() {
        return this.appSharedPref.getString(this.Ad_Flag, "");
    }

    public void set_Ad_Flag(String str) {
        this.prefEditor.putString(this.Ad_Flag, str).commit();
    }

    // Qureka
    public String get_Qureka_Link() {
        return this.appSharedPref.getString(this.Qureka_Link, "");
    }

    public void set_Qureka_Link(String str) {
        this.prefEditor.putString(this.Qureka_Link, str).commit();
    }

    public String get_Qureka_Flag() {
        return this.appSharedPref.getString(this.Qureka_Flag, "");
    }

    public void set_Qureka_Flag(String str) {
        this.prefEditor.putString(this.Qureka_Flag, str).commit();
    }

    public String get_Privacy_Policy() {
        return this.appSharedPref.getString(this.Privacy_Policy, "");
    }

    public void set_Privacy_Policy(String str) {
        this.prefEditor.putString(this.Privacy_Policy, str).commit();
    }

    //Interstitial Ads
    public String get_Splash_Interstitial_Id() {
        return this.appSharedPref.getString(this.Splash_Interstitial_Id, "");
    }

    public void set_Splash_Interstitial_Id(String str) {
        this.prefEditor.putString(this.Splash_Interstitial_Id, str).commit();
    }

    public String get_Facebook_Interstitial() {
        return this.appSharedPref.getString(this.Facebook_Interstitial, "");
    }

    public void set_Facebook_Interstitial(String str) {
        this.prefEditor.putString(this.Facebook_Interstitial, str).commit();
    }

    public String get_Admob_Interstitial_Id1() {
        return this.appSharedPref.getString(this.Admob_Interstitial_Id1, "");
    }

    public void set_Admob_Interstitial_Id1(String str) {
        this.prefEditor.putString(this.Admob_Interstitial_Id1, str).commit();
    }

    public String get_Admob_Interstitial_Id2() {
        return this.appSharedPref.getString(this.Admob_Interstitial_Id2, "");
    }

    public void set_Admob_Interstitial_Id2(String str) {
        this.prefEditor.putString(this.Admob_Interstitial_Id2, str).commit();
    }

    public String get_Admob_Interstitial_Id3() {
        return this.appSharedPref.getString(this.Admob_Interstitial_Id3, "");
    }

    public void set_Admob_Interstitial_Id3(String str) {
        this.prefEditor.putString(this.Admob_Interstitial_Id3, str).commit();
    }

    // Native Ads
    public String get_Admob_Native_Id1() {
        return this.appSharedPref.getString(this.Admob_Native_Id1, "");
    }

    public void set_Admob_Native_Id1(String str) {
        this.prefEditor.putString(this.Admob_Native_Id1, str).commit();
    }

    public String get_Admob_Native_Id2() {
        return this.appSharedPref.getString(this.Admob_Native_Id2, "");
    }

    public void set_Admob_Native_Id2(String str) {
        this.prefEditor.putString(this.Admob_Native_Id2, str).commit();
    }

    public String get_Admob_Native_Id3() {
        return this.appSharedPref.getString(this.Admob_Native_Id3, "");
    }

    public void set_Admob_Native_Id3(String str) {
        this.prefEditor.putString(this.Admob_Native_Id3, str).commit();
    }

    public String get_Facebook_Native() {
        return this.appSharedPref.getString(this.Facebook_Native, "");
    }

    public void set_Facebook_Native(String str) {
        this.prefEditor.putString(this.Facebook_Native, str).commit();
    }

    // Banner Ads
    public String get_Admob_Banner_Id1() {
        return this.appSharedPref.getString(this.Admob_Banner_Id1, "");
    }

    public void set_Admob_Banner_Id1(String str) {
        this.prefEditor.putString(this.Admob_Banner_Id1, str).commit();
    }

    public String get_Admob_Banner_Id2() {
        return this.appSharedPref.getString(this.Admob_Banner_Id2, "");
    }

    public void set_Admob_Banner_Id2(String str) {
        this.prefEditor.putString(this.Admob_Banner_Id2, str).commit();
    }

    public String get_Admob_Banner_Id3() {
        return this.appSharedPref.getString(this.Admob_Banner_Id3, "");
    }

    public void set_Admob_Banner_Id3(String str) {
        this.prefEditor.putString(this.Admob_Banner_Id3, str).commit();
    }

    public String get_Facebook_Banner() {
        return this.appSharedPref.getString(this.Facebook_Banner, "");
    }

    public void set_Facebook_Banner(String str) {
        this.prefEditor.putString(this.Facebook_Banner, str).commit();
    }

    //Set Color
    public String getAdbtcolor() {
        return this.appSharedPref.getString(this.adbtcolor, "");
    }

    public void setAdbtcolor(String str) {
        this.prefEditor.putString(this.adbtcolor, str).commit();
    }

    public String getBackColor() {
        return this.appSharedPref.getString(this.backColor, "");
    }

    public void setBackcolor(String str) {
        this.prefEditor.putString(this.backColor, str).commit();
    }

    public String getTextColor() {
        return this.appSharedPref.getString(this.textColor, "");
    }

    public void setTextColor(String str) {
        this.prefEditor.putString(this.textColor, str).commit();
    }
}