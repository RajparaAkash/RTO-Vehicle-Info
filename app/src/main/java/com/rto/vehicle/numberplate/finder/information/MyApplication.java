package com.rto.vehicle.numberplate.finder.information;

import android.os.StrictMode;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOExamResultModel;
import com.rto.vehicle.numberplate.finder.information.Model.BIKE_RTOQuestionModel;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalContext;
import com.sdk.dierct.link.open.on.fail.sdk.MyApplication_;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyApplication extends MyApplication_ {

    public static String DATE_FORMATE = "dd-MM-yyyy";
    public static String DefaultPref = "DefaultPref";
    public static String ENGLISH_RESOURCE = "en";
    public static ArrayList<BIKE_RTOQuestionModel> EQuestionsArray = new ArrayList<>();
    public static String EXAM_RESOURCE = "en";
    public static ArrayList<BIKE_RTOQuestionModel> GQuestionsArray = new ArrayList<>();
    public static String GUJARATI_RESOURCE = "gu";
    public static ArrayList<BIKE_RTOQuestionModel> HQuestionsArray = new ArrayList<>();
    public static String HINDI_RESOURCE = "hi";
    public static BIKE_RTOExamResultModel LastRTOExamResultModel = new BIKE_RTOExamResultModel();
    public static String MARATHI_RESOURCE = "mr";
    public static ArrayList<BIKE_RTOQuestionModel> MQuestionsArray = new ArrayList<>();
    public static ArrayList<BIKE_RTOQuestionModel> QuestionsArray = new ArrayList<>();
    public static String RTOExamResult = "RTOExamResult";
    public static ArrayList<Integer> SignList = new ArrayList<>();
    public static int INSURANCE_DETAILS_SCREEN_VIEW_COUNTER = 0;
    public static int SEARCH_VEHICLE_SCREEN_VIEW_COUNTER = 0;
    public static int VEHICLE_DETAILS_CLICK_TO_SEE_SCREEN_VIEW_COUNTER = 0;
    public static int VEHICLE_DETAILS_SCREEN_VIEW_COUNTER = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        GlobalContext.initialize(this);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        String ONESIGNAL_APP_ID = "d62e1d74-1b9c-4e33-aacf-868e7b2a7571";
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.initWithContext(this);

        OneSignal.setNotificationOpenedHandler(result ->
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "OSNotificationOpenedResult result: " + result.toString()));

        OneSignal.setNotificationWillShowInForegroundHandler(notificationReceivedEvent -> {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "NotificationWillShowInForegroundHandler fired!" +
                    " with notification event: " + notificationReceivedEvent.toString());

            OSNotification notification = notificationReceivedEvent.getNotification();

            notificationReceivedEvent.complete(notification);
        });
        Logger logger = Logger.getLogger(getPackageName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        RtoQuestionData_Fatch();
        RtoSigns_Fatch();
    }

    private void RtoSigns_Fatch() {
        SignList.add(R.drawable.sign163);
        SignList.add(R.drawable.sign165);
        SignList.add(R.drawable.sign166);
        SignList.add(R.drawable.sign81);
        SignList.add(R.drawable.sign76);
        SignList.add(R.drawable.sign69);
        SignList.add(R.drawable.sign4);
        SignList.add(R.drawable.sign19);
        SignList.add(R.drawable.sign90);
        SignList.add(R.drawable.sign67);
        SignList.add(R.drawable.sign20);
        SignList.add(R.drawable.sign3);
        SignList.add(R.drawable.sign9);
        SignList.add(R.drawable.sign17);
        SignList.add(R.drawable.sign18);
        SignList.add(R.drawable.sign16);
        SignList.add(R.drawable.sign162);
        SignList.add(R.drawable.sign89);
        SignList.add(R.drawable.sign64);
        SignList.add(R.drawable.sign14);
        SignList.add(R.drawable.sign21);
        SignList.add(R.drawable.sign30);
        SignList.add(R.drawable.sign82);
        SignList.add(R.drawable.sign83);
        SignList.add(R.drawable.sign91);
        SignList.add(R.drawable.sign94);
        SignList.add(R.drawable.sign6);
        SignList.add(R.drawable.sign65);
        SignList.add(R.drawable.sign80);
        SignList.add(R.drawable.sign11);
        SignList.add(R.drawable.sign22);
        SignList.add(R.drawable.sign15);
        SignList.add(R.drawable.sign164);
        SignList.add(R.drawable.sign12);
        SignList.add(R.drawable.sign92);
        SignList.add(R.drawable.sign167);
        SignList.add(R.drawable.sign24);
        SignList.add(R.drawable.sign25);
        SignList.add(R.drawable.sign26);
        SignList.add(R.drawable.sign28);
        SignList.add(R.drawable.sign5);
        SignList.add(R.drawable.sign47);
        SignList.add(R.drawable.sign38);
        SignList.add(R.drawable.sign1);
        SignList.add(R.drawable.sign40);
        SignList.add(R.drawable.sign41);
        SignList.add(R.drawable.sign32);
        SignList.add(R.drawable.sign7);
        SignList.add(R.drawable.sign46);
        SignList.add(R.drawable.sign70);
        SignList.add(R.drawable.sign13);
        SignList.add(R.drawable.sign118);
        SignList.add(R.drawable.sign72);
        SignList.add(R.drawable.sign71);
        SignList.add(R.drawable.sign129);
        SignList.add(R.drawable.sign34);
        SignList.add(R.drawable.sign35);
        SignList.add(R.drawable.sign29);
        SignList.add(R.drawable.sign120);
        SignList.add(R.drawable.warning_road_narrowing_right);
        SignList.add(R.drawable.warning_road_narrowing_left);
        SignList.add(R.drawable.two_way_traffic_straight_ahead);
        SignList.add(R.drawable.sign10);
        SignList.add(R.drawable.truck_lay_bay);
        SignList.add(R.drawable.toll_booth_ahead);
        SignList.add(R.drawable.sign2);
        SignList.add(R.drawable.sign52);
        SignList.add(R.drawable.sign53);
        SignList.add(R.drawable.sign55);
        SignList.add(R.drawable.sign54);
        SignList.add(R.drawable.sign161);
        SignList.add(R.drawable.sign56);
        SignList.add(R.drawable.sign66);
        SignList.add(R.drawable.sign78);
        SignList.add(R.drawable.sign103);
        SignList.add(R.drawable.sign43);
        SignList.add(R.drawable.sign75);
        SignList.add(R.drawable.sign50);
        SignList.add(R.drawable.sign74);
        SignList.add(R.drawable.pedestrian_subway);
        SignList.add(R.drawable.repair_facility);
    }

    private void RtoQuestionData_Fatch() {
        String str;
        String str2;
        String str3;
        String str4;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.rto_english)));
            StringBuilder stringBuffer = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            JSONArray jSONArray = new JSONObject(stringBuffer.toString()).getJSONArray("data");
            int length = jSONArray.length();
            int i = 0;
            while (true) {
                str = "option";
                str2 = "correctAnswer";
                str3 = "Answer";
                str4 = "Question";
                if (i >= length) {
                    break;
                }
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("Isimage");
                String string2 = jSONObject.getString("ImageUrl");
                String string3 = jSONObject.getString("Question");
                String string4 = jSONObject.getString("Answer");
                String string5 = jSONObject.getString("correctAnswer");
                ArrayList<String> arrayList = new ArrayList<>();
                JSONArray jSONArray2 = jSONObject.getJSONArray("option");
                String obj = jSONArray2.get(0).toString();
                String obj2 = jSONArray2.get(1).toString();
                String obj3 = jSONArray2.get(2).toString();
                arrayList.add(obj);
                arrayList.add(obj2);
                arrayList.add(obj3);
                BIKE_RTOQuestionModel bIKE_RTOQuestionModel = new BIKE_RTOQuestionModel();
                bIKE_RTOQuestionModel.setIsimage(string);
                bIKE_RTOQuestionModel.setImageUrl(string2);
                bIKE_RTOQuestionModel.setQuestion(string3);
                bIKE_RTOQuestionModel.setAnswer(string4);
                bIKE_RTOQuestionModel.setCorrectAnswer(string5);
                bIKE_RTOQuestionModel.setQuestionOptions(arrayList);
                bIKE_RTOQuestionModel.setAttemptMiss(false);
                bIKE_RTOQuestionModel.setAttempt(false);
                bIKE_RTOQuestionModel.setSelctedAnswer("0");
                EQuestionsArray.add(bIKE_RTOQuestionModel);
                i++;
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.rto_hindi)));
            StringBuilder stringBuffer2 = new StringBuilder();
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    break;
                }
                stringBuffer2.append(readLine2);
            }
            JSONArray jSONArray3 = new JSONObject(stringBuffer2.toString()).getJSONArray("data");
            int length2 = jSONArray3.length();
            int i2 = 0;
            while (i2 < length2) {
                JSONObject jSONObject2 = jSONArray3.getJSONObject(i2);
                String string6 = jSONObject2.getString("Isimage");
                String string7 = jSONObject2.getString("ImageUrl");
                String string8 = jSONObject2.getString(str4);
                String string9 = jSONObject2.getString(str3);
                String string10 = jSONObject2.getString(str2);
                String str5 = str2;
                ArrayList<String> arrayList2 = new ArrayList<>();
                JSONArray jSONArray5 = jSONObject2.getJSONArray(str);
                String str6 = str;
                String obj4 = jSONArray5.get(0).toString();
                String str7 = str3;
                String obj5 = jSONArray5.get(1).toString();
                String str8 = str4;
                String obj6 = jSONArray5.get(2).toString();
                arrayList2.add(obj4);
                arrayList2.add(obj5);
                arrayList2.add(obj6);
                BIKE_RTOQuestionModel bIKE_RTOQuestionModel2 = new BIKE_RTOQuestionModel();
                bIKE_RTOQuestionModel2.setIsimage(string6);
                bIKE_RTOQuestionModel2.setImageUrl(string7);
                bIKE_RTOQuestionModel2.setQuestion(string8);
                bIKE_RTOQuestionModel2.setAnswer(string9);
                bIKE_RTOQuestionModel2.setCorrectAnswer(string10);
                bIKE_RTOQuestionModel2.setQuestionOptions(arrayList2);
                bIKE_RTOQuestionModel2.setAttemptMiss(false);
                bIKE_RTOQuestionModel2.setAttempt(false);
                bIKE_RTOQuestionModel2.setSelctedAnswer("0");
                HQuestionsArray.add(bIKE_RTOQuestionModel2);
                i2++;
                str2 = str5;
                str = str6;
                str3 = str7;
                str4 = str8;
            }
            String str9 = str;
            String str10 = str2;
            String str11 = str3;
            String str12 = str4;
            BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.rto_gujarati)));
            StringBuilder stringBuffer3 = new StringBuilder();
            while (true) {
                String readLine3 = bufferedReader3.readLine();
                if (readLine3 == null) {
                    break;
                }
                stringBuffer3.append(readLine3);
            }
            JSONArray jSONArray6 = new JSONObject(stringBuffer3.toString()).getJSONArray("data");
            int length3 = jSONArray6.length();
            int i4 = 0;
            while (i4 < length3) {
                JSONObject jSONObject3 = jSONArray6.getJSONObject(i4);
                String string11 = jSONObject3.getString("Isimage");
                String string12 = jSONObject3.getString("ImageUrl");
                String str13 = str12;
                String string13 = jSONObject3.getString(str13);
                String str14 = str11;
                String string14 = jSONObject3.getString(str14);
                String str15 = str10;
                String string15 = jSONObject3.getString(str15);
                ArrayList<String> arrayList3 = new ArrayList<>();
                str10 = str15;
                String str16 = str9;
                JSONArray jSONArray8 = jSONObject3.getJSONArray(str16);
                str9 = str16;
                String obj7 = jSONArray8.get(0).toString();
                str11 = str14;
                String obj8 = jSONArray8.get(1).toString();
                str12 = str13;
                String obj9 = jSONArray8.get(2).toString();
                arrayList3.add(obj7);
                arrayList3.add(obj8);
                arrayList3.add(obj9);
                BIKE_RTOQuestionModel bIKE_RTOQuestionModel3 = new BIKE_RTOQuestionModel();
                bIKE_RTOQuestionModel3.setIsimage(string11);
                bIKE_RTOQuestionModel3.setImageUrl(string12);
                bIKE_RTOQuestionModel3.setQuestion(string13);
                bIKE_RTOQuestionModel3.setAnswer(string14);
                bIKE_RTOQuestionModel3.setCorrectAnswer(string15);
                bIKE_RTOQuestionModel3.setQuestionOptions(arrayList3);
                bIKE_RTOQuestionModel3.setAttemptMiss(false);
                bIKE_RTOQuestionModel3.setAttempt(false);
                bIKE_RTOQuestionModel3.setSelctedAnswer("0");
                GQuestionsArray.add(bIKE_RTOQuestionModel3);
                i4++;
            }
            BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.rto_marathi)));
            StringBuilder stringBuffer4 = new StringBuilder();
            while (true) {
                String readLine4 = bufferedReader4.readLine();
                if (readLine4 == null) {
                    break;
                }
                stringBuffer4.append(readLine4);
            }
            JSONArray jSONArray9 = new JSONObject(stringBuffer4.toString()).getJSONArray("data");
            int length4 = jSONArray9.length();
            int i6 = 0;
            while (i6 < length4) {
                JSONObject jSONObject4 = jSONArray9.getJSONObject(i6);
                String string16 = jSONObject4.getString("Isimage");
                String string17 = jSONObject4.getString("ImageUrl");
                String str17 = str12;
                String string18 = jSONObject4.getString(str17);
                String str18 = str11;
                String string19 = jSONObject4.getString(str18);
                String str19 = str10;
                String string20 = jSONObject4.getString(str19);
                JSONArray jSONArray10 = jSONArray9;
                ArrayList<String> arrayList4 = new ArrayList<>();
                str10 = str19;
                String str20 = str9;
                JSONArray jSONArray11 = jSONObject4.getJSONArray(str20);
                int i7 = length4;
                String obj10 = jSONArray11.get(0).toString();
                str9 = str20;
                String obj11 = jSONArray11.get(1).toString();
                str11 = str18;
                String obj12 = jSONArray11.get(2).toString();
                arrayList4.add(obj10);
                arrayList4.add(obj11);
                arrayList4.add(obj12);
                BIKE_RTOQuestionModel bIKE_RTOQuestionModel4 = new BIKE_RTOQuestionModel();
                bIKE_RTOQuestionModel4.setIsimage(string16);
                bIKE_RTOQuestionModel4.setImageUrl(string17);
                bIKE_RTOQuestionModel4.setQuestion(string18);
                bIKE_RTOQuestionModel4.setAnswer(string19);
                bIKE_RTOQuestionModel4.setCorrectAnswer(string20);
                bIKE_RTOQuestionModel4.setQuestionOptions(arrayList4);
                bIKE_RTOQuestionModel4.setAttemptMiss(false);
                bIKE_RTOQuestionModel4.setAttempt(false);
                bIKE_RTOQuestionModel4.setSelctedAnswer("0");
                MQuestionsArray.add(bIKE_RTOQuestionModel4);
                i6++;
                str12 = str17;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
