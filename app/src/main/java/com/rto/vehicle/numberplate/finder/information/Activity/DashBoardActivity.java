package com.rto.vehicle.numberplate.finder.information.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rto.vehicle.numberplate.finder.information.Model.FuelModel;
import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.MyConstant;
import com.rto.vehicle.numberplate.finder.information.R;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalReferenceEngine;
import com.sdk.dierct.link.open.on.fail.sdk.InterAds;
import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity implements GlobalReferenceEngine.Callback {

    LinearLayout fule_main_layout;
    ProgressBar fule_progress;

    String cityId;
    String cityName;
    ArrayList<FuelModel> FuelList;
    String cardPosition = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dash_board);

        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner1));
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner2));

        fule_main_layout = findViewById(R.id.fule_main_layout);
        fule_progress = findViewById(R.id.fule_progress);

        fule_main_layout.setVisibility(View.GONE);
        fule_progress.setVisibility(View.VISIBLE);

        FuelList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(MyConstant.MY_PREFS_NAME, 0);
        cityName = sharedPreferences.getString("cityName", "Kolkata");
        cityId = sharedPreferences.getString("cityId", "4");

        ((TextView) findViewById(R.id.textView8)).setText(this.cityName);
        new GetData().execute();

        findViewById(R.id.setting_img).setOnClickListener(v -> {
            startActivity(new Intent(DashBoardActivity.this, SettingActivity.class));
        });

        findViewById(R.id.img_licence_info).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, DLActivity.class));
            });
        });

        findViewById(R.id.rc_img_1_bike).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, SearchVehicleActivity.class).putExtra("TYPE", "RC"));
            });
        });

        findViewById(R.id.img_insurance_info).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, SearchVehicleActivity.class).putExtra("TYPE", "INSURANCE"));
            });
        });

        findViewById(R.id.rc_img_2_bus).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, SearchVehicleActivity.class).putExtra("TYPE", "RC"));
            });
        });

        findViewById(R.id.rc_img_3_car).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, SearchVehicleActivity.class).putExtra("TYPE", "RC"));
            });
        });

        findViewById(R.id.rc_img_4_tractor).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, SearchVehicleActivity.class).putExtra("TYPE", "RC"));
            });
        });

        findViewById(R.id.change_city_text).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, FuleCityActivity.class));
            });
        });

        findViewById(R.id.celebrity_img_1_singers).setOnClickListener(v -> {
            cardPosition = "3";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_2_dancers).setOnClickListener(v -> {
            cardPosition = "1";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_3_sports).setOnClickListener(v -> {
            cardPosition = "5";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_4_actors).setOnClickListener(v -> {
            cardPosition = "7";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_5_tycoons).setOnClickListener(v -> {
            cardPosition = "2";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_6_actress).setOnClickListener(v -> {
            cardPosition = "6";
            initCeleb();
        });

        findViewById(R.id.celebrity_img_7_politicians).setOnClickListener(v -> {
            cardPosition = "4";
            initCeleb();
        });

        findViewById(R.id.dl_test_img_1).setOnClickListener(view -> {
            testQuestionDialog();
        });

        findViewById(R.id.dl_test_img_2).setOnClickListener(view -> {
            testPaperDialog();
        });

        findViewById(R.id.dl_test_img_3).setOnClickListener(view -> {
            testExamDialog();
        });

        findViewById(R.id.dl_test_img_4).setOnClickListener(v -> {
            new InterAds().ShowfuullAd(this, () -> {
                startActivity(new Intent(DashBoardActivity.this, TestResultActivity.class));
            });
        });
    }

    private void testQuestionDialog() {
        final Dialog dialog = new Dialog(this, R.style.fullScreenDialog);
        dialog.setContentView(R.layout.dialog_language_choose);
        final RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        dialog.findViewById(R.id.select).setOnClickListener(view -> {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.btn_english) {
                MyApplication.EXAM_RESOURCE = MyApplication.ENGLISH_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.EQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    startActivity(new Intent(DashBoardActivity.this, TestRtoQuestionActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_gujarati) {
                MyApplication.EXAM_RESOURCE = MyApplication.GUJARATI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.GQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    startActivity(new Intent(DashBoardActivity.this, TestRtoQuestionActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_hindi) {
                MyApplication.EXAM_RESOURCE = MyApplication.HINDI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.HQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    startActivity(new Intent(DashBoardActivity.this, TestRtoQuestionActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_marathi) {
                MyApplication.EXAM_RESOURCE = MyApplication.MARATHI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.MQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    startActivity(new Intent(DashBoardActivity.this, TestRtoQuestionActivity.class));
                    dialog.dismiss();
                });
            }
        });
        dialog.show();
    }

    public void testPaperDialog() {
        final Dialog dialog = new Dialog(this, R.style.fullScreenDialog);
        dialog.setContentView(R.layout.dialog_language_choose);
        final RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        dialog.findViewById(R.id.select).setOnClickListener(view -> {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.btn_english) {
                MyApplication.EXAM_RESOURCE = MyApplication.ENGLISH_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.EQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestPaperActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_gujarati) {
                MyApplication.EXAM_RESOURCE = MyApplication.GUJARATI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.GQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestPaperActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_hindi) {
                MyApplication.EXAM_RESOURCE = MyApplication.HINDI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.HQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestPaperActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_marathi) {
                MyApplication.EXAM_RESOURCE = MyApplication.MARATHI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.MQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestPaperActivity.class));
                    dialog.dismiss();
                });
            }
        });
        dialog.show();
    }

    public void testExamDialog() {
        final Dialog dialog = new Dialog(this, R.style.fullScreenDialog);
        dialog.setContentView(R.layout.dialog_language_choose);
        final RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        dialog.findViewById(R.id.select).setOnClickListener(view -> {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.btn_english) {
                MyApplication.EXAM_RESOURCE = MyApplication.ENGLISH_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.EQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestExamActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_gujarati) {
                MyApplication.EXAM_RESOURCE = MyApplication.GUJARATI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.GQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestExamActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_hindi) {
                MyApplication.EXAM_RESOURCE = MyApplication.HINDI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.HQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestExamActivity.class));
                    dialog.dismiss();
                });
            } else if (checkedRadioButtonId == R.id.btn_marathi) {
                MyApplication.EXAM_RESOURCE = MyApplication.MARATHI_RESOURCE;
                MyApplication.QuestionsArray = MyApplication.MQuestionsArray;
                new InterAds().ShowfuullAd(this, () -> {
                    DashBoardActivity.this.startActivity(new Intent(DashBoardActivity.this, TestExamActivity.class));
                    dialog.dismiss();
                });
            }
        });
        dialog.show();
    }

    @Override
    public void onConfigLoaded() {
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalReferenceEngine.setCallback(this);
        GlobalReferenceEngine.updateConfig();
    }

    public void initCeleb() {
        Intent intent = new Intent(DashBoardActivity.this, CelebrityListActivity.class);
        intent.putExtra("path", cardPosition);
        new InterAds().ShowfuullAd(this, () -> {
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    private class GetData extends AsyncTask<String, String, String> {
        StringBuilder gold22kp;
        StringBuilder gold24kp;

        private GetData() {
            this.gold24kp = new StringBuilder();
            this.gold22kp = new StringBuilder();
        }

        @Override
        public String doInBackground(String[] strArr) {
            try {
                Elements select = Jsoup.connect("https://www.mypetrolprice.com/" + DashBoardActivity.this.cityId + "/Fuel-prices-in-Kolkata").get().select("div.OuterDiv");
                int size = select.size();
                for (int i = 0; i < size; i++) {

                    FuelList.add(new FuelModel(select.eq(i).select("div.UCFuelName").text(),
                            select.eq(i).select("div.Italic").text(),
                            select.eq(i).select("span.day").text(),
                            select.eq(i).select("span.month").text(),
                            select.eq(i).select("span.year").text(),
                            select.eq(i).select("div.fnt27").text(),
                            select.eq(i).select("div.fnt18").text(),
                            select.eq(i).select("div.UCFuelName").text()));
                }
                return null;
            } catch (IOException unused) {
                return null;
            }
        }

        @Override
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (!FuelList.isEmpty()) {
                FuelModel fuelModel0 = FuelList.get(0);
                ((TextView) findViewById(R.id.petrol_value)).setText(fuelModel0.getCurrentPrice());
                FuelModel fuelModel1 = FuelList.get(1);
                ((TextView) findViewById(R.id.diesel_value)).setText(fuelModel1.getCurrentPrice());
            }
            fule_progress.setVisibility(View.GONE);
            fule_main_layout.setVisibility(View.VISIBLE);
        }
    }

    public void showDialog() {

        Dialog myDialog = new Dialog(this);
        myDialog.requestWindowFeature(1);
        myDialog.setContentView(R.layout.dialog_exit);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        myDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myDialog.show();

        myDialog.findViewById(R.id.no_text).setOnClickListener(v -> {
            myDialog.dismiss();
        });

        myDialog.findViewById(R.id.yes_text).setOnClickListener(v -> {
            myDialog.dismiss();
            finishAffinity();
            System.exit(0);
        });
    }
}
