package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;

public class InterAdsBack {

    public void ShowfuullAd(Activity source_class, AdClosedListener adCloseListener) {

        Apps_Preference preference = new Apps_Preference(source_class);

        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {

            if (preference.getFullflag().equalsIgnoreCase("on")) {

                if (preference.getBackflag().equalsIgnoreCase("on")) {

                    if (Con_stant.Back_Counter % Integer.parseInt(preference.getBackclick()) == 0) {
                        Con_stant.Back_Counter++;
                        callad(preference, source_class, adCloseListener);
                    } else {
                        Con_stant.Back_Counter++;
                        if (adCloseListener != null) {
                            adCloseListener.AdisClosed();
                        }
                    }

                } else {
                    if (adCloseListener != null) {
                        adCloseListener.AdisClosed();
                    }
                }
            } else {
                if (adCloseListener != null) {
                    adCloseListener.AdisClosed();
                }
            }
        } else {
            if (adCloseListener != null) {
                adCloseListener.AdisClosed();
            }
        }
    }

    public void callad(Apps_Preference preference, Activity source_class, AdClosedListener adCloseListener) {

        if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
            InterShowGoogle.ShowAd_Full(source_class, adCloseListener);
        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
            InterShowFb.ShowAd_FullFb(source_class, adCloseListener);
        } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
            if (Con_stant.Alt_Cnt_Inter == 2) {
                Con_stant.Alt_Cnt_Inter = 1;
                InterShowGoogle.ShowAd_Full(source_class, adCloseListener);
            } else {
                Con_stant.Alt_Cnt_Inter++;
                InterShowFb.ShowAd_FullFb(source_class, adCloseListener);
            }
        }
    }
}
