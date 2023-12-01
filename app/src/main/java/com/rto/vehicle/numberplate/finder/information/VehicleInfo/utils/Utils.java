package com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;


public class Utils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrEmptyOrNA(String str) {
        return str == null || str.isEmpty() || str.equalsIgnoreCase("na") || str.equalsIgnoreCase("n/a") || str.equalsIgnoreCase("not available") || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("0");
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPatternMatches(String str) {
        return Pattern.compile("(\\w{4,11})").matcher(str).matches();
    }

    public static String formatString(String str) {
        return isNullOrEmpty(str) ? "" : str.replaceAll("[^A-Za-z0-9]", "");
    }

    public static boolean isLicensePatternMatches(String str) {
        return Pattern.compile("(\\w{10,20})").matcher(str).matches();
    }

    public static boolean isDigitOnly(String str) {
        return Pattern.compile("(\\d+)").matcher(str).matches();
    }

    public static boolean isValidDecimal(String str) {
        return Pattern.compile("^[.0-9]+(\\.\\d+)?%?$").matcher(str).matches();
    }

    public static String formatPrice(double d) {
        return new DecimalFormat("###.##").format(d);
    }


    public static Date formatDateByPattern(String str, String str2) {
        if (str2 == null || str2.contentEquals("")) {
            return new Date();
        }
        try {
            return new SimpleDateFormat(str, Locale.US).parse(str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getVehicleAge(String str) {
        Date formatDateByPattern;
        String concat;
        String concat2;
        if (isNullOrEmpty(str) || (formatDateByPattern = formatDateByPattern("dd-MMM-yyyy", str)) == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatDateByPattern);
        Calendar calendar2 = Calendar.getInstance();
        int i = calendar2.get(1) - calendar.get(1);
        int i2 = calendar2.get(2) + 1;
        int i3 = calendar.get(2) + 1;
        int i4 = i2 - i3;
        if (i4 < 0) {
            i--;
            i4 = (12 - i3) + i2;
            if (calendar2.get(5) < calendar.get(5)) {
                i4--;
            }
        } else if (i4 == 0 && calendar2.get(5) < calendar.get(5)) {
            i--;
            i4 = 11;
        }
        int i5 = 0;
        if (calendar2.get(5) > calendar.get(5)) {
            i5 = calendar2.get(5) - calendar.get(5);
        } else if (calendar2.get(5) < calendar.get(5)) {
            int i6 = calendar2.get(5);
            calendar2.add(2, -1);
            i5 = (calendar2.getActualMaximum(5) - calendar.get(5)) + i6;
        } else if (i4 == 12) {
            i++;
            i4 = 0;
        }
        if (i <= 1) {
            concat = "".concat(String.valueOf(i)).concat(" Year ");
        } else {
            concat = "".concat(String.valueOf(i)).concat(" Years ");
        }
        if (i4 <= 1) {
            concat2 = concat.concat(String.valueOf(i4)).concat(" Month ");
        } else {
            concat2 = concat.concat(String.valueOf(i4)).concat(" Months ");
        }
        if (i5 <= 1) {
            return concat2.concat(String.valueOf(i5)).concat(" Day");
        }
        return concat2.concat(String.valueOf(i5)).concat(" Days");
    }

    public static String getInsuranceAge(String str) {
        Date formatDateByPattern;
        String concat;
        String concat2;
        if (isNullOrEmpty(str) || (formatDateByPattern = formatDateByPattern("dd-MMM-yyyy", str)) == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatDateByPattern);
        Calendar calendar2 = Calendar.getInstance();
        if (calendar2.get(1) > calendar.get(1) || calendar2.get(2) + 1 > calendar.get(2) + 1) {
            return "EXPIRED";
        }
        int i = calendar.get(1) - calendar2.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar2.get(2) + 1;
        int i4 = i2 - i3;
        if (i4 < 0) {
            i--;
            i4 = (12 - i3) + i2;
            if (calendar2.get(5) < calendar.get(5)) {
                i4--;
            }
        } else if (i4 == 0 && calendar2.get(5) < calendar.get(5)) {
            i--;
            i4 = 11;
        }
        int i5 = 0;
        if (calendar2.get(5) > calendar.get(5)) {
            i5 = calendar2.get(5) - calendar.get(5);
        } else if (calendar2.get(5) < calendar.get(5)) {
            int i6 = calendar2.get(5);
            calendar2.add(2, -1);
            i5 = (calendar2.getActualMaximum(5) - calendar.get(5)) + i6;
        } else if (i4 == 12) {
            i++;
            i4 = 0;
        }
        if (i <= 1) {
            concat = "".concat(String.valueOf(i)).concat(" Year ");
        } else {
            concat = "".concat(String.valueOf(i)).concat(" Years ");
        }
        if (i4 <= 1) {
            concat2 = concat.concat(String.valueOf(i4)).concat(" Month ");
        } else {
            concat2 = concat.concat(String.valueOf(i4)).concat(" Months ");
        }
        if (i5 <= 1) {
            return concat2.concat(String.valueOf(i5)).concat(" Day");
        }
        return concat2.concat(String.valueOf(i5)).concat(" Days");
    }

    public static void contactUs(Activity activity, String str, String str2) {
        try {
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", GlobalReferenceEngine.contactEmail, null));
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", str2);
            activity.startActivity(Intent.createChooser(intent, "Send email..."));
        } catch (Throwable th) {
        }
    }

    public static String getDeviceData() {
        return "--------------------------------------------\nDevice Model: " + Build.MODEL + "\nAndroid Version: " + Build.VERSION.RELEASE + "\nDevice: " + Build.DEVICE + "\nDevice Brand: " + Build.BRAND + "\n";
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAndroidId(Context context) {
        String keyDeviceId = PreferencesHelper.getKeyDeviceId();
        if (isNullOrEmpty(keyDeviceId)) {
            try {
                keyDeviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
                PreferencesHelper.setKeyDeviceId(keyDeviceId);
                return keyDeviceId;
            } catch (Exception e) {
                e.printStackTrace();
                return keyDeviceId;
            }
        }
        return keyDeviceId;
    }

    public static String getTimeInMilli() {
        return String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime());
    }

    public static String getRandomNumber() {
        return String.valueOf(new Random().nextInt(1000));
    }

    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static String encryptStr(Context context, String str, String str2) {
        byte[] bytes = ("android_vehicle-details|encTradetu-" + str + "|" + getAppVersionCode(context) + "|" + str2).getBytes();
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            messageDigest.update(bytes);
            for (byte b : messageDigest.digest()) {
                String hexString = Integer.toHexString(b & (-1));
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static boolean isActivityFinished(Context context) {
        if (context instanceof Activity) {
            if (Build.VERSION.SDK_INT < 17) {
                return !((Activity) context).isFinishing();
            }
            Activity activity = (Activity) context;
            return !activity.isFinishing() && !activity.isDestroyed();
        }
        return false;
    }

    public static String[] splitRegistrationNo(String str) {
        String[] strArr = new String[2];
        try {
            String str2 = str.split("\\d*$")[0];
            strArr[0] = str2;
            String replace = str.replace(str2, "");
            strArr[1] = replace;
            if (isNullOrEmpty(replace)) {
                strArr[1] = "0";
            }
        } catch (Exception unused) {
            strArr[0] = str;
            strArr[1] = "0";
        }
        return strArr;
    }


    public static String getSearchTypeByNo(String str) {
        return isNullOrEmpty(str) ? "" : str.length() <= 11 ? "RC" : "DL";
    }

    public static String formatPrice(int i) {
        if (i <= 0) {
            return "0";
        }
        String valueOf = String.valueOf(i);
        if (valueOf.length() <= 3) {
            return valueOf;
        }
        String str = "";
        try {
            int length = valueOf.length() - 3;
            if (length % 2 == 0) {
                for (int i2 = 1; i2 <= length; i2++) {
                    if (i2 % 2 == 0) {
                        str = str.concat(String.valueOf(valueOf.charAt(i2 - 1))).concat(",");
                    } else {
                        str = str.concat(String.valueOf(valueOf.charAt(i2 - 1)));
                    }
                }
            } else {
                for (int i3 = 0; i3 < length; i3++) {
                    if (i3 == 0) {
                        str = str.concat(String.valueOf(valueOf.charAt(i3))).concat(",");
                    } else if (i3 % 2 == 0) {
                        str = str.concat(String.valueOf(valueOf.charAt(i3))).concat(",");
                    } else {
                        str = str.concat(String.valueOf(valueOf.charAt(i3)));
                    }
                }
            }
            return str.concat(valueOf.substring(length));
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(i);
        }
    }

    public static boolean isHoursDiff(long j, int i) {
        if (j <= 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - j;
        return currentTimeMillis > 0 && currentTimeMillis <= ((long) (((i * 60) * 60) * 1000));
    }

    public static String extractWarningMessage(String str, String str2) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        try {
            String substring = str.substring(str.indexOf("showMessageInDialog"));
            if (!isNullOrEmpty(substring) && substring.contains(",")) {
                String[] split = substring.split("\",");
                if (split.length < 3) {
                    return "";
                }
                String[] split2 = split[2].split("\"");
                return split2.length < 2 ? "" : split2[1].trim();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while fetching " + str2 + " details, please try again!";
        }
    }

    public static String formatUrl(String str) {
        if (str.contains("flipkart")) {
            if (!str.contains("?")) {
                return str.concat("?affid=").concat(GlobalReferenceEngine.flipkartAffiliateId).concat("&affExtParam1=rto_externallink");
            }
            if (!str.contains("affid")) {
                return str.concat("&affid=").concat(GlobalReferenceEngine.flipkartAffiliateId).concat("&affExtParam1=rto_externallink");
            }
            try {
                return GlobalReferenceEngine.cueLinksBaseUrl.concat(URLEncoder.encode(str, "UTF-8").replace("%2F", "/")).concat("&subid=rto_externallink");
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        } else if (str.contains("2gud")) {
            if (!str.contains("?")) {
                return str.concat("?affid=").concat(GlobalReferenceEngine.twoGudAffiliateId).concat("&affExtParam1=rto_externallink");
            }
            if (!str.contains("affid")) {
                return str.concat("&affid=").concat(GlobalReferenceEngine.twoGudAffiliateId).concat("&affExtParam1=rto_externallink");
            }
            try {
                return GlobalReferenceEngine.cueLinksBaseUrl.concat(URLEncoder.encode(str, "UTF-8").replace("%2F", "/")).concat("&subid=rto_externallink");
            } catch (Exception unused) {
                return str;
            }
        } else if (!str.contains("amazon.in")) {
            try {
                return GlobalReferenceEngine.cueLinksBaseUrl.concat(URLEncoder.encode(str, "UTF-8").replace("%2F", "/")).concat("&subid=rto_externallink");
            } catch (Exception unused2) {
                return str;
            }
        } else if (!str.contains("?")) {
            return str.concat("?tag=").concat(GlobalReferenceEngine.amazonAffiliateId).concat("&ascsubtag=rto_externallink");
        } else {
            if (!str.contains("tag")) {
                return str.concat("&tag=").concat(GlobalReferenceEngine.amazonAffiliateId).concat("&ascsubtag=rto_externallink");
            }
            try {
                return GlobalReferenceEngine.cueLinksBaseUrl.concat(URLEncoder.encode(str, "UTF-8").replace("%2F", "/")).concat("&subid=rto_externallink");
            } catch (Exception unused3) {
                return str;
            }
        }
    }

    public static String getOwnershipString(String str) {
        return (isNullOrEmpty(str) || str.equalsIgnoreCase("1")) ? "FIRST OWNER" : str.equalsIgnoreCase("2") ? "SECOND OWNER" : str.equalsIgnoreCase("3") ? "THIRD OWNER" : str.equalsIgnoreCase("4") ? "FOURTH OWNER" : str.equalsIgnoreCase("5") ? "FIFTH OWNER" : str.equalsIgnoreCase("6") ? "SIXTH OWNER" : str.equalsIgnoreCase("7") ? "SEVENTH OWNER" : str.equalsIgnoreCase("8") ? "EIGHTH OWNER" : str.equalsIgnoreCase("9") ? "NINTH OWNER" : str.equalsIgnoreCase("10") ? "TENTH OWNER" : str.equalsIgnoreCase("11") ? "ELEVENTH OWNER" : str.equalsIgnoreCase("12") ? "TWELFTH OWNER" : str.equalsIgnoreCase("13") ? "THIRTEENTH OWNER" : str.equalsIgnoreCase("14") ? "FOUTEENTH OWNER" : str.equalsIgnoreCase("15") ? "FIFTEENTH OWNER" : "FIRST OWNER";
    }

    public static String hideString(String str) {
        return isNullOrEmpty(str) ? "" : str.replaceAll("\\w", "X");
    }
}
